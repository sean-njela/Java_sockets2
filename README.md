# 📡 Java TCP Client-Server Program

Welcome to the **Java TCP Client-Server Program** 🌐 — a simple Java application that simulates a client-server communication model where the client sends commands and the server responds with specific actions. The server processes three main commands: STATS, ANAGRAM, and DROP, with each having a different output or function.

## 📝 Features

- **STATS**: Sends a string to the server, and the server returns the number of lowercase letters, uppercase letters, digits, and remaining characters (special characters, spaces, etc.).
- **ANAGRAM**: Sends a string to the server, and the server returns an anagram of that string.
- **DROP**: Closes the connection between the client and server.
- **Error Handling**: Handles incorrect commands and informs the user when the server is down.
- **Timeout**: Closes the server connection automatically after 1 minute of inactivity (TTL - Time to Live).

---

## 🚀 Getting Started

### 1️⃣ **Prerequisites**

Ensure you have the following installed:

- ☕ **Java JDK** — Version 8 or above.

### 2️⃣ **Setting Up the Server**

1. Clone or download the Java files (both `TCPServer2.java` and `TCPClient2.java`) to your local machine.
2. Open a terminal in the directory where the files are saved.

3. Compile the server-side code using:

   ```bash
   javac TCPServer2.java
   ```

4. Run the server by typing:

   ```bash
   java TCPServer2
   ```

   The server will start and listen for incoming client connections. You should see output like:

   ```bash
   Working Directory: /path/to/your/working/directory
   Server started on IP: 127.0.0.1, Port: 5000
   ```

---

### 3️⃣ **Setting Up the Client**

1. In the same directory, compile the client-side code using:

   ```bash
   javac TCPClient2.java
   ```

2. Run the client by passing in the string you want to send to the server:

   ```bash
   java TCPClient2 "<your-text-here>"
   ```

3. Upon running the client, you will be prompted to choose one of three options:

   ```bash
   Enter integer value from the following:
   1: STATS: Prints the stats of the text string sent by the client.
   2: ANAGRAM: Prints the anagram of the text string sent by the client.
   3: DROP: Ends the connection.
   ```

---

## 📂 Command Details

### 4️⃣ **Client Commands**

- **STATS Command**:

  - Choose `1` to send the `STATS` command.
  - The server will return the number of lowercase letters, uppercase letters, digits, and special characters in the provided text.

  Example:

  ```bash
  $ java TCPClient2 "Hello123!"
  Enter integer value from the following:
  1: STATS: Prints the stats of the text string sent by the client.
  2: ANAGRAM: Prints the anagram of the text string sent by the client.
  3: DROP: Ends the connection.
  > 1
  STATS of: HELLO123!
  Number of lowercase letters: 4
  Number of uppercase letters: 1
  Number of digits: 3
  Number of special characters: 1
  ```

- **ANAGRAM Command**:

  - Choose `2` to send the `ANAGRAM` command.
  - The server will return an anagram (shuffled version) of the provided text.

  Example:

  ```bash
  $ java TCPClient2 "Hello123!"
  Enter integer value from the following:
  1: STATS: Prints the stats of the text string sent by the client.
  2: ANAGRAM: Prints the anagram of the text string sent by the client.
  3: DROP: Ends the connection.
  > 2
  ANAGRAM of: HELLO123!
  Server Response: oH1e3lL!2
  ```

- **DROP Command**:

  - Choose `3` to send the `DROP` command.
  - The server will close the connection with the client.

  Example:

  ```bash
  $ java TCPClient2 "Hello123!"
  Enter integer value from the following:
  1: STATS: Prints the stats of the text string sent by the client.
  2: ANAGRAM: Prints the anagram of the text string sent by the client.
  3: DROP: Ends the connection.
  > 3
  DROP connection to server.
  Connection closed by server.
  ```

---

### 5️⃣ **Error Handling**

The client-server system handles several error cases:

- **Invalid Command**: If an invalid command is sent, the server will notify the client with an error message:

  ```bash
  Error: Invalid command received!
  ```

- **Server Down**: If the server is down when trying to connect, the client will display:

  ```bash
  Error: Unable to connect to the server. Please try again later.
  ```

- **Timeout**: The server will automatically disconnect after 1 minute of inactivity. The client will receive a message notifying them about this:

  ```bash
  Server disconnected due to inactivity.
  ```

---

## 🎨 How it Works

1. **TCP Server**:

   - The server starts on `localhost` at port `5000`.
   - It waits for a client connection and processes incoming commands (`STATS`, `ANAGRAM`, or `DROP`).
   - It computes results and sends them back to the client.
   - If there is no activity from the client for 60 seconds, the server disconnects automatically.

2. **TCP Client**:
   - The client sends a string and selects one of three options:
     - `STATS`: Requests text statistics from the server.
     - `ANAGRAM`: Requests an anagram of the text from the server.
     - `DROP`: Ends the connection.
   - The client prints server responses to the console.

---

## 🔄 Restarting the Connection

If the server has closed the connection due to a timeout or a `DROP` command, you can always restart the server and run the client again by following the steps under "Setting Up the Server" and "Setting Up the Client."

---

## 📜 License

This project is open-source and freely available to modify or extend. Feel free to fork it and enhance it further!

---

## 🎉 Acknowledgments

Thanks for using this program! If you encounter any issues or want to contribute improvements, feel free to create a pull request or issue. 🚀

Happy Coding! 😊
