import java.io.*;
import java.net.*;

import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

public class ServeurSocket{
	
	public static  void traitCommand(String command,RobotPi robot,PrintWriter pw) throws IOException, InterruptedException {
		
		String[] commands = command.split(" ");
	     if(commands.length>=2) {
	    	 String trameok = commands[0]+" "+"TRAME_OK";
	    	 pw.println(trameok);//" <Nom de la commande><espace>TRAME_OK\n"
	    	 int Vit =Integer.parseInt(commands[1]);
	    	 switch (commands[0]) {
	    	 
	    	 case "AVANCER":
	    		 robot.moteur.controlMotor(Motor.Direction.AVANCER,2*Vit);
	         	
	             break;
	         case "RECULER":
	        	 robot.moteur.controlMotor(Motor.Direction.RECULER,2*Vit);
	         	
	             break;

	         case "GAUCHE":
	        	 robot.moteur.controlMotor(Motor.Direction.GAUCHE,2*Vit);
	         	
	             break;
	         case "DROITE":
	        	 robot.moteur.controlMotor(Motor.Direction.DROIT,2*Vit);
	         	
	             break;
	    	 
	    	 }//
	    	 }
	    	 else {
	    		 String trameok = command+" "+"TRAME_OK";
		    	 
	    		 switch (command) {
	    		 
	    		 	case "ARRET":
	    		 		pw.println(trameok);//" <Nom de la commande><espace>TRAME_OK\n"
	    		 		robot.moteur.controlMotor(Motor.Direction.ARRET,0);
	    		 		break;
	    		 	// Pour controler la Pince
	    		 	case "OUVRIR_PINCE":
	    		 		pw.println(trameok);//" <Nom de la commande><espace>TRAME_OK\n"
	    		 		robot.pince.controlPince(Pince.Action.OUVRIR);
	    		 		break;
	    		 		
	    		 	case "BAISSER_PINCE":
	    		 		pw.println(trameok);//" <Nom de la commande><espace>TRAME_OK\n"
	    		 		robot.pince.controlPince(Pince.Action.BAISSER);
	    		 		break;
	    		 	
	    		 	case "FERMER_PINCE":
	    		 		pw.println(trameok);//" <Nom de la commande><espace>TRAME_OK\n"
	    		 		robot.pince.controlPince(Pince.Action.FERMER);
	    		 		break;
	    		 		
	    		 	case "LEVER_PINCE":
	    		 		pw.println(trameok);//" <Nom de la commande><espace>TRAME_OK\n"
	    		 		robot.pince.controlPince(Pince.Action.LEVER);
	    		 		break;
	    		 		
	    		 		
	    		 		// Pour controler le mouvements du Camera
	    		 	case "VIDEO_START":
	    		 		pw.println(trameok);//" <Nom de la commande><espace>TRAME_OK\n"
	    		 		robot.camera.camaraCapt();
	    		 		break;
	    		 		
	    		 	case "VIDEO_STOP":
	    		 		pw.println(trameok);//" <Nom de la commande><espace>TRAME_OK\n"
	    		 		robot.camera.camaraCapt(); // a faire dans Camera
	    		 		break;
	    		 	
	    		 	case "CAM_GAUCHE":
	    		 		pw.println(trameok);//" <Nom de la commande><espace>TRAME_OK\n"
	    		 		robot.camera.controlMo_Cam(Camera.CAM_Direction.GAUCHE);
	    		 		break;
	    		 		
	    		 	case "CAM_DEVANT":
	    		 		pw.println(trameok);//" <Nom de la commande><espace>TRAME_OK\n"
	    		 		robot.camera.controlMo_Cam(Camera.CAM_Direction.CENTRE);
	    		 		break;
	    		 		
	    		 	case "CAM_DROITE":
	    		 		pw.println(trameok);//" <Nom de la commande><espace>TRAME_OK\n"
	    		 		robot.camera.controlMo_Cam(Camera.CAM_Direction.DROIT);
	    		 		break;
	    		 	
	    		 	// Pour controler les Capteurs
	    		 	case "DETECT":
	    		 		robot.capteur.Capts_IR();
	    		 		break;
	    			case "DIST_US":
	    				pw.println(robot.capteur.Capts_US());
	    		 		break;
	    		 
	    		 	//pour controler le BOUSSOLE:	
	    			case "BOUSSOLE":
	    				pw.println(robot.boussole.Capts_BOUSSOLE());
	    		 		break;
	    		 	
	    		 		//pour controler le BOUSSOLE:	
	    			case "ETAT_BATT":
	    				pw.println(robot.BMS_BATT());
	    		 		break;
	    	 }
	    		 }
	     
		
	}
  public static void main(String[] args) throws Exception
  {
   ServerSocket server;   
   Socket socket;
   BufferedReader br;//br permet de lire les données envoyées par le client.
   PrintWriter pw; //pw permet d’envoyer des données au client.
   String messageRecu;
   RobotPi robot = new RobotPi();
   //192.168.0.2 - 159.31.64.12
   server = new ServerSocket(2009,1,InetAddress.getByName("159.31.64.12"));
  System.out.println( "Attente client ...");
  
   socket = server.accept(); //attente connexion client.
 System.out.println( "Client connecter");
   
   //Retourne un socket qui va permettre de lire les messages (émis par le
   //client) et d’envoyer des messages vers le client.

   //br permet de lire le flux de données envoyé par le client.
   br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

  //pw permet d’envoyer des données au client.
  //true indique que la vidange du buffer d’émission est automatique.
   pw = new PrintWriter(socket.getOutputStream(), true);
   
   while(true) 
    {
     messageRecu = br.readLine(); // lecture de la trame reçue (bloquant)
     
     
     traitCommand(messageRecu,robot,pw);
	 System.out.println("\nMR: "+ messageRecu);
    
    }//fin while
   
   
 }// fin main()
}//fin classe
