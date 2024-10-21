import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient2 {
    public static int PORT = 5000; // Define server port
    public static String SERVER_IP = "127.0.0.1"; // Define server IP address
    private static int choice; // To store user's menu choice

    public static void main(String[] args) {
        if (args.length != 1) { // Check if a string is passed in arguments
            System.err.println("Usage: java TCPClient 'word-of-choice'<string>");
            System.exit(1);
        }

        System.out.println("Address: " + SERVER_IP);

        try (Scanner usrChoice = new Scanner(System.in)) {
            System.out.println("Enter integer value from the following:");
            System.out.println("1: STATS: Prints the stats of the text string sent by the client.");
            System.out.println("2: ANAGRAM: Prints the anagram of the text string sent by the client.");
            System.out.println("3: DROP: Ends the connection.");
            choice = usrChoice.nextInt(); // Read user's menu choice
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number between 1 and 3.");
            System.exit(1);
        }

        String word = args[0]; // The word (text string) to send to the server

        // Try to establish a connection to the server
        Socket mysocket = null;
        try {
            mysocket = new Socket(SERVER_IP, PORT); // Attempt to connect to the server
            System.out.println("Socket: " + mysocket);

            BufferedReader in = new BufferedReader(new InputStreamReader(mysocket.getInputStream())); // To read data from the server
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mysocket.getOutputStream())), true); // To send data to the server

            // Read server message about TTL
            System.out.println(in.readLine()); // Read and display the server's TTL message

            switch (choice) {
                case 1 -> { // STATS
                    System.out.println("STATS of: " + word);
                    out.println("STATS"); // Send STATS command
                    out.println(word); // Send text string to server

                    // Receive statistics from the server
                    for (int i = 0; i < 4; i++) {
                        String str = in.readLine();
                        System.out.println(str);
                    }
                }
                case 2 -> { // ANAGRAM
                    System.out.println("ANAGRAM of: " + word);
                    out.println("ANAGRAM"); // Send ANAGRAM command
                    out.println(word); // Send text string to server

                    // Receive the anagram from the server
                    String anagram = in.readLine();
                    System.out.println(anagram);
                }
                case 3 -> { // DROP
                    System.out.println("DROP connection to server.");
                    out.println("DROP"); // Send DROP command

                    // Receive server confirmation of closed connection
                    String str = in.readLine();
                    System.out.println(str);
                }
                default -> {
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                    System.exit(1);
                }
            }

        } catch (IOException e) { // Handle if the server is down
            System.out.println("Error: Could not connect to the server. It might be down.");
        } finally {
            if (mysocket != null) { // Close the socket if it was successfully opened
                try {
                    System.out.println("Closing socket...");
                    mysocket.close();
                } catch (IOException e) {
                    System.out.println("Error closing socket: " + e.getMessage());
                }
            } else {
                System.out.println("Socket was never opened.");
            }
        }
    }
}
