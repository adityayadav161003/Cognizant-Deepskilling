import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Q38_DecompilerDemo {
    public static void main(String[] args) {
        System.out.println("=== Java Decompiler Demo (using CFR) ===");

        String sourceCode = 
            "public class DecompileTarget {\n" +
            "    public void testMethod() {\n" +
            "        int value = 42;\n" +
            "        if (value > 10) {\n" +
            "            System.out.println(\"Value is greater than 10\");\n" +
            "        } else {\n" +
            "            System.out.println(\"Value is small\");\n" +
            "        }\n" +
            "    }\n" +
            "}\n";

        String targetClassName = "DecompileTarget";
        String srcFileName = targetClassName + ".java";
        String classFileName = targetClassName + ".class";
        
        // Find CFR decompiler jar
        File cfrJar = new File("libs/cfr-0.152.jar");
        if (!cfrJar.exists()) {
            cfrJar = new File("week1/libs/cfr-0.152.jar");
        }
        if (!cfrJar.exists()) {
            cfrJar = new File("../libs/cfr-0.152.jar");
        }

        if (!cfrJar.exists()) {
            System.err.println("Error: CFR decompiler jar not found at: " + cfrJar.getAbsolutePath());
            System.err.println("Please run environment setup to download it.");
            return;
        }

        try {
            // 1. Write the source file
            try (FileWriter writer = new FileWriter(srcFileName)) {
                writer.write(sourceCode);
            }
            System.out.println("1. Generated temporary class source file: " + srcFileName);

            String sdkBinDir = findJavaBinDirectory();
            String javacPath = sdkBinDir.isEmpty() ? "javac" : sdkBinDir + "javac";
            String javaPath = sdkBinDir.isEmpty() ? "java" : sdkBinDir + "java";

            // 2. Compile the class
            System.out.println("2. Compiling class: " + srcFileName + "...");
            runCommand(javacPath, srcFileName);

            if (!new File(classFileName).exists()) {
                throw new RuntimeException("Compilation failed: class file not created.");
            }
            System.out.println("   Compilation succeeded.");

            // 3. Decompile using CFR
            System.out.println("3. Decompiling " + classFileName + " using CFR (" + cfrJar.getName() + ")...");
            System.out.println("--------------------------------------------------------------------------------");
            runCommand(javaPath, "-jar", cfrJar.getAbsolutePath(), classFileName);
            System.out.println("--------------------------------------------------------------------------------");

        } catch (Exception e) {
            System.err.println("Decompilation demo failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cleanup files
            try {
                Files.deleteIfExists(Paths.get(srcFileName));
                Files.deleteIfExists(Paths.get(classFileName));
                System.out.println("\n4. Cleaned up temporary source and class files.");
            } catch (Exception ignored) {}
        }
    }

    private static String findJavaBinDirectory() {
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
