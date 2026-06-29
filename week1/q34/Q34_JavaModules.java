import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class Q34_JavaModules {
    public static void main(String[] args) {
        System.out.println("=== Java Modules Compilation & Execution Demonstration ===");

        String baseDirStr = "temp_modules_demo";
        Path basePath = Paths.get(baseDirStr);

        try {
            // Clean up any old directories
            if (Files.exists(basePath)) {
                Files.walk(basePath)
                     .sorted(Comparator.reverseOrder())
                     .map(Path::toFile)
                     .forEach(File::delete);
            }

            // Create structure for com.utils
            Path utilsSrc = basePath.resolve("com.utils");
            Path utilsPkg = utilsSrc.resolve("com/utils");
            Files.createDirectories(utilsPkg);

            // Create structure for com.greetings
            Path greetingsSrc = basePath.resolve("com.greetings");
            Path greetingsPkg = greetingsSrc.resolve("com/greetings");
            Files.createDirectories(greetingsPkg);

            // Create output directory for compiled modules
            Path modsOut = basePath.resolve("mods");
            Files.createDirectories(modsOut);

            // Write com.utils files
            writeFile(utilsSrc.resolve("module-info.java").toFile(), 
                "module com.utils {\n" +
                "    exports com.utils;\n" +
                "}\n"
            );
            writeFile(utilsPkg.resolve("StringUtils.java").toFile(),
                "package com.utils;\n" +
                "public class StringUtils {\n" +
                "    public static String toTitleCase(String input) {\n" +
                "        if (input == null || input.isEmpty()) return input;\n" +
                "        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();\n" +
                "    }\n" +
                "}\n"
            );

            // Write com.greetings files
            writeFile(greetingsSrc.resolve("module-info.java").toFile(),
                "module com.greetings {\n" +
                "    requires com.utils;\n" +
                "}\n"
            );
            writeFile(greetingsPkg.resolve("Main.java").toFile(),
                "package com.greetings;\n" +
                "import com.utils.StringUtils;\n" +
                "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        String raw = \"welcome to java 9 modules!\";\n" +
                "        System.out.println(\"Greetings Module: Working with com.utils...\");\n" +
                "        System.out.println(\"Original string: \" + raw);\n" +
                "        System.out.println(\"Processed string: \" + StringUtils.toTitleCase(raw));\n" +
                "    }\n" +
                "}\n"
            );

            System.out.println("1. Created module source directory structure.");

            // Compile com.utils
            System.out.println("2. Compiling module 'com.utils'...");
            runCommand("javac", "-d", modsOut.resolve("com.utils").toString(),
                    utilsSrc.resolve("module-info.java").toString(),
                    utilsPkg.resolve("StringUtils.java").toString());

            // Compile com.greetings
            System.out.println("3. Compiling module 'com.greetings' with module-path...");
            runCommand("javac", "--module-path", modsOut.toString(), 
                    "-d", modsOut.resolve("com.greetings").toString(),
                    greetingsSrc.resolve("module-info.java").toString(),
                    greetingsPkg.resolve("Main.java").toString());

            // Run modular application
            System.out.println("4. Running modular application (java --module-path ... -m com.greetings/com.greetings.Main):");
            System.out.println("--------------------------------------------------------------------------------");
            runCommand("java", "--module-path", modsOut.toString(), "-m", "com.greetings/com.greetings.Main");
            System.out.println("--------------------------------------------------------------------------------");

        } catch (Exception e) {
            System.err.println("An error occurred during modular compilation/execution:");
            e.printStackTrace();
        } finally {
            // Cleanup directories
            try {
                if (Files.exists(basePath)) {
                    Files.walk(basePath)
                         .sorted(Comparator.reverseOrder())
                         .map(Path::toFile)
                         .forEach(File::delete);
                }
                System.out.println("5. Cleaned up temporary directory.");
            } catch (Exception ignored) {}
        }
    }

    private static void writeFile(File file, String content) throws Exception {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
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
