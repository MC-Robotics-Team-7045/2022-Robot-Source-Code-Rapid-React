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
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.util.Color;

public class ColorSubsystem extends SubsystemBase {
  // private Spark wheelSpark = new Spark(Constants.MOTOR_COLOR_WHEEL_PORT);
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  public ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
  public Color detectedColor2 = colorSensor.getColor();
  private final ColorMatch m_colorMatcher = new ColorMatch();

  private final Color kBlueTarget = new Color(0.143, 0.427, 0.429);
  private final Color kGreenTarget = new Color(0.197, 0.5, 0.240);
  private final Color kRedTarget = new Color(0.5, 0.232, 0.114);
  private final Color kYellowTarget = new Color(0.361, 0.524, 0.113);
  /**
   * Creates a new ColorWheel.
   */
  public ColorSubsystem() {
    super();

    // Shuffleboard.getTab("Shooter").add("Color-Blue", detectedColor2.blue);
    // Shuffleboard.getTab("Shooter").add("Color-Green", detectedColor2.green);
    // Shuffleboard.getTab("Shooter").add("Color-Red", detectedColor2.red);

  }

  public void init() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);    


  }

  // Start the color sensor
  public void start() {
    detectedColor2 = colorSensor.getColor();
    SmartDashboard.putNumber("Blue", detectedColor2.blue);
    SmartDashboard.putNumber("Green", detectedColor2.green);
    SmartDashboard.putNumber("Red", detectedColor2.red);

  }

  @Override
  public void periodic() {
    double IR = colorSensor.getIR();
    detectedColor2 = colorSensor.getColor();

    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor2);

    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("RColor", detectedColor2.red);
    SmartDashboard.putNumber("GColor", detectedColor2.green);
    SmartDashboard.putNumber("BColor", detectedColor2.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
    SmartDashboard.putNumber("IR", IR);
  }
}
