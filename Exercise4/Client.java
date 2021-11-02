import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author MELISSA LIAO
 *
 */
public class Client {
	
	private Socket dateSocket;
	private BufferedReader stdIn;
	private BufferedReader socketIn;
	private PrintWriter socketOut;
	
	public Client(String serverName, int portNumber) {
		try {
			dateSocket = new Socket(serverName, portNumber);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(dateSocket.getInputStream()));
			socketOut = new PrintWriter((dateSocket.getOutputStream()), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getDateTime() {
		String line = "";
		String response = "";
		while (!line.equals("QUIT")) {
			System.out.println("Please select an option (DATE/TIME) or type QUIT to end the program:");
			try {
				line = stdIn.readLine();// user must input either DATE or TIME
				socketOut.println(line);// connects to server to receive feedback
				response = socketIn.readLine();
				if (response != null) {
					System.out.println(response);
				}	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {// once user chooses to quit, all sockets will be closed
			stdIn.close();
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			System.out.println("Closing error: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		
		Client myClient = new Client("localhost", 9090);// port number must be the same as DateServer
		myClient.getDateTime();

	}

}
