
import java.io.IOException;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;
import com.pi4j.util.Console;





public class Motor {
	public Constantes constant;
	
	/* // PIC_Move_Commands	
    public static final byte CMD_ROBOT_AVANCER = (byte)0x10;
    public static final byte CMD_ROBOT_STOP = (byte)0x11;
    public static final byte CMD_ROBOT_TOURNER_D = (byte)0x12;
    public static final byte CMD_ROBOT_TOURNER_G = (byte)0x13;
    public static final byte CMD_ROBOT_RECULER = (byte)0x14;
    public static final byte CMD_ROBOT_ZERO = (byte)0x00;*/
	    
	final Console console; //pour l'affichage dans le terminal
	I2CBus i2c ;// definition d'un variable qui represent le source pour la comunication I2C
	I2CDevice device ;//
	public Motor(I2CDevice _device) throws IOException, InterruptedException, UnsupportedBusNumberException {
		console = new Console();
		this.device = _device;
		this.constant = new Constantes();
		   
	   }
	   	
	 // Function pour controler les mouvements du robot
	    public void controlMotor(Direction direction, int speed) throws IOException, InterruptedException {
	        // Validate the speed parameter
	        if (speed < 0 || speed > 255) {
	            throw new IllegalArgumentException("Speed should be a byte value between 0 and 255.");
	        }

	        byte speedByte = (byte) speed;
	        
	        switch (direction) {
	            case AVANCER:
	            	console.println("... ROBOT AVANCER");
	                device.write(constant.CMD_ROBOT_AVANCER);
	                device.write(speedByte); // Commande spécifique pour déplacer le robot vers l'avant
	                
	                break;
	            case RECULER:
	            	console.println("... ROBOT RECULER");
	                device.write(constant.CMD_ROBOT_RECULER);
	                device.write(speedByte);// Commande spécifique pour déplacer le robot vers l'arrière
	                
	                break;
	            case ARRET:
	            	console.println("... ROBOT ARRETER");
	                device.write(constant.CMD_ROBOT_STOP);
	                device.write(constant.CMD_ROBOT_ZERO);// Commande spécifique pour arrêter le mouvement
	           	    break;
	           	    
	            case GAUCHE:
	            	console.println("... ROBOT GAUCHE");
	                device.write(constant.CMD_ROBOT_TOURNER_G);
	                device.write(speedByte);// Commande spécifique pour tourner à gauche
	                
	                break;
	            case DROIT:
	            	console.println("... ROBOT DROIT");
	                device.write(constant.CMD_ROBOT_TOURNER_D);
	                device.write(speedByte);// Commande spécifique pour tourner à droite
	                
	                break;
	            default:
	                throw new IllegalArgumentException("Invalid direction provided.");
	        }

	    }

	    // Enum to represent movement directions
	    public enum Direction {
	        AVANCER, RECULER, ARRET, GAUCHE, DROIT
	    }
	    
	   /* public static void main(String[] args) throws IOException, InterruptedException, UnsupportedBusNumberException {
	    	
	    	Motor motor = new Motor();
	    	motor.console.title("<-- The RobotPi Project -->");
	        // allow for user to exit program using CTRL-C
	    	motor.console.promptForExit();
	    	motor.controlMotor(Direction.FORWARD);
			while(true);
		}*/

}
