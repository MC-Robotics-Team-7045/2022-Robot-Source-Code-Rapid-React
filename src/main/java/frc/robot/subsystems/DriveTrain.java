/*----------------------------------------------------------------------------*/
/* Copyright (c) 2022 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Add your docs here.
 */
public class DriveTrain extends SubsystemBase {
  private final CANSparkMax motorLeftFront = new CANSparkMax(Constants.CAN_MOTOR_LEFT_FRONT_PORT, MotorType.kBrushless);
  private final CANSparkMax motorLeftRear = new CANSparkMax(Constants.CAN_MOTOR_LEFT_REAR_PORT, MotorType.kBrushless); //Set as follower
  private final CANSparkMax motorRightFront = new CANSparkMax(Constants.CAN_MOTOR_RIGHT_FRONT_PORT, MotorType.kBrushless);
  private final CANSparkMax motorRightRear = new CANSparkMax(Constants.CAN_MOTOR_RIGHT_REAR_PORT, MotorType.kBrushless); //Set as follower

  private DifferentialDrive drive; 

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public DriveTrain() {
    super();
    
    motorLeftFront.restoreFactoryDefaults();
    motorLeftRear.restoreFactoryDefaults();
    motorRightFront.restoreFactoryDefaults();
    motorRightRear.restoreFactoryDefaults();
    //Set rear motors to FOLLOW front motors
    motorLeftRear.follow(motorLeftFront);
    motorRightRear.follow(motorRightFront);
    //Set Neutral Modes to BRAKE
    motorLeftFront.setIdleMode(IdleMode.kBrake);
    motorLeftRear.setIdleMode(IdleMode.kBrake);
    motorRightFront.setIdleMode(IdleMode.kBrake);
    motorRightRear.setIdleMode(IdleMode.kBrake);

    motorLeftFront.setInverted(true);
    motorRightFront.setInverted(false); //arcade drive takes care of inversion?
    //motorLeftRear.setInverted(false; //set as follower
    //motorRightRear.setInverted(true);
    

    drive = new DifferentialDrive(motorLeftFront, motorRightFront);
    // LiveWindow
    addChild("T-Drive", drive);

  }

  public void init() {
    //Factory Default all hardware to prevent unexpected behaviour
   

    drive.arcadeDrive(0, 0);
    drive.setSafetyEnabled(false);

  }

  public void stop() {

    drive.arcadeDrive(0, 0);

  }

  @Override
  public void periodic() {

    drive.feed();
    ;

  }

  // @Override
  // public void initDefaultCommand() {
  // Set the default command for a subsystem here.
  // setDefaultCommand(manualDrive);
  // }

  public void manualDrive(double move, double turn, double throttle) {
    throttle = (throttle + 1.0) / 2.0; // Shift throttle value from [-1,1] to [0,1]
    move = move * throttle;
    turn = turn * throttle * Constants.kTurnScaling;

    // Apply limits. Note that motors square inputs. e.g. 0.8 limit results in 0.64
    // speed.
    if (Math.abs(move) > Constants.kMoveLimit)
      move = Constants.kMoveLimit * Math.signum(move);
    if (Math.abs(turn) > Constants.kTurnLimit)
      turn = Constants.kTurnLimit * Math.signum(turn);

    // Check minimum thresholds to avoid motor creep/deadzones

    if (Math.abs(move) < Constants.kMoveMinThreshold)
      move = 0;
    if (Math.abs(turn) < Constants.kTurnMinThreshold)
      turn = 0;

    drive.arcadeDrive(move, turn);

    /*
     * Print to RioLog
     * System.out.print("manualDrive  ");
     * System.out.print(move);
     * System.out.print(" ");
     * System.out.print(turn);
     * System.out.print(" ");
     * System.out.println(throttle);
     */

    SmartDashboard.putNumber("Move", move);
    SmartDashboard.putNumber("Turn", turn);
    SmartDashboard.putNumber("Throttle", throttle);

  } // end manualDrive

  // targetDrive ignores throttle for turn set by targetting system, but still
  // applies to move
  // just in case locking onto target while driving.
  public void targetDrive(double move, double turn, double throttle) {
    // throttle = (throttle + 1.0) / 2.0; //Shift throttle value from [-1,1] to
    // [0,1]
    // move = move * throttle; //let targetting system control the move speed
    // irrespective of throttle
    // turn = turn * throttle; //Let targetting system control the turn speed
    // irrespective of throttle

    // Apply limits. Note that motors square inputs. e.g. 0.8 limit results in 0.64
    // speed.
    if (Math.abs(move) > Constants.kMoveLimit)
      move = Constants.kMoveLimit * Math.signum(move);
    if (Math.abs(turn) > Constants.kTurnLimit)
      turn = Constants.kTurnLimit * Math.signum(turn);

    // Check minimum thresholds to avoid motor creep/deadzones
    // MINIMUMS TAKEN CARE OF IN AUTO TARGETING FOR LIMELIGHT
    // if (Math.abs(move) < Constants.kMoveMinThreshold) move = 0;
    // if (Math.abs(turn) < Constants.kTurnMinThreshold) turn = 0;

    drive.arcadeDrive(move, turn);

    /*
     * Print to RioLog
     * System.out.print("targetDrive  ");
     * System.out.print(move);
     * System.out.print(" ");
     * System.out.print(turn);
     * System.out.print(" ");
     * System.out.println(throttle);
     */

    SmartDashboard.putNumber("Move", move);
    SmartDashboard.putNumber("Turn", turn);
    SmartDashboard.putNumber("Throttle", throttle);

  } // end targetDrive

  public void log() {

    // debug to Shuffleboard
    if (Constants.kDebug) {
      // final ShuffleboardTab driveTrainTab = Shuffleboard.getTab("DriveTrain");
      // driveTrainTab.add("Tank Drive", drive );

      // NetworkTableEntry moveWidget, turnWidget;
      // moveWidget = driveTrainTab.add("Move",0.0).withWidget("Text
      // VIew").getEntry();
      // turnWidget = driveTrainTab.add("Turn",0.0).withWidget("Text
      // VIew").getEntry();

      // moveWidget.setNumber(move);
      // turnWidget.setNumber(turn);

    } // end debug
  }

}
