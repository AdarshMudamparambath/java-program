import java.io.*;
import java.net.*;

public class TCPServer {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server listening on port " + PORT + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connection accepted from " + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort());

                // Create a new thread to handle communication with the client
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
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

                // Create a separate thread to continuously read from the client
                ClientReader clientReader = new ClientReader(in);
                clientReader.start();

                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                String userInputLine;
                while ((userInputLine = userInput.readLine()) != null) {
                    out.println("Server: " + userInputLine);
                }

                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class ClientReader extends Thread {
        private BufferedReader in;

        public ClientReader(BufferedReader in) {
            this.in = in;
        }

        public void run() {
            try {
                String clientInput;
                while ((clientInput = in.readLine()) != null) {
                    System.out.println("Client: " + clientInput);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
