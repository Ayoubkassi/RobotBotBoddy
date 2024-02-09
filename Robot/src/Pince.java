import java.io.IOException;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;
import com.pi4j.util.Console;

public class Pince {
	public Constantes constant;
	
	 // PIC_Pince_Commands
    public static final byte CMD_PINCE_OUVRIR = (byte)0x20;
    public static final byte CMD_PINCE_FERMER = (byte)0x21;
    public static final byte CMD_PINCE_LEVER = (byte)0x22;
    public static final byte CMD_PINCE_BAISSER = (byte)0x23;
    public static final byte CMD_PINCE_PARAM = (byte)0x00;
    
    final Console console; //pour l'affichage dans le terminal
    I2CBus i2c ;// definition d'un variable qui represent le source pour la comunication I2C
   	I2CDevice device ;//
    
    public Pince(I2CDevice _device)throws IOException, InterruptedException, UnsupportedBusNumberException {
    	console = new Console();
    	this.device = _device;
    	this.constant = new Constantes();
    }
 public void controlPince(Action action ) throws IOException, InterruptedException {
        
        
        switch (action) {
            case OUVRIR:
            	console.println("... PINCE OUVRIR");
                device.write(CMD_PINCE_OUVRIR);
                device.write(CMD_PINCE_PARAM);
                break;
            case FERMER:
            	console.println("... PINCE FERMER");
                device.write(CMD_PINCE_FERMER);
                device.write(CMD_PINCE_PARAM);
                break;
            case LEVER:
            	console.println("... PINCE LEVER");
                device.write(CMD_PINCE_LEVER);
                device.write(CMD_PINCE_PARAM);
                break;
            case BAISSER:
            	console.println("... PINCE BAISSER");
                device.write(CMD_PINCE_BAISSER);
                device.write(CMD_PINCE_PARAM);
                break;
            default:
                throw new IllegalArgumentException("Invalid direction provided.");
        }

    }

    // Enum to represent movement directions
    public enum Action {
        OUVRIR, FERMER, BAISSER, LEVER
    }
    
    /*public static void main(String[] args) throws IOException, InterruptedException, UnsupportedBusNumberException {
    	
		Pince pince = new Pince();
		pince.console.title("<-- The RobotPi Project -->");
        // allow for user to exit program using CTRL-C
		pince.console.promptForExit();
		pince.controlPince(Action.OUVRIR);
		while(true);
	}*/
}
