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
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
/**
 * Add your docs here.
 */
public class Intake extends SubsystemBase {
  private final WPI_VictorSPX intakeMotor = new WPI_VictorSPX(Constants.CAN_MOTOR_INTAKE_PORT);

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Intake() {
    super();

    // LiveWindow
    // addChild("Intake Motor", intakeMotor);
    if (Constants.kDebug) {
      Shuffleboard.selectTab("Shooter");
      Shuffleboard.getTab("Shooter").add("Intake Motor", intakeMotor)
          .withPosition(1, 2);
    }
  }
  public void init() {

    intakeMotor.configFactoryDefault();
    intakeMotor.setInverted(false);
    intakeMotor.setNeutralMode(NeutralMode.Brake);

  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    SmartDashboard.putData("Intake Motor", intakeMotor);
  }
  // Start the intake motor
  public void start() {
    intakeMotor.set(Constants.kIntakeSpeed);
  }

  // Reverse the intake motor
  public void reverse() {
    intakeMotor.set(-Constants.kIntakeSpeed);
  }

  // Stops the intake motor

  public void stop() {
    intakeMotor.set(0);

  }

  public boolean isRunning() {
    double speed = intakeMotor.get();
    if (Math.abs(speed) > 0) {
      return true;
    } else {
      return false;
    }
  }
}
