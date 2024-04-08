import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server1 {
    private static final int PORT = 8080;
    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server listening on port " + PORT);

            // Accept two client connections
            while (clients.size() < 2) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                clientHandler.start();
            }

            // Start a thread to read input from console and broadcast it to clients
            new Thread(Server1::readInputFromConsoleAndBroadcast).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readInputFromConsoleAndBroadcast() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            for (ClientHandler client : clients) {
                client.sendMessageToClient(input);
            }
        }
    }

    static class ClientHandler extends Thread {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Client: " + message);
                    // Relay message to all other clients
                    for (ClientHandler client : clients) {
                        if (client != this) {
                            client.sendMessageToClient(message);
                        }
                    }
                }

                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendMessageToClient(String message) {
            out.println(message);
        }
    }
}
