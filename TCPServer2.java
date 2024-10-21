import java.io.*;
import java.net.*;

public class TCPServer2 {
    public static int PORT = 5000; // Server port
    public static final int TIMEOUT = 60000; // 1 minute timeout (60000 milliseconds)

    public static void main(String[] args) throws IOException {
        // Get the local IP address
        InetAddress localAddress = InetAddress.getLocalHost();
        System.out.println("Working Directory: " + System.getProperty("user.dir"));

        ServerSocket s = new ServerSocket(PORT); // Create server socket
        System.out.println("Server started on IP: " + localAddress.getHostAddress() + ", Port: " + PORT);

        try {
            while (true) { // Loop to continuously accept new connections
                Socket mysocket = s.accept(); // Wait for client to connect
                System.out.println("Connection accepted: " + mysocket);

                try {
                    mysocket.setSoTimeout(TIMEOUT); // Set 1 minute timeout for inactivity

                    // Inform the client about the TTL
                    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mysocket.getOutputStream())), true);
                    out.println("Connected to the server. TTL for inactivity is 1 minute.");

                    // Create BufferedReader to read client input
                    BufferedReader in = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));

                    while (true) {
                        try {
                            String str = in.readLine(); // Read client input

                            if (str == null) { // Check if client disconnected
                                System.out.println("Client disconnected.");
                                break;
                            }

                            System.out.println("Received: " + str); // Log received message

                            // Handle STATS command
                            if (str.equals("STATS")) {
                                String txt = in.readLine(); // Read the text to process stats
                                if (txt == null) {
                                    out.println("No text received for STATS.");
                                    continue;
                                }

                                int lowerCaseCount = 0, upperCaseCount = 0, digitCount = 0, otherCount = 0;
                                for (int i = 0; i < txt.length(); i++) {
                                    char ch = txt.charAt(i);
                                    if (Character.isLowerCase(ch)) lowerCaseCount++;
                                    else if (Character.isUpperCase(ch)) upperCaseCount++;
                                    else if (Character.isDigit(ch)) digitCount++;
                                    else otherCount++;
                                }

                                // Send statistics back to client
                                out.println("Lowercase letters: " + lowerCaseCount);
                                out.println("Uppercase letters: " + upperCaseCount);
                                out.println("Digits: " + digitCount);
                                out.println("Other characters: " + otherCount);
                            }

                            // Handle ANAGRAM command
                            else if (str.equals("ANAGRAM")) {
                                String txt = in.readLine(); // Read the text to create anagram
                                if (txt == null) {
                                    out.println("No text received for ANAGRAM.");
                                    continue;
                                }

                                // Create anagram by shuffling characters (a simple logic)
                                String anagram = new StringBuilder(txt).reverse().toString();
                                out.println("Anagram: " + anagram);
                            }

                            // Handle DROP command (close connection)
                            else if (str.equals("DROP")) {
                                out.println("Connection closing as per DROP request.");
                                break;
                            }

                            // Handle incorrect command
                            else {
                                out.println("Error: Unrecognized command.");
                            }

                        } catch (SocketTimeoutException e) {
                            // Inform client about the timeout and close connection
                            out.println("No activity for 1 minute. Connection closed due to inactivity.");
                            System.out.println("Connection closed due to inactivity.");
                            break; // Exit the loop and close connection
                        }
                    }
                } finally {
                    System.out.println("Closing client connection...");
                    mysocket.close(); // Close the socket after finishing the communication
                }
            }
        } finally {
            System.out.println("Closing server socket...");
            s.close(); // Close server socket when the server shuts down
        }
    }
}
