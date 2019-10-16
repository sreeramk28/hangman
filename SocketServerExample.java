import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerExample {
    
    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9877;
    private String thecategory;
	private String theword;
	public String retCategory() {
		return thecategory;
	}
	public String retWord() {
		return theword;
	}	
    public SocketServerExample() throws IOException, ClassNotFoundException{
        //create the socket server object
        server = new ServerSocket(port);
        for(int i=0; i<2; i++) {
			System.out.println("....Connected....");
            if(i == 0) 
				System.out.println("...Receiving Category...");
			else if(i == 1) {
				System.out.println("...Receiving Word...");
			}	
			//System.out.println("Waiting for the client request");
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            String message = (String) ois.readObject();
            if(i == 0) {
				thecategory = message;
			}
			if(i == 1) {
			}	theword = message;
			System.out.print("Message Received: ");
			int len = message.length();
			while(len-- > 0) System.out.print("X");
            System.out.println();
			//create ObjectOutputStream object
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //write object to Socket
            oos.writeObject(message);
            //close resources
            ois.close();
            oos.close();
            socket.close();
        }
        System.out.println("Connection Closed!!");
        server.close();
    }
    
}
