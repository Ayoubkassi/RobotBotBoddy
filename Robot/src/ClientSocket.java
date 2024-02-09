import java.io.*;
import java.net.*;
import java.util.Scanner;


public class ClientSocket {

   public static void main(String[] args) throws Exception 
   {
    String message="";
	
    Socket socket = new Socket(InetAddress.getByName("159.31.64.12"),2009);  //Créée un socket client et 
										 // se connecte au serveur à l'adresse et N° de port spécifié
	BufferedReader br;//br permet de lire les données retournées par le serveur.
    PrintWriter pw; //pw permet d'envoyer des données au serveur.
    
    
    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    
    while(true) {
    System.out.println("Enter command :");

    
    String userName = myObj.nextLine();  // Read user input
    //System.out.println("command is: " + userName);  // Output user input
    
    
    //System.out.println("SOCKET = " + socket);

    br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    pw = new PrintWriter(socket.getOutputStream(),true);

	System.out.println("envoie de bonjour ... 44\u00B008'11.2\" N  \"  "+ '\u00B0');
	pw.println(userName);
	System.out.println(br.readLine());
    }
	
   }
}
