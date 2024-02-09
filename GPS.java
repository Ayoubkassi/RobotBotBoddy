import com.pi4j.io.serial.*;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GPS implements SerialDataEventListener{
		
		private Serial serial;
		public String trame="";
		public GPS() throws IOException {
	    	
	        this.serial = null;
			// Initialisation 
	    	this.serial= SerialFactory.createInstance();
	       // SerialDataEvent event = SerialDataEvent(serial);
	       
	        
	        serial.open(Serial.DEFAULT_COM_PORT,Baud._9600,DataBits._8,Parity.NONE,StopBits._1,FlowControl.NONE);  // Ouverture du port série par défaut à 9600 bauds
	        
	      
	        serial.addListener((SerialDataEventListener)this);
	    }
	    
		public void dataReceived(SerialDataEvent event) {

	        // NOTE! - It is extremely important to read the data received from the
	        // serial port.  If it does not get read from the receive buffer, the
	        // buffer will continue to grow and consume memory.

	        // print out the data received to the console
	        try {
	        	trame =trame + event.getAsciiString();
	        	System.out.print( trame);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    
	    }
	    
	    private void processGPGGA(String gpggaSentence) {
	       
	        String[] fields = gpggaSentence.split(",");

	        if (fields[0].equals("$GPGGA")) {
	        	
	        	 System.out.println("le Trame est : " + gpggaSentence);
	        	 
	            System.out.println("Index de ligne : " + fields[0]);
	            System.out.println("Heure : " + fields[1]);
	            System.out.println("Latitude : " + fields[2] + " " + fields[3]);
	            System.out.println("Longitude : " + fields[4] + " " + fields[5]);
	            System.out.println("Qualité de fixation : " + fields[6]);
	            System.out.println("Nombre de satellites : " + fields[7]);
	            System.out.println("Dilution horizontale de la précision : " + fields[8]);
	            System.out.println("Altitude : " + fields[9] + " " + fields[10]);
	            System.out.println("Séparation géoïde : " + fields[12] + " " + fields[13]);
	            System.out.println("position : " + fields[12] + " " + fields[13]);
	            System.out.println("------------------------------");}
	        }
	    
	    public void receiveData() {
	        // Continuous data reception
	        System.out.println("Listening for GPS data. Press Enter to exit.");

	        try {
	            while (System.in.available() == 0 ) {
	            	if(extractGPGGA(trame)!= null) {
	            	trame=extractGPGGA(trame);
	            	processGPGGA(trame);
	            	}
	            	
	             
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            // Close the serial port
	            trame="";
	            System.out.println("Finished listening for GPS data.");
	        }
	    }
	    
	    public static String extractGPGGA(String data) {
	        // Define the pattern to match the desired string
	        Pattern pattern = Pattern.compile("\\$GPGGA.*?\\*");
	        Matcher matcher = pattern.matcher(data);

	        // Check if the pattern is found
	        if (matcher.find()) {
	            // Extract and return the matched string
	            return matcher.group();
	        } else {
	            // Return null if no match is found
	            return null;
	        }
	    }
	    
	    
	    public static void main(String[] args) throws IOException {
	    	
	        
	    	GPS gps = new GPS();

	    	gps.receiveData();
	    	
	    }
	    
	}


