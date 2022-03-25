/*----------------------------------------------------------------------------*/
/* Copyright (c) 2022 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DartSubsystem;

import frc.robot.Constants;

public class DartForwardCommand extends CommandBase {
  private final DartSubsystem m_dart;

  // Creates a new ClimberFwdCommand.

  public DartForwardCommand(DartSubsystem climbervar) {
    super();
    m_dart = climbervar;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climbervar);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // System.out.println("FClimber-Init");

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    m_dart.up();
  
    if (Constants.kDebug) {
      System.out.print("Dart extending forward - ");
      System.out.format("%.2f", m_dart.dartPot.getVoltage());
      System.out.format(" %d",m_dart.anaPosition);
      System.out.println("");
    }
    /*
     * //TOGGLE FUnction with no Limits
     * if (m_dart.isRunning()){
     * m_dart.stop();
     * }
     * else{
     * m_dart.up();
     * }
     */
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_dart.stop();
    // System.out.println("FClimber-Exec-END");
  }

  // Returns true when the command should end. True to run once.
  /*
   * Hall Effect Sensor
   * Sensor is driven low in the presence of a magnetic field, and high impedance
   * when there is no magnet present
   * Use this as a limit switch.
   */
  @Override
  public boolean isFinished() {

    // Voltage drops as string extends. 0" is approx 4.8V. Full extension < 1V
    // UNCOMMENT TO USE STRING POTENTIOMETER
    // if (m_dart.dartVoltage() < Constants.kDartForwardVoltage) { // limit reached
    // on POT
    //
    // if (Constants.kDebug) {
    // System.out.print("Dart extending forward- ");
    // System.out.format("%.2f", m_dart.dartPot.getVoltage());
    // System.out.println(" - LIMIT REACHED!");
    // }
    // return true;
    // } else {
    // return false;
    // }

    // UNcomment if using on breakout board TALON SRX
    // Check limit switches on Talon breakout. Possibly mislabled fwd vs rev?
    // if (m_dart.isReverseLimitTriggered()){
    // if (Constants.kDebug) {
    // System.out.println("Dart LIMIT Switch triggered on TalonSRX!");
    // }
    // return true;
    // } else {
    // return false;
    // }

    // Uncomment if using Hall
    if (m_dart.isUpperHallLimitTriggered()) {
      if (Constants.kDebug) {
        System.out.println("Dart DIO LIMIT Switch triggered on Upper Hall Effect");
      }
      return true;
    } else {
      return false;
    }
  }

      // Uncomment if using Analog via SRX Breakout
//      if (m_dart.dartPosition() < 650) {
//        if (Constants.kDebug) {
//          System.out.println("Dart POSITION < 650");
//        }
//        return true;
//      } else {
//        return false;
//      }
//    }
}
