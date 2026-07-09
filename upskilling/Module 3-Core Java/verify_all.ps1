# PowerShell verification script for restructured Java assignments
$ErrorActionPreference = "Stop"

# Use installed OpenJDK 21 path
$javaBin = "C:\Program Files\Eclipse Adoptium\jdk-21.0.11.10-hotspot\bin"
$javac = Join-Path $javaBin "javac.exe"
$java = Join-Path $javaBin "java.exe"
$javap = Join-Path $javaBin "javap.exe"

# If the hardcoded path is not available, default to system commands
if (-not (Test-Path $javac)) {
    $javac = "javac"
    $java = "java"
    $javap = "javap"
}

Write-Host "=== Java Compiler Verification ===" -ForegroundColor Cyan
& $javac -version
& $java -version
Write-Host "================================" -ForegroundColor Cyan

$successCount = 0
$totalCount = 40

# Helper to run compilation inside subfolder
function Compile-JavaFile($folder, $filename, $classpath = ".") {
    Write-Host "Compiling $folder/$filename... " -NoNewline -ForegroundColor Yellow
    try {
        $fullPath = Join-Path $folder $filename
        $p = Start-Process -FilePath $javac -ArgumentList "-cp", $classpath, $fullPath -NoNewWindow -PassThru -Wait
        if ($p.ExitCode -eq 0) {
            Write-Host "SUCCESS" -ForegroundColor Green
            return $true
        } else {
            Write-Host "FAILED (Exit Code: $($p.ExitCode))" -ForegroundColor Red
            return $false
        }
    } catch {
        Write-Host "ERROR: $($_.Exception.Message)" -ForegroundColor Red
        return $false
    }
}

# Helper to run java program inside subfolder with mock input and check output
function Run-JavaProgram($folder, $className, $mockInput = "", $expectedSubstring = "", $classpath = ".") {
    Write-Host "Running $className... " -NoNewline -ForegroundColor Yellow
    try {
        $psi = New-Object System.Diagnostics.ProcessStartInfo
        $psi.FileName = $java
        $psi.Arguments = "-cp `"$classpath`" $className"
        $psi.RedirectStandardInput = $true
        $psi.RedirectStandardOutput = $true
        $psi.RedirectStandardError = $true
        $psi.UseShellExecute = $false
        $psi.CreateNoWindow = $true
        $psi.WorkingDirectory = (Resolve-Path $folder).Path

        $proc = [System.Diagnostics.Process]::Start($psi)
        
        if ($mockInput -ne "") {
            $proc.StandardInput.WriteLine($mockInput)
        }
        
        $output = $proc.StandardOutput.ReadToEnd()
        $err = $proc.StandardError.ReadToEnd()
        $proc.WaitForExit(15000) # 15s timeout
        
        if ($proc.ExitCode -ne 0) {
            Write-Host "FAILED (Exit Code: $($proc.ExitCode))" -ForegroundColor Red
            Write-Host "Error details: $err" -ForegroundColor DarkRed
            return $false
        }
        
        if ($expectedSubstring -ne "" -and -not $output.Contains($expectedSubstring)) {
            Write-Host "FAILED (Output validation failed)" -ForegroundColor Red
            Write-Host "--- Output ---`n$output" -ForegroundColor DarkGray
            return $false
        }
        
        Write-Host "SUCCESS" -ForegroundColor Green
        return $true
    } catch {
        Write-Host "ERROR: $($_.Exception.Message)" -ForegroundColor Red
        return $false
    }
}

