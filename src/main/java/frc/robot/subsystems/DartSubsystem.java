/*----------------------------------------------------------------------------*/
/* Copyright (c) 2022 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


/**
 * Add your docs here.
 */
public class DartSubsystem extends SubsystemBase {
  private final WPI_TalonSRX dartMotor = new WPI_TalonSRX(Constants.CAN_MOTOR_DART_PORT);
  
  //If using Hall Effect seonsors
  public DigitalInput upperLimit = new DigitalInput(Constants.DART_UPPER_LIMIT_DIO_PORT);
  public DigitalInput lowerLimit = new DigitalInput(Constants.DART_LOWER_LIMIT_DIO_PORT);

  //If using string potentiometer
  public AnalogInput dartPot = new AnalogInput(Constants.DART_POT_PORT);

  // public Counter m_LIDAR = new Counter(9);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public DartSubsystem() {
    super();

    dartMotor.configFactoryDefault();
    dartMotor.setInverted(false);
    dartMotor.setNeutralMode(NeutralMode.Brake);

    //Connect external Sensors on Dart to TalonSRX Breakout Limit input pins.
    dartMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
    dartMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);

    // LiveWindow
    // addChild("dart Motor", dartMotor);
    if (Constants.kDebug) {
      Shuffleboard.selectTab("Shooter");
      Shuffleboard.getTab("Shooter").add("dart Motor", dartMotor).withPosition(4, 1);
      Shuffleboard.getTab("Shooter").add("dart Voltage", dartPot).withPosition(4, 2);
    }
  }

  public void init() {

    dartMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
    dartMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    SmartDashboard.putData("dart String", dartPot);
    SmartDashboard.putData("dart Motor", (Sendable) dartMotor);

  }

  // Start the dart motor
  public void up() {
    dartMotor.set(Constants.kDartSpeed);
  }

  // Reverse the dart motor
  public void down() {
    dartMotor.set(-Constants.kDartSpeed);
  }

  // Stops the intake motor
  public void stop() {
    dartMotor.set(0);

  }

  public boolean isRunning() {
    double speed = dartMotor.get();
    if (Math.abs(speed) > 0) {
      return true;
    } else {
      return false;
    }
  }

  //Get string potentiometer voltage
  public double dartVoltage() {
    return dartPot.getVoltage();
  }

//If using Limit Switches on Talon SRX breakout board
  public boolean isForwardLimitTriggered(){
    if (dartMotor.isFwdLimitSwitchClosed()==1){
      return true;
    } else {
      return false;
    }
  }

 public boolean isReverseLimitTriggered(){
    if (dartMotor.isRevLimitSwitchClosed()==1){
      return true;
    } else {
      return false;
    }
  } 
  //If using Hall Effect sonsors on Dart
  public boolean isUpperHallLimitTriggered(){
    if (upperLimit.get()==false){
      return true;
    } else {
      return false;
    }
  } 
  public boolean isLowerHallLimitTriggered(){
    if (lowerLimit.get()==false){
      return true;
    } else {
      return false;
    }
  } 
}
