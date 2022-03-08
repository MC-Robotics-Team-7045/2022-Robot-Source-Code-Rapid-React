/*----------------------------------------------------------------------------*/
/* Copyright (c) 2022 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
//import frc.robot.commands.IndexerCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
//import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
//import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

/**
 * Add your docs here.
 */
public class Indexer extends SubsystemBase {
  private final PWMVictorSPX indexerMotor = new PWMVictorSPX(Constants.MOTOR_INDEXER_PORT);

  // Light Beam Switches are TRUE when no ball is present
  public DigitalInput swShooter = new DigitalInput(Constants.SWITCH_S1_SHOOTER_DIO_PORT);
  public DigitalInput swMidIndex = new DigitalInput(Constants.SWITCH_S2_MID_INDEX_DIO_PORT);
  // public DigitalInput swStartIndex = new
  // DigitalInput(Constants.SWITCH_S3_START_INDEX_DIO_PORT);
  public DigitalInput swIntake = new DigitalInput(Constants.SWITCH_S4_INTAKE_DIO_PORT);

  public static int balls = Constants.kInitMagazineBalls;
  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Indexer() {
    super();

    // LiveWindow
    // addChild("Indexer Motor", indexerMotor);
    if (Constants.kDebug) {
      Shuffleboard.selectTab("Shooter");
      Shuffleboard.getTab("Shooter").add("Indexer Motor", indexerMotor).withPosition(1, 1);
      Shuffleboard.getTab("Shooter").add("Switch-Shooter", swShooter).withPosition(0, 0);
      Shuffleboard.getTab("Shooter").add("Switch-Mid", swMidIndex).withPosition(0, 1);
      Shuffleboard.getTab("Shooter").add("Switch-Intake", swIntake).withPosition(0, 2);
    }
  }

  public void init() {
    SmartDashboard.putNumber("Ball Count", balls);
    SmartDashboard.putBoolean("SW-SHOOTER", swShooter.get());
    SmartDashboard.putBoolean("SW-MidINDEX", swMidIndex.get());
    SmartDashboard.putBoolean("SW-INTAKE", swIntake.get());
    SmartDashboard.putNumber("Indexer Motor", indexerMotor.get());

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    SmartDashboard.putNumber("Ball Count", balls);
    SmartDashboard.putBoolean("SW-SHOOTER", swShooter.get());
    SmartDashboard.putBoolean("SW-MidINDEX", swMidIndex.get());
    SmartDashboard.putBoolean("SW-INTAKE", swIntake.get());
    SmartDashboard.putData("Indexer Motor", indexerMotor);

  }

  // Start the Indexer
  public void start() {
    indexerMotor.set(Constants.kIndexerSpeed);
  }

  // Reverse the Indexer motor
  public void reverse() {
    indexerMotor.set(-Constants.kIndexerSpeed);
  }

  // Stops the Indexer motor
  public void stop() {
    indexerMotor.set(0);

  }

  /**
   * Return true when the indexer motor is running
   */
  public boolean isRunning() {

    if (Math.abs(indexerMotor.get()) > 0.0) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isMagFUll() {
    if (balls == Constants.kMaxMagazineBalls) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isMagEMPTY() {
    if (balls == 0) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isShooterPrimed() {
    if (swShooter.get() == true) { // if true, there is no ball
      return false;
    } else {
      return true;
    }
  }

}
