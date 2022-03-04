/*----------------------------------------------------------------------------*/
/* Copyright (c) 2022 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogInput;
//import edu.wpi.first.wpilibj.Counter;
import frc.robot.Constants;

//import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

/**
 * Add your docs here.
 */
public class ClimberSubsystem extends SubsystemBase {
  private final PWMVictorSPX climberMotor = new PWMVictorSPX(Constants.MOTOR_CLIMBER_PORT);
  //public DigitalInput upperLimit = new DigitalInput(Constants.climber_UPPER_LIMIT_DIO_PORT);
  //public DigitalInput lowerLimit = new DigitalInput(Constants.climber_LOWER_LIMIT_DIO_PORT);
  public AnalogInput climberPot = new AnalogInput(Constants.CLIMBER_POT_PORT);
  //public Counter m_LIDAR = new Counter(9);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public ClimberSubsystem(){
    super();

    //LiveWindow
    //addChild("climber Motor", climberMotor);
    Shuffleboard.selectTab("Climber");
    Shuffleboard.getTab("Climber").add("Climber Motor", climberMotor).withPosition(0,0);;
    Shuffleboard.getTab("Climber").add("Climber Voltage", climberPot).withPosition(0,1);;

  }
public void init(){

}
  //Start the climber motor
  public void up() {
    climberMotor.set(Constants.kClimberSpeed);
  }

 //Reverse the climber motor
 public void down() {
  climberMotor.set(-Constants.kClimberSpeed);
}
 //Reverse the climber motor
 public void hold() {
  climberMotor.set(-Constants.kClimberHoldSpeed);
}

 // Stops the intake motor
  public void stop() {
    climberMotor.set(0);

  }

  public boolean isRunning() {
    double speed = climberMotor.get();
    if (Math.abs(speed)> 0){
      return true;
    }
    else{
      return false;
    }
  }

  public double climberVoltage(){
    return climberPot.getVoltage();
  }


}



