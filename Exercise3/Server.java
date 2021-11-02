import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author MELISSA LIAO
 *
 */
public class Server {
	
	private Socket aSocket;
	private ServerSocket serverSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	
	public Server() {
		try {// port number should be the same as client's port to connect
			serverSocket = new ServerSocket(8099);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void verifyPalindrome() {
		String line = null;
		while (true) {
			try {// read word from client's user input
				line = socketIn.readLine();
				if (line == null) {
					break;
				}
				else {
					String reverse = "";
					for(int i = line.length() - 1; i >= 0; i--) {
						reverse += line.charAt(i);// reverse the word backwards
					}
					if(line.equals(reverse)) {// verify if reversed word is the same as original
						line = line + " is a Palindrome.";
					}
					else {
						line = line + " is NOT a Palindrome.";
					}
					socketOut.println(line);
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public static void main(String[] args) {
		
		Server myServer = new Server();

		try {
			myServer.aSocket = myServer.serverSocket.accept();
			System.out.println("Server is running...");
			myServer.socketIn = new BufferedReader(new InputStreamReader(myServer.aSocket.getInputStream()));
			myServer.socketOut = new PrintWriter(myServer.aSocket.getOutputStream(), true);
			myServer.verifyPalindrome();
			myServer.socketIn.close();
			myServer.socketOut.close();
			System.out.println("Server is terminated!");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
