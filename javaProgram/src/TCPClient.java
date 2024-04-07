import java.io.*;
import java.net.*;

public class TCPClient {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, PORT);
            System.out.println("Connected to server");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Create a separate thread to continuously read from the server
            ServerReader serverReader = new ServerReader(socket);
            serverReader.start();

            String userInputLine;
            while ((userInputLine = userInput.readLine()) != null) {
                out.println(userInputLine);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ServerReader extends Thread {
        private Socket socket;

        public ServerReader(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String serverInput;
                while ((serverInput = in.readLine()) != null) {
                    System.out.println("Server: " + serverInput);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
