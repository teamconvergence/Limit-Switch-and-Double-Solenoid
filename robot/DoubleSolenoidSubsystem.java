/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import com.ctre.phoenix.motorcontrol.ControlMode;


/**
 * Add your docs here.
 */
public class DoubleSolenoidSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Compressor compressor = new Compressor(RobotMap.p_compressor);
  public DoubleSolenoid sol = new DoubleSolenoid(RobotMap.p_solenoidIn,RobotMap.p_solenoidOut);
  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    reverse();
    
  }
  public void reverse(){
    compressor.enabled();
    sol.set(DoubleSolenoid.Value.kReverse);
  }
  public void forward(){
    sol.set(DoubleSolenoid.Value.kForward);
  }
  public void off(){
    sol.set(DoubleSolenoid.Value.kOff);
  }

}
