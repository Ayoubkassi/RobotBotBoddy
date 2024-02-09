import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.lang.*;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;
import com.pi4j.util.Console;
import com.pi4j.wiringpi.I2C;




public class SUP500R_GPS 
{
	public Constantes constant;
	public Camera camera;//
	public Motor moteur;
	public Pince pince;
	public Boussole boussole;//
	public Capteurs capteur;//
	public GPS gps;
	
	/*// PIC I2C address
    public static final int PIC_ADDR = 0x70; // address pin not connected (FLOATING)
    public static final int PIC_REG_ID = 0x3FFFFE; // address pin not connected (FLOATING)
    // PIC_BATT_Commands
    public static final byte CMD_BATT = (byte)0x70;*/
   
    
    I2CBus i2c ;// definition d'un variable qui represent le source pour la comunication I2C
	I2CDevice device ;//
	final Console console; //pour l'affichage dans le terminal

    public SUP500R_GPS() throws IOException, InterruptedException, UnsupportedBusNumberException {
    	
		console = new Console();
    	this.i2c = I2CFactory.getInstance(I2CBus.BUS_1);
    	
    	this.device = i2c.getDevice(constant.PIC_ADDR);
    	this.constant = new Constantes();
    	this.camera = new Camera(this.device);
    	this.boussole = new Boussole(this.device);
    	this.capteur = new Capteurs(this.device);
    	//this.gps = new GPS();
    	
    }

    
    // Méthode principale pour tester le contrôle du robot
    
    @SuppressWarnings("static-access")
	public static void main(String[] args) throws UnsupportedBusNumberException, IOException, InterruptedException {
    	
    	SUP500R_GPS robot = new SUP500R_GPS(); 

    	
    	// print program title/header
        
    	robot.console.title("<-- The RobotPi Project -->");

        // allow for user to exit program using CTRL-C
    	robot.console.promptForExit();
    	robot.camera.controlMo_Cam(Camera.CAM_Direction.DROIT);
       
        while(true) {
        	robot.camera.camaraCapt();
        	
        	//robot.capteur.Capts_IR();
        	//robot.boussole.Capts_BOUSSOLE();
        	//robot.gps.receiveData();
        	//robot.BMS_BATT();
        }
        }
    
    public void BMS_BATT() throws IOException, InterruptedException {
		 
    	byte[] buffer_BATT= new byte[2];
    	
    	console.println("... LIRE LA BATTERIE");
    	device.write(constant.CMD_BATT);
        device.write(constant.CMD_ROBOT_ZERO);
        Thread.sleep(1);
		device.read(buffer_BATT,0,2);
		
		int BATT_SUM =(((int)buffer_BATT[0] & 0xFF) <<8) | ((int)buffer_BATT[1] & 0xFF);
		float T_BATT = (float)(BATT_SUM * (12.6/1023.0));
		
		console.println("TENSION DE BATT "+ BATT_SUM);
		
		console.println("TENSION DE BATT "+ T_BATT);
		Thread.sleep(200);

		}
}