# Define array of tests
$tests = @(
    @{ ClassName = "Q02_SimpleCalculator"; Input = "15`n5`n/"; Expected = "3.00"; CP = "." },
    @{ ClassName = "Q03_EvenOrOddChecker"; Input = "17"; Expected = "is odd."; CP = "." },
    @{ ClassName = "Q04_LeapYearChecker"; Input = "2024"; Expected = "is a leap year."; CP = "." },
    @{ ClassName = "Q05_MultiplicationTable"; Input = "7"; Expected = "7.00 x 5 = 35.00"; CP = "." },
    @{ ClassName = "Q06_DataTypeDemonstration"; Input = ""; Expected = "Double value: 2.7182818284"; CP = "." },
    @{ ClassName = "Q07_TypeCastingExample"; Input = ""; Expected = "Casted int value (narrowing): 99"; CP = "." },
    @{ ClassName = "Q08_OperatorPrecedence"; Input = ""; Expected = "Result: 88"; CP = "." },
    @{ ClassName = "Q09_GradeCalculator"; Input = "85"; Expected = "Assigned Grade: B"; CP = "." },
    # Number Guessing Game: feed 1 to 100 so it eventually guesses correctly
    @{ ClassName = "Q10_NumberGuessingGame"; Input = ((1..100) -join "`n"); Expected = "Congratulations!"; CP = "." },
    @{ ClassName = "Q11_FactorialCalculator"; Input = "5"; Expected = "Factorial of 5 is 120."; CP = "." },
    @{ ClassName = "Q12_MethodOverloading"; Input = ""; Expected = "Calling add(5, 10, 15) [three integers]: 30"; CP = "." },
    @{ ClassName = "Q13_RecursiveFibonacci"; Input = "8"; Expected = "The 8-th Fibonacci number is: 21"; CP = "." },
    @{ ClassName = "Q14_ArraySumAndAverage"; Input = "3`n10`n20`n30"; Expected = "Average of elements: 20.00"; CP = "." },
    @{ ClassName = "Q15_StringReversal"; Input = "antigravity"; Expected = "ytivargitna"; CP = "." },
    @{ ClassName = "Q16_PalindromeChecker"; Input = "A man, a plan, a canal: Panama"; Expected = "The string is a palindrome."; CP = "." },
    @{ ClassName = "Q17_ClassAndObjectCreation"; Input = ""; Expected = "Car Details: 2024 Tesla Model 3"; CP = "." },
    @{ ClassName = "Q18_InheritanceExample"; Input = ""; Expected = "Calling Dog.makeSound(): Bark"; CP = "." },
    @{ ClassName = "Q19_InterfaceImplementation"; Input = ""; Expected = "Beethoven's Moonlight Sonata!"; CP = "." },
    @{ ClassName = "Q20_TryCatchExample"; Input = "10`n0"; Expected = "Division by zero is not allowed."; CP = "." },
    @{ ClassName = "Q21_CustomException"; Input = "15"; Expected = "Age is less than 18. Access Denied."; CP = "." },
    @{ ClassName = "Q22_FileWriting"; Input = "Hello File System!"; Expected = "written to output.txt."; CP = "." },
    @{ ClassName = "Q23_FileReading"; Input = ""; Expected = "Line 1: Hello File System!"; CP = "." },
    @{ ClassName = "Q24_ArrayListExample"; Input = "Alice`nBob`ndone"; Expected = "1. Alice"; CP = "." },
    @{ ClassName = "Q25_HashMapExample"; Input = "1`n101`nJohn`n2`n101`n3"; Expected = "Student Found: ID 101 -> John"; CP = "." },
    @{ ClassName = "Q26_ThreadCreation"; Input = ""; Expected = "Both threads have finished execution."; CP = "." },
    @{ ClassName = "Q27_LambdaExpressions"; Input = ""; Expected = "Sorted alphabetically: [Apple, Banana, Cherry"; CP = "." },
    @{ ClassName = "Q28_StreamAPI"; Input = ""; Expected = "Filtered even numbers list: [2, 4, 6, 8, 10, 22, 44]"; CP = "." },
    @{ ClassName = "Q29_Records"; Input = ""; Expected = "Adults (age >= 18):"; CP = "." },
    @{ ClassName = "Q30_PatternMatchingSwitch"; Input = ""; Expected = 'The object is a String: "Hello, Java 21!"'; CP = "." },
    # JDBC uses the downloaded sqlite-jdbc driver
    @{ ClassName = "Q31_BasicJDBCConnection"; Input = ""; Expected = "Student ID: 1, Name: Alice, Grade: A"; CP = "../libs/sqlite-jdbc-3.8.11.2.jar;." },
    @{ ClassName = "Q32_InsertUpdateJDBC"; Input = ""; Expected = "ID: 1, Name: David Smith, Grade: A"; CP = "../libs/sqlite-jdbc-3.8.11.2.jar;." },
    @{ ClassName = "Q33_TransactionHandlingJDBC"; Input = ""; Expected = "Transaction COMMITTED successfully."; CP = "../libs/sqlite-jdbc-3.8.11.2.jar;." },
    @{ ClassName = "Q34_JavaModules"; Input = ""; Expected = "Processed string: Welcome to java 9 modules!"; CP = "." },
    @{ ClassName = "Q35_TCPClientServerChat"; Input = ""; Expected = 'Server final reply: "Goodbye!"'; CP = "." },
    @{ ClassName = "Q36_HTTPClientAPI"; Input = ""; Expected = "Status: 200"; CP = "." },
    @{ ClassName = "Q37_JavapBytecodeInspection"; Input = ""; Expected = "istore_3"; CP = "." },
    @{ ClassName = "Q38_DecompilerDemo"; Input = ""; Expected = "DecompileTarget"; CP = "." },
    @{ ClassName = "Q39_ReflectionDemo"; Input = ""; Expected = "ReflectionTarget: This is a private secret method!"; CP = "." },
    @{ ClassName = "Q40_VirtualThreads"; Input = ""; Expected = "Virtual Threads launched and completed: 100000"; CP = "." },
    @{ ClassName = "Q41_ExecutorServiceCallable"; Input = ""; Expected = "Sum of all squares: 55"; CP = "." }
)

