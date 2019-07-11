import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Socket server = new Socket(
				JOptionPane.showInputDialog("IP"), 
				Integer.parseInt(JOptionPane.showInputDialog("PORT"))); 

		// Receiver
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Scanner s = new Scanner(server.getInputStream());
					while (s.hasNextLine()) {
						System.out.println(s.nextLine());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
		
		
		// Sender
		Scanner fromkeyboard = new Scanner(System.in);
		PrintStream tosend = new PrintStream(server.getOutputStream());
		while(fromkeyboard.hasNextLine()){
			tosend.println(fromkeyboard.nextLine());
		}
		
	}
	
}
