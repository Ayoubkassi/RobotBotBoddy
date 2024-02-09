import java.io.IOException;
import java.util.Arrays;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;
import com.pi4j.platform.PlatformAlreadyAssignedException;
import com.pi4j.util.Console;

public class I2c_Communication {
	// Pic I2C address
    public static final int Pic_ADDR = 0x70; // address pin not connected (FLOATING)
   
    // PIC_Move_Commands
     
    	
    public static final byte CMD_ROBOT_AVANCER = (byte)0x10;
    public static final byte CMD_ROBOT_STOP = (byte)0x11;
    public static final byte CMD_ROBOT_TOURNER_D = (byte)0x12;
    public static final byte CMD_ROBOT_TOURNER_G = (byte)0x13;
    public static final byte CMD_ROBOT_RECULER = (byte)0x14;

 // PIC_Pince_Commands

    public static final byte CMD_PINCE_OUVRIR = (byte)0x20;
    public static final byte CMD_PINCE_FERMER = (byte)0x21;
    public static final byte CMD_PINCE_LEVER = (byte)0x22;
    public static final byte CMD_PINCE_BAISSER = (byte)0x23;
    public static final byte CMD_PINCE_PARAM = (byte)0x00;
    
	

}
