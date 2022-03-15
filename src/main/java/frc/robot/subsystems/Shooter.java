/*----------------------------------------------------------------------------*/
/* Copyright (c) 2022 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;



import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

/**
 * Add your docs here.
 */
public class Shooter extends SubsystemBase {
  private final WPI_VictorSPX shooterMotor = new WPI_VictorSPX(Constants.CAN_MOTOR_SHOOTER_PORT);
  public static double overrideShooterSpeed = Constants.kShooterSpeed; //Set initial vale

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Shooter(){
    //super();
    super();
    
    //LiveWindow
    Shuffleboard.selectTab("Shooter");
    Shuffleboard.getTab("Shooter").add("Shooter Motor", shooterMotor)
        .withPosition(1,0);
    //Shuffleboard.getTab("Shooter").add("Shooter Encoder", shooterEncoder)
    //    .withPosition(1,1);
    //Shuffleboard.getTab("Shooter").add("Controller", m_controller)
    //    .withPosition(0,3);

  }
  public void init() {

    shooterMotor.configFactoryDefault();
    shooterMotor.setInverted(false);
    shooterMotor.setNeutralMode(NeutralMode.Brake);

  }
   //Start the intake motor //TEST
   public void start() {
    //shooterMotor.set(Constants.kShooterSpeed);
    shooterMotor.set(overrideShooterSpeed);
  }

//COMMENT OUT ENABLE AND DISABLE HERE IF USING PID SHOOTER
  public void enable() {
    //shooterMotor.set(Constants.kShooterSpeed);
    shooterMotor.set(overrideShooterSpeed);
  }
  public void disable() {
    shooterMotor.set(0);
  }
/////////////////////////////////////////////////////////////


 // Stops the Shooter motor

  public void stop() {
    shooterMotor.set(0);
  }

  /**
   * Return true when the shooter motor is running
   */
  public boolean isRunning() {

    if (Math.abs(shooterMotor.get()) > 0.0){
      return true;
    }
    else{
      return false;
    }
  }
}



