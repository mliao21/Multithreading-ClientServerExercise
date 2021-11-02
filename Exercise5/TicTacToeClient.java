import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author MELISSA LIAO
 *
 */
public class TicTacToeClient {
	
	private Socket gameSocket;
	private BufferedReader stdIn;
	private BufferedReader socketIn;
	private PrintWriter socketOut;
	
	public TicTacToeClient(String serverName, int portNumber) {
		try {
			gameSocket = new Socket(serverName, portNumber);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(gameSocket.getInputStream()));
			socketOut = new PrintWriter((gameSocket.getOutputStream()), true);
		} catch (IOException e) {
			System.err.println("Error in client! " + e.getStackTrace());
		}
	}
	
	public void communicateServer() {		
		try {
			while(true) {
				String read = "";
				while(true) {
					read = socketIn.readLine();// receive messages from the server
					// if message prompts user for input, loop will break and ask for input
					if(read.contains("\0")) {
						read= read.replace("\0", "");
						System.out.println(read);
						break;
					}
					if(read.equals("QUIT")) {
						System.out.println("Goodbye!");
						return;// will break all loops and close out sockets
					}
					System.out.println(read);// prints out messages sent by server
				}
				read = stdIn.readLine();// prompts user for input
				socketOut.println(read);// sends input back to server
				socketOut.flush();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {// All sockets are close once the game is done.
				stdIn.close();
				gameSocket.close();
				socketIn.close();
				socketOut.close();
			} catch (IOException e) {
				System.out.println("Closing error: " + e.getMessage());
			}
		}	
	}

	public static void main(String[] args) {
		
		// client communicates with server using the same port number, i.e. 8099
		TicTacToeClient myClient = new TicTacToeClient("localhost", 8099);
		myClient.communicateServer();

	}

}
