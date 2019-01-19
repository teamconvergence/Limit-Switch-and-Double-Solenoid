/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public static DriveTrain drivetrain = new DriveTrain();
  public static OI oi = new OI();
  public static LimelightCamera limelight = new LimelightCamera();

  //constants for autonoumous driving
  public static float kpAim = 0.04f;
  public static float kpDistance = -0.07f;
  public static float minAimCommand = 0.05f;

  //Declaration of USB Camera and Autonomous Command
  UsbCamera cam;
  public static DoubleSolenoidSubsystem sol = new DoubleSolenoidSubsystem();
  public static LimitSwitch limit = new LimitSwitch();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    //Initialize and start the USB Camera
    cam = CameraServer.getInstance().startAutomaticCapture();
    //Set the max FPS of the camera at 60
    cam.setFPS(60);
    sol.reverse();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    // Arcade Drive
    if(oi.isAButtonPressed()&& limelight.isTargetVisible()){
      double xAdjust = 0.0;
      final double minSpeedX = 0.4;
      final double minSpeedY = 0.4;
      final double xZone = 20.0;
      final double yZone = -10;
      double ySpeed = 0.0;
      
      double currentX = limelight.getX();

      if(currentX>=xZone||-currentX>=xZone){
        xAdjust = kpAim*currentX;
      }
      else if(currentX>0&&currentX<xZone){
        xAdjust = minSpeedX;
      }
      else if(currentX<0&&currentX>-xZone){
        xAdjust = -minSpeedX;
      }
      else{
        xAdjust = 0;
      }
      double currentY = limelight.getY();
      if(currentY<yZone){
        ySpeed = kpDistance*currentY;
      }
      else if(currentY>=yZone&&currentY<0){
        ySpeed = minSpeedY;
      }
      else if(currentY>0){
        ySpeed=-minSpeedY;
      }
      else {
        ySpeed = 0;
      }

      drivetrain.arcadeDrive(ySpeed, xAdjust);

      // if(kpDistance*limelight.getY()>0.2){
      //   drivetrain.arcadeDrive(0.2, xAdjust);
      // }
      // else if(kpDistance*limelight.getY()<-0.2){
      //   drivetrain.arcadeDrive(-0.2, xAdjust);
      // }
      // else {
      //   drivetrain.arcadeDrive(kpDistance*limelight.getY(), xAdjust);
      // }
      // IDEAL TURN RATE 0.4 
      // IDEAL SPEED 0.4

      //ANY POSITIVE Y CAMERA VALUE WE WANT SPEED AT -0.4
      // ANY NEGATIVE Y CAMERA VALUE GREATER THAN -10 WILL BE 0.4
      // ANYTHING LESS THAN -10 ON THE Y AXIS WILL BE A HIGHER SPEED

      //FOR X AXIS ABOVE |20| GET COMPUTED SPEED
      //FOR X AXIS BELOW |20| SET 0.4
      // drivetrain.arcadeDrive(kpDistance*limelight.getY(), xAdjust);
      // drivetrain.arcadeDrive(0.4, 0);
        
    }
    else{
      if (Math.abs(oi.getLeftJoystick()) > .05 || Math.abs(oi.getRightJoystick()) > .05) {
        // THIS NEEDS TO BE CHANGED. IT IS TEST CODE FOR LIMITS.
       if(limit.leftLimitPosition()&&limit.rightLimitPosition()){
         drivetrain.arcadeDrive(-oi.getLeftJoystick(), oi.getRightJoystick());
       }
      } 
      else {
        drivetrain.arcadeDrive(0, 0);
      }
    }
    limelight.readCamera();
    limelight.updateDashboard();

    if(oi.isRightBumper()){
      sol.reverse();
    }
    else if(oi.isLeftBumper()){
      sol.forward();
    }

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}