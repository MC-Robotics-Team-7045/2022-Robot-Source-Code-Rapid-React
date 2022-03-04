/*----------------------------------------------------------------------------*/
/* Copyright (c) 2022 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.Constants;

/**
 * Add your docs here.
 */
public class Compressor extends SubsystemBase {
  private final DoubleSolenoid dSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.DSOL_PORT_FWD, Constants.DSOL_PORT_REV);


  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Compressor(){
    super();

    Shuffleboard.selectTab("Shooter");
    Shuffleboard.getTab("Shooter").add("Cylinder", dSolenoid);
  }
  public void init() {
    dSolenoid.set(DoubleSolenoid.Value.kOff);
  }
  //Extend the cylinder
  public void extend() {
    dSolenoid.set(DoubleSolenoid.Value.kForward);
  }

 //retract the cylinder
  public void retract() {
    dSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void toggle(){
    dSolenoid.toggle();

  }



}



