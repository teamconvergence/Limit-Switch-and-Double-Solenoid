/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
/**
 * Add your docs here.
 */
public class LimelightCamera extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  NetworkTableEntry tv = table.getEntry("tv");

  double x = tx.getDouble(0.0);
  double y = ty.getDouble(0.0);
  double area = ta.getDouble(0.0);
  // boolean visible = tv.getBoolean(true);

  public LimelightCamera(){
    // nothing to do 
  }

  public void readCamera(){
    x = tx.getDouble(0.0);
    y = ty.getDouble(0.0);
    area = ta.getDouble(0.0);
    // visible = tv.getBoolean(false);
  }

  public void updateDashboard(){  
    SmartDashboard.putNumber("LimelightX:  ", x);
    SmartDashboard.putNumber("LimelightY:  ", y);
    SmartDashboard.putNumber("LimelightArea:  ", area);
    SmartDashboard.putBoolean("LimelightTargetVisible:  ", isTargetVisible());
  }
  public double getX(){
    return tx.getDouble(0.0);
  }
  public double getY(){
    return ty.getDouble(0.0);
  }
  public double getArea(){
    return ta.getDouble(0.0);
  }
  public boolean isTargetVisible(){
    if(getArea()>0){
      return true;
    }
    return false;
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
