import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Q37_JavapBytecodeInspection {
    public static void main(String[] args) {
        System.out.println("=== Using javap to Inspect Bytecode ===");

        // Target source code representing the class to inspect
        String sourceCode = 
            "public class SimpleMath {\n" +
            "    public int addAndMultiply(int a, int b) {\n" +
            "        int sum = a + b;\n" +
            "        return sum * 2;\n" +
            "    }\n" +
            "}\n";

        String targetClassName = "SimpleMath";
        String srcFileName = targetClassName + ".java";
        String classFileName = targetClassName + ".class";

        try {
            // 1. Write the source file
            try (FileWriter writer = new FileWriter(srcFileName)) {
                writer.write(sourceCode);
            }
            System.out.println("1. Generated temporary class source file: " + srcFileName);

            // Determine java compiler path or use system path
            String sdkBinDir = findJavaBinDirectory();
            String javacPath = sdkBinDir.isEmpty() ? "javac" : sdkBinDir + "javac";
            String javapPath = sdkBinDir.isEmpty() ? "javap" : sdkBinDir + "javap";

            // 2. Compile the class
            System.out.println("2. Compiling " + srcFileName + " using " + javacPath + "...");
            runCommand(javacPath, srcFileName);

            if (!new File(classFileName).exists()) {
                throw new RuntimeException("Compilation failed: " + classFileName + " not generated.");
            }
            System.out.println("   Compilation succeeded.");

            // 3. Run javap -c to inspect bytecode
            System.out.println("3. Running javap -c " + targetClassName + "...");
            System.out.println("--------------------------------------------------------------------------------");
            runCommand(javapPath, "-c", targetClassName);
            System.out.println("--------------------------------------------------------------------------------");

            // 4. Interpretation notes
            System.out.println("\n=== Interpretation of the Bytecode ===");
            System.out.println("- iload_1 & iload_2: Load the int parameters 'a' and 'b' onto the operand stack.");
            System.out.println("- iadd: Pops two integers from the stack, adds them, and pushes the result back.");
            System.out.println("- istore_3: Pops the sum result and stores it in local variable 3 ('sum').");
            System.out.println("- iload_3: Loads the local variable 3 ('sum') onto the stack.");
            System.out.println("- iconst_2: Pushes the integer constant '2' onto the stack.");
            System.out.println("- imul: Pops two values (sum and 2) from the stack, multiplies them, and pushes the result.");
            System.out.println("- ireturn: Returns the integer result from the method.");

        } catch (Exception e) {
            System.err.println("Execution failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cleanup files
            try {
                Files.deleteIfExists(Paths.get(srcFileName));
                Files.deleteIfExists(Paths.get(classFileName));
                System.out.println("\n4. Cleaned up temporary files.");
            } catch (Exception ignored) {}
        }
    }

    private static String findJavaBinDirectory() {
        // Try to check if we are in the installed Temurin path
        String path = "C:\\Program Files\\Eclipse Adoptium\\jdk-21.0.11.10-hotspot\\bin\\";
        if (new File(path + "javac.exe").exists()) {
            return path;
        }
        return "";
    }

    private static void runCommand(String... command) throws Exception {
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        Process p = pb.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("   " + line);
            }
        }
        int exitCode = p.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Command failed with exit code: " + exitCode);
        }
    }
}