Write-Host "`n=== Starting Compilation and Verification ===" -ForegroundColor Cyan

foreach ($t in $tests) {
    $className = $t.ClassName
    $javaFileName = "$className.java"
    
    # Folder map lookup for descriptive folder names
    $folderMap = @{
        "Q02" = "SimpleCalculator"
        "Q03" = "EvenOrOddChecker"
        "Q04" = "LeapYearChecker"
        "Q05" = "MultiplicationTable"
        "Q06" = "DataTypeDemonstration"
        "Q07" = "TypeCastingExample"
        "Q08" = "OperatorPrecedence"
        "Q09" = "GradeCalculator"
        "Q10" = "NumberGuessingGame"
        "Q11" = "FactorialCalculator"
        "Q12" = "MethodOverloading"
        "Q13" = "RecursiveFibonacci"
        "Q14" = "ArraySumAndAverage"
        "Q15" = "StringReversal"
        "Q16" = "PalindromeChecker"
        "Q17" = "ClassAndObjectCreation"
        "Q18" = "InheritanceExample"
        "Q19" = "InterfaceImplementation"
        "Q20" = "TryCatchExample"
        "Q21" = "CustomException"
        "Q22" = "FileWriting"
        "Q23" = "FileReading"
        "Q24" = "ArrayListExample"
        "Q25" = "HashMapExample"
        "Q26" = "ThreadCreation"
        "Q27" = "LambdaExpressions"
        "Q28" = "StreamAPI"
        "Q29" = "Records"
        "Q30" = "PatternMatchingSwitch"
        "Q31" = "BasicJDBCConnection"
        "Q32" = "InsertUpdateJDBC"
        "Q33" = "TransactionHandlingJDBC"
        "Q34" = "JavaModules"
        "Q35" = "TCPClientServerChat"
        "Q36" = "HTTPClientAPI"
        "Q37" = "JavapBytecodeInspection"
        "Q38" = "DecompilerDemo"
        "Q39" = "ReflectionDemo"
        "Q40" = "VirtualThreads"
        "Q41" = "ExecutorServiceCallable"
    }

    if ($className -match "^(Q\d+)_(.+)$") {
        $prefix = $Matches[1]
        $folder = $folderMap[$prefix]
    } else {
        Write-Host "Error parsing folder for $className" -ForegroundColor Red
        continue
    }

    # Compile inside subfolder
    $compiled = Compile-JavaFile $folder $javaFileName $t.CP
    if ($compiled) {
        if ($folder -eq "FileReading") {
            Copy-Item -Path "FileWriting/output.txt" -Destination "FileReading/output.txt" -Force -ErrorAction SilentlyContinue
        }
        # Run inside subfolder
        $runResult = Run-JavaProgram $folder $className $t.Input $t.Expected $t.CP
        if ($runResult) {
            $successCount++
        }
    }
    Write-Host "----------------------------------------" -ForegroundColor DarkGray
}

Write-Host "`n=== Verification Summary ===" -ForegroundColor Cyan
if ($successCount -eq $totalCount) {
    Write-Host "ALL $successCount / $totalCount TESTS PASSED SUCCESSFULLY!" -ForegroundColor Green
} else {
    Write-Host "FAILED: Only $successCount / $totalCount tests passed." -ForegroundColor Red
}

# Cleanup compiled files and database files
Get-ChildItem -Path . -Filter *.class -Recurse | Remove-Item -Force -ErrorAction SilentlyContinue
Get-ChildItem -Path . -Filter *.db -Recurse | Remove-Item -Force -ErrorAction SilentlyContinue
