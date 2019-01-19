package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	// Controllers and button boxes
	Joystick xbox1 = new Joystick(RobotMap.p_xbox1);

	// xbox1 buttons
	JoystickButton a = new JoystickButton(xbox1, 1);
	JoystickButton b = new JoystickButton(xbox1, 2);
	JoystickButton x = new JoystickButton(xbox1, 3);
	JoystickButton y = new JoystickButton(xbox1, 4);
	JoystickButton lb = new JoystickButton(xbox1, 5);
	JoystickButton rb = new JoystickButton(xbox1, 6);
	JoystickButton back = new JoystickButton(xbox1, 7);
	JoystickButton start = new JoystickButton(xbox1, 8);

	public OI() {

	}

	public double getLeftJoystick() {
		return xbox1.getRawAxis(1);
	}

	public double getRightJoystick() {
		return xbox1.getRawAxis(0);
	}
	public boolean isAButtonPressed(){
		return xbox1.getRawButton(1);
	}
	public boolean isRightBumper(){
		return xbox1.getRawButton(6);
	}
	public boolean isLeftBumper(){
		return xbox1.getRawButton(5);
	}


}