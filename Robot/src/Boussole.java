import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;
import com.pi4j.util.Console;

public class Boussole {
	public Constantes constant;
	
	/*public static final byte CMD_ROBOT_ZERO = (byte)0x00;
	// PIC_BOUSSOLE_Commands
    public static final byte CMD_BOUSSOLE = (byte)0x60;*/
	
    final Console console; //pour l'affichage dans le terminal
	I2CBus i2c ;// definition d'un variable qui represent le source pour la comunication I2C
	I2CDevice device ;//
	public Boussole(I2CDevice _device) throws IOException, InterruptedException, UnsupportedBusNumberException {
			console = new Console();
			this.device = _device;
			this.constant = new Constantes();
			   
		   }
	 // Function pour controler la Boussole:
    public String Capts_BOUSSOLE() throws IOException, InterruptedException {
		 
    	byte[] buffer_BOUSSOLE= new byte[2];
    	
    	console.println("... LIRE LA BOUSSOLE");
    	device.write(constant.CMD_BOUSSOLE);
        device.write(constant.CMD_ROBOT_ZERO);
        Thread.sleep(20);
		device.read(buffer_BOUSSOLE,0,2);
		
		float DEGREE =((((int)buffer_BOUSSOLE[0] & 0xFF)<<8) | ((int)buffer_BOUSSOLE[1] & 0xFF))/10;
		
		return("BOUSSOLE "+ DEGREE);

		}
    /*public static void main(String[] args) throws IOException, InterruptedException, UnsupportedBusNumberException {
    	
    	Boussole boussole = new Boussole();
    	boussole.console.title("<-- The RobotPi Project -->");
        // allow for user to exit program using CTRL-C
        while(true) {
        	boussole.Capts_BOUSSOLE();
       
        }
	}*/

}
