import java.io.IOException;
import java.lang.*;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;
import com.pi4j.util.Console;


public class Camera {
	public Constantes constant;
	
	 /*// PIC_MOV_CAM_Commands
    public static final byte CMD_CAM_TOURNER_G = (byte)0x30;
    public static final byte CMD_CAM_CENTRER = (byte)0x32;
    public static final byte CMD_CAM_TOURNER_D = (byte)0x31;
    public static final byte CMD_ROBOT_ZERO = (byte)0x00;*/
    
    final Console console; //pour l'affichage dans le terminal
    I2CBus i2c ;// definition d'un variable qui represent le source pour la comunication I2C
   	I2CDevice device ;//
	
	public Camera(I2CDevice _device)throws IOException, InterruptedException, UnsupportedBusNumberException {
    	console = new Console();
    	this.device = _device;
    	this.constant = new Constantes();
		
	}
	
	 //fonction pour fair des capture des images du robot
    public static void camaraCapt() throws IOException {
    	Runtime.getRuntime().exec(
    			//"sudo raspistill -v -t 0 -tl 500 -w 300 -h 200 -o /var/www/html/vueRobot.jpg"
    			"sudo  raspivid -v -t 0 -tl 500 -w 300 -h 200 -o /var/www/html/vueRobot.jpg"
    			);
    }
    // Function pour controler le mouvements du Camera
    public void controlMo_Cam(CAM_Direction direction) throws IOException, InterruptedException {
        
        
        switch (direction) {
            case GAUCHE:
            	console.println("... CAMERA A TOURNER GAUCHE .. ");
                device.write(constant.CMD_CAM_TOURNER_G);// Commande spécifique pour déplacer le Camera vers gauche
                device.write(constant.CMD_ROBOT_ZERO); 
                break;
            case CENTRE:
            	console.println("... CAMERA EST CENTRER ...");
                device.write(constant.CMD_CAM_CENTRER);// Commande spécifique pour déplacer le Camera vers centre
                device.write(constant.CMD_ROBOT_ZERO);
                break;
            case DROIT:
            	console.println("... CAMERA A TOURNER DROIT ");
                device.write(constant.CMD_CAM_TOURNER_D);// Commande spécifique pour déplacer le Camera vers droit
                device.write(constant.CMD_ROBOT_ZERO);
                break;
            default:
                throw new IllegalArgumentException("Invalid Camera direction provided.");
        }

    }

    // Enum to represent movement directions
    public enum CAM_Direction {
        GAUCHE, CENTRE, DROIT
    }
	/*public static void main(String[] args) throws IOException {
		Camera cam = new Camera();
		cam.camaraCapt();
		while(true);
	}*/
	

}
