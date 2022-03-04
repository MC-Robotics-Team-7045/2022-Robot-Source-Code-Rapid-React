/*----------------------------------------------------------------------------*/
/* Copyright (c) 2022 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.util.Color;


public class ColorSubsystem extends SubsystemBase {
  //private Spark wheelSpark = new Spark(Constants.MOTOR_COLOR_WHEEL_PORT);
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
  public ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
  private Color detectedColor2 = colorSensor.getColor();
 /**
   * Creates a new ColorWheel.
   */
  public ColorSubsystem() {
    super();

 //Shuffleboard.getTab("Shooter").add("Color-Blue", detectedColor2.blue);
 //Shuffleboard.getTab("Shooter").add("Color-Green", detectedColor2.green);
 //Shuffleboard.getTab("Shooter").add("Color-Red", detectedColor2.red);

  }

  public void init(){
 
    }

  
   //Start the color sensor
   public void start() {
    detectedColor2 = colorSensor.getColor();
    SmartDashboard.putNumber("Blue", detectedColor2.blue);
    SmartDashboard.putNumber("Green", detectedColor2.green);
    SmartDashboard.putNumber("Red", detectedColor2.red);


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
//    SmartDashboard.putNumber("RColor", detectedColor2.red);
//    SmartDashboard.putNumber("GColor", detectedColor2.green);
//    SmartDashboard.putNumber("BColor", detectedColor2.blue);
  }
}
