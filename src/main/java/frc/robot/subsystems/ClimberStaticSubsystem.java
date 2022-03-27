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
//import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.Constants;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Add your docs here.
 */
public class ClimberStaticSubsystem extends SubsystemBase {
  private final CANSparkMax climberMotor = new CANSparkMax(Constants.CAN_MOTOR_STATIC_CLIMBER_PORT, MotorType.kBrushless);
  //private final WPI_TalonSRX climberMotor = new WPI_TalonSRX(Constants.CAN_MOTOR_STATIC_CLIMBER_PORT);
  // public DigitalInput upperLimit = new
  // DigitalInput(Constants.climber_UPPER_LIMIT_DIO_PORT);
  // public DigitalInput lowerLimit = new
  // DigitalInput(Constants.climber_LOWER_LIMIT_DIO_PORT);
  public AnalogInput climberPot = new AnalogInput(Constants.CLIMBER_STATIC_POT_PORT);

  // public Counter m_LIDAR = new Counter(9);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public ClimberStaticSubsystem() {
    super();

    // LiveWindow
    // addChild("climber Motor", climberMotor);
    if (Constants.kDebug) {
      Shuffleboard.selectTab("Shooter");
      //Shuffleboard.getTab("Shooter").add("Climber Motor", climberMotor).withPosition(3, 1);
      //Shuffleboard.getTab("Shooter").add("Active Climber Voltage", climberPot).withPosition(3, 2);
    }
  }

  public void init() {
    //Spark MAX
    climberMotor.restoreFactoryDefaults();
    climberMotor.setInverted(true);
    climberMotor.setIdleMode(IdleMode.kBrake);
 

    //TALONX SRX
     //climberMotor.configFactoryDefault();
    //climberMotor.setInverted(false);
    //climberMotor.setIdleMode(IdleMode.kBrake);


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    SmartDashboard.putData("Static Climber String", climberPot);
    //SmartDashboard.putNumber("Static Climber Motor", climberMotor.getAppliedOutput());  //Spark Max
    //SmartDashboard.putData("Static Climber Motor", climberMotor);  //Talon SRX
  }

  // Start the climber motor
  public void up() {
    climberMotor.set(Constants.kClimberStaticSpeed);
  }

  // Reverse the climber motor
  public void down() {
    climberMotor.set(-Constants.kClimberStaticSpeed);
  }

  // Reverse the climber motor
  public void hold() {
    climberMotor.set(-Constants.kClimberStaticHoldSpeed);
  }

  // Stops the intake motor
  public void stop() {
    climberMotor.set(0);

  }

  public boolean isRunning() {
    double speed = climberMotor.get();
    if (Math.abs(speed) > 0) {
      return true;
    } else {
      return false;
    }
  }

  public double climberVoltage() {
    return climberPot.getVoltage();
  }

}
