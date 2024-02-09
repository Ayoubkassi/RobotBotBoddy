
public class Constantes {
	// PIC I2C address
    public static final int PIC_ADDR = 0x70; // address pin not connected (FLOATING)
    public static final int PIC_REG_ID = 0x3FFFFE; // address pin not connected (FLOATING)
    
    // PIC_Move_Commands	
    public static final byte CMD_ROBOT_AVANCER = (byte)0x10;
    public static final byte CMD_ROBOT_STOP = (byte)0x11;
    public static final byte CMD_ROBOT_TOURNER_D = (byte)0x12;
    public static final byte CMD_ROBOT_TOURNER_G = (byte)0x13;
    public static final byte CMD_ROBOT_RECULER = (byte)0x14;
    public static final byte CMD_ROBOT_ZERO = (byte)0x00;
    
    // PIC_Pince_Commands
    public static final byte CMD_PINCE_OUVRIR = (byte)0x20;
    public static final byte CMD_PINCE_FERMER = (byte)0x21;
    public static final byte CMD_PINCE_LEVER = (byte)0x22;
    public static final byte CMD_PINCE_BAISSER = (byte)0x23;
    public static final byte CMD_PINCE_PARAM = (byte)0x00;
    
 // PIC_MOV_CAM_Commands
    public static final byte CMD_CAM_TOURNER_G = (byte)0x30;
    public static final byte CMD_CAM_CENTRER = (byte)0x32;
    public static final byte CMD_CAM_TOURNER_D = (byte)0x31;
    
    
    // PIC_USs_Commands
    public static final byte CMD_CAPT_US_DIST = (byte)0x40;
    
    // PIC_IRs_Commands
    public static final byte CMD_IR_DETECT = (byte)0x50;
    
    // PIC_BOUSSOLE_Commands
    public static final byte CMD_BOUSSOLE = (byte)0x60;
    
    // PIC_BATT_Commands
    public static final byte CMD_BATT = (byte)0x70;

}
