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
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

/**
 * Add your docs here.
 */
public class DriveTrain extends SubsystemBase {
  private final WPI_VictorSPX motorLeftFront = new WPI_VictorSPX(Constants.CAN_MOTOR_LEFT_FRONT_PORT);
  private final WPI_VictorSPX motorLeftRear = new WPI_VictorSPX(Constants.CAN_MOTOR_LEFT_REAR_PORT); //Set as follower
  private final WPI_VictorSPX motorRightFront = new WPI_VictorSPX(Constants.CAN_MOTOR_RIGHT_FRONT_PORT);
  private final WPI_VictorSPX motorRightRear = new WPI_VictorSPX(Constants.CAN_MOTOR_RIGHT_REAR_PORT); //Set as follower

  private final DifferentialDrive drive = new DifferentialDrive(motorLeftFront, motorRightFront);

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public DriveTrain() {
    super();

    // LiveWindow
    addChild("T-Drive", drive);

  }

  public void init() {
    //Factory Default all hardware to prevent unexpected behaviour
    motorLeftFront.configFactoryDefault();
    motorLeftRear.configFactoryDefault();
    motorRightFront.configFactoryDefault();
    motorRightRear.configFactoryDefault();
    //Set rear motors to FOLLOW front motors
    motorLeftRear.follow(motorLeftFront);
    motorRightRear.follow(motorRightFront);
    //Set Neutral Modes to BRAKE
    motorLeftFront.setNeutralMode(NeutralMode.Brake);
    motorLeftRear.setNeutralMode(NeutralMode.Brake);
    motorRightFront.setNeutralMode(NeutralMode.Brake);
    motorRightRear.setNeutralMode(NeutralMode.Brake);

    motorLeftFront.setInverted(false);
    motorRightFront.setInverted(true);
    motorLeftRear.setInverted(InvertType.FollowMaster);
    motorRightRear.setInverted(InvertType.FollowMaster);

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
