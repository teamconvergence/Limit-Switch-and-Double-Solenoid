package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static int p_xbox1 = 0;

	// DriveTrain Motors
	public static int p_leftDrive1 = 0;
	// public static int p_leftDrive2 = 1;
	public static int p_rightDrive1 = 1;
	// public static int p_rightDrive2 = 3;
	public static int p_solenoidIn = 3;
	public static int p_solenoidOut = 4;
	public static int p_compressor = 0;
	public static int p_limitleft = 0;// IMPORTANT THESE ARE PLACE HOLDER VARIABLES. RESET THEM.
	public static int p_limitright = 1;

}