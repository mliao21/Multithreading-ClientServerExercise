import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author MELISSA LIAO
 *
 */
public class TicTacToeServer implements Constants {
	
	private ServerSocket serverSocket;
	private ExecutorService pool;
	
	public TicTacToeServer() {
		try {// Connects client with same port number
			serverSocket = new ServerSocket(8099);
			pool = Executors.newCachedThreadPool();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void communicateClient() {
		try {
			while (true) {
				// Communicates with Player class. A game only starts if 
				// two players connects to the server.
				Player xPlayer = new Player(serverSocket.accept(), LETTER_X);
				Player oPlayer = new Player(serverSocket.accept(), LETTER_O);
				
				// Referee sets players
				Referee theRef = new Referee();
				theRef.setoPlayer(oPlayer);
				theRef.setxPlayer(xPlayer);
				
				// Instantiate game and run with thread pool to start
				// multiple games at the same time
				Game theGame = new Game();
				theGame.appointReferee(theRef);
				System.out.println("Game has started...");
				pool.execute(theGame);
			}
		} catch (IOException e) {
			e.printStackTrace();
			pool.shutdown();
			System.out.println("Server is terminated!");
		}		
	}

	public static void main(String[] args) {
		
		TicTacToeServer myServer = new TicTacToeServer();
		System.out.println("Server is running...");
		myServer.communicateClient();

	}

}
