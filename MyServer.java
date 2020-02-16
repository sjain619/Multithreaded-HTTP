import java.io.*;
import java.net.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MyServer {

	Socket	socket = null; 
	int port;

	public MyServer(int port){ 
		this.port = port;
	}
	public static void main(String args[]) 
	{ 
		MyServer  server = new MyServer(1253); 
		server.startAccepting(server.port);
	} 
	
	public void startAccepting(int port){
		try{ 
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Listening for connection");
			while(true) {
				try {
					socket=serverSocket.accept();
					Thread request = new RequestProcessor(socket);
					request.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}catch (IOException e) {
			System.exit(0);
		}
	}
}




