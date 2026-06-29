import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Q35_TCPClientServerChat {
    private static final int DEFAULT_PORT = 5000;

    public static void main(String[] args) {
        if (args.length > 0) {
            String role = args[0].toLowerCase();
            if (role.equals("server")) {
                runServer(DEFAULT_PORT);
            } else if (role.equals("client")) {
                runClient("localhost", DEFAULT_PORT);
            } else {
                System.out.println("Unknown role. Usage: java Q35_TCPClientServerChat [server|client]");
            }
        } else {
            // Self-running demonstration mode
            System.out.println("=== TCP Client-Server Chat Simulation ===");
            System.out.println("No arguments provided. Running automated thread-based simulation.");
            runSimulation();
        }
    }

    private static void runServer(int port) {
        System.out.println("[Server] Starting server on port " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[Server] Waiting for client to connect...");
            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 Scanner scanner = new Scanner(System.in)) {

                System.out.println("[Server] Client connected: " + clientSocket.getRemoteSocketAddress());
                out.println("Welcome to the TCP Chat Server! Type 'bye' to exit.");

                String clientMsg;
                while ((clientMsg = in.readLine()) != null) {
                    System.out.println("[Client says]: " + clientMsg);
                    if (clientMsg.equalsIgnoreCase("bye")) {
                        out.println("Goodbye client!");
                        break;
                    }
                    // Interactive mode (reading from console input for server)
                    System.out.print("[Server reply]: ");
                    if (scanner.hasNextLine()) {
                        String reply = scanner.nextLine();
                        out.println(reply);
                        if (reply.equalsIgnoreCase("bye")) {
                            break;
                        }
                    } else {
                        out.println("Received: " + clientMsg);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("[Server Error]: " + e.getMessage());
        }
    }

    private static void runClient(String host, int port) {
        System.out.println("[Client] Connecting to " + host + ":" + port + "...");
        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("[Client] Connected successfully!");
            // Read welcome message
            System.out.println("[Server says]: " + in.readLine());

            while (true) {
                System.out.print("[Client send]: ");
                if (scanner.hasNextLine()) {
                    String clientMsg = scanner.nextLine();
                    out.println(clientMsg);
                    if (clientMsg.equalsIgnoreCase("bye")) {
                        System.out.println("[Client] Connection closed.");
                        break;
                    }
                    String serverReply = in.readLine();
                    if (serverReply == null) {
                        System.out.println("[Client] Server disconnected.");
                        break;
                    }
                    System.out.println("[Server reply]: " + serverReply);
                    if (serverReply.equalsIgnoreCase("bye")) {
                        break;
                    }
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("[Client Error]: " + e.getMessage());
        }
    }

    private static void runSimulation() {
        final int simPort = 5055;
        // Start server in background thread
        Thread serverThread = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(simPort)) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    out.println("Hello Client! I am the automated test server.");
                    String msg = in.readLine();
                    System.out.println("[Sim-Server] Received: \"" + msg + "\"");
                    out.println("I received your message: " + msg);

                    msg = in.readLine();
                    System.out.println("[Sim-Server] Received: \"" + msg + "\"");
                    out.println("Goodbye!");
                }
            } catch (Exception e) {
                System.err.println("[Sim-Server Error]: " + e.getMessage());
            }
        });

        serverThread.start();

        // Let the server socket start up
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}

        // Connect client from main thread
        try (Socket socket = new Socket("localhost", simPort);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("[Sim-Client] Connected to server.");
            System.out.println("[Sim-Client] Server says: \"" + in.readLine() + "\"");

            System.out.println("[Sim-Client] Sending: \"Ping\"");
            out.println("Ping");

            System.out.println("[Sim-Client] Server replied: \"" + in.readLine() + "\"");

            System.out.println("[Sim-Client] Sending: \"Close Connection\"");
            out.println("Close Connection");
            System.out.println("[Sim-Client] Server final reply: \"" + in.readLine() + "\"");

        } catch (Exception e) {
            System.err.println("[Sim-Client Error]: " + e.getMessage());
        }

        try {
            serverThread.join();
        } catch (InterruptedException ignored) {}
        System.out.println("[Simulation] Complete.");
    }
}
