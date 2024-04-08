import java.io.*;
import java.net.*;

public class Client1 {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, PORT);
            System.out.println("Connected to server");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Create a separate thread to continuously read from the server
            ServerReader serverReader = new ServerReader(in);
            serverReader.start();

            String userInputLine;
            while ((userInputLine = userInput.readLine()) != null) {
                out.println("Client 1: " + userInputLine);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ServerReader extends Thread {
        private BufferedReader in;

        public ServerReader(BufferedReader in) {
            this.in = in;
        }

        public void run() {
            try {
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
