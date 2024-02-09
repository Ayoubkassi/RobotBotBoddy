import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;
import com.pi4j.util.Console;


public class Capteurs {
	public Constantes constant;
	
	/*public static final byte CMD_ROBOT_ZERO = (byte)0x00;
	// PIC_USs_Commands
    public static final byte CMD_CAPT_US_DIST = (byte)0x40;
    
    // PIC_IRs_Commands
    public static final byte CMD_IR_DETECT = (byte)0x50;*/
    
    final Console console; //pour l'affichage dans le terminal
	I2CBus i2c ;// definition d'un variable qui represent le source pour la comunication I2C
	I2CDevice device ;//
	public Capteurs(I2CDevice _device) throws IOException, InterruptedException, UnsupportedBusNumberException {
		console = new Console();
		   
		this.device = _device;
		this.constant = new Constantes();
	   }
	// Function pour controler les Capteurs
    public String Capts_US() throws IOException, InterruptedException {
		 
    	byte[] buffer=new byte[3];
    	String[] US_DIRECTION = new String[]{"GAUCHE","CENTRE","DROIT"};
    	
    	console.println("... les donner du capteur_US sont :"); // c'est un message qui vas etre afficher sur le terminal pour indiquer que les donner suivants sont celle du capteur US
    	device.write(constant.CMD_CAPT_US_DIST);
        device.write(constant.CMD_ROBOT_ZERO);
        Thread.sleep(505);
		device.read(buffer,0,3);
		
		return("DIST_US "+buffer[0]+" "+buffer[1]+" "+buffer[2]);
		}
    
    public void Capts_IR() throws IOException, InterruptedException {
		 
    	console.println("... les donner du capteur_IR sont :");// c'est un message qui vas etre afficher sur le terminal pour indiquer que les donner suivants sont celle des capteurs IR
    	device.write(constant.CMD_IR_DETECT);
        device.write(constant.CMD_ROBOT_ZERO);
		byte bytesRead = (byte) device.read();
		String[] IR_DIRECTION = new String[]{"IR_AVG","IR_AVD","IR_ARG","IR_ARD"};
		Map<String, Boolean> Capteurs_IR = new HashMap<>();
		Capteurs_IR.put("IR_AVG", ((bytesRead>>3) & 0x01)==1);
		Capteurs_IR.put("IR_AVD", ((bytesRead>>2) & 0x01)==1);
		Capteurs_IR.put("IR_ARG", ((bytesRead>>1) & 0x01)==1);
		Capteurs_IR.put("IR_ARD", (bytesRead & 0x01)==1);
		
		for (int i=0; i<4 ; i++) {
			console.println("DETECT "+IR_DIRECTION[i] +" : "+ Capteurs_IR.get(IR_DIRECTION[i]));
		}
		}
    
    /*public static void main(String[] args) throws IOException, InterruptedException, UnsupportedBusNumberException {
    	
    	Capteurs capter = new Capteurs();
    	capter.console.title("<-- The RobotPi Project -->");
        // allow for user to exit program using CTRL-C
        while(true) {
        	capter.Capts_IR();
        	capter.Capts_US();
       
        }
	}*/
}
