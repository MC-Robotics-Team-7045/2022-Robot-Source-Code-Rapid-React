/*----------------------------------------------------------------------------*/
/* Copyright (c) 2022 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

//REFERENCES
// https://andymark-weblinc.netdna-ssl.com/media/W1siZiIsIjIwMTkvMDcvMTIvMTMvMjkvNTYvNTIwMjFhMjMtMGQ1Ny00Mjg4LWJmYTUtODBiMmRlM2FiMTgwL3BsaS0wNi1pbnN0cnVjdGlvbi5wZGYiXV0/pli-06-instruction.pdf?sha=e7f6330249d13563
// https://girlsofsteeldocs.readthedocs.io/en/latest/technical-resources/sensors/LIDAR-Lite-Distance-Sensor.html
// Wired with PWM configuration and 1-kOhm resistor.
// Setup as counter



public class Lidar extends SubsystemBase{
  private Counter counter;
 /**
   * Creates a new Lidar
   */
public Lidar() {
    super();
    counter = new Counter(Constants.LIDAR_DIO_PORT);
    counter.setMaxPeriod(1.0);
    // Configure for measuring rising to falling pulses
    counter.setSemiPeriodMode(true);
    counter.setSamplesToAverage(100);
    counter.reset();
    //Shuffleboard.selectTab("Shooter");
    //Shuffleboard.getTab("Shooter").add("LIDAR Dist", getDistance());
    
  }
  
 @Override
  // This method will be called once per scheduler run
  public void periodic() {

  }

    
  public double getDistance (){
    double dist;
    if(counter.get() < 1)
      dist = 0;
    else
      dist = (counter.getPeriod()*1000000.0/10.0) - Constants.LidarOffset; //convert to distance. sensor is high 10 us for every centimeter. 
      dist = dist / 2.54 / 12.0; //Convert CM to FEET   

      SmartDashboard.putNumber("LIDAR Distance", dist); //put the distance on the dashboard
      
  return  dist;
  }
}
