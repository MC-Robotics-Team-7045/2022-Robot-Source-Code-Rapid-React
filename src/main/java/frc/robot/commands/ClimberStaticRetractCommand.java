/*----------------------------------------------------------------------------*/
/* Copyright (c) 2022 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.subsystems.ClimberStaticSubsystem;

public class ClimberStaticRetractCommand extends CommandBase {
  private final ClimberStaticSubsystem m_Climber;
  private double timeStamp;
  private boolean holdStage = false;

  // Creates a new Climber Retract Command.

  public ClimberStaticRetractCommand(ClimberStaticSubsystem climbervar) {
    super();
    m_Climber = climbervar;
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

    if (holdStage) {
      m_Climber.hold();
    } else {
      m_Climber.down();
    }

    if (Constants.kDebug) {
      System.out.print("Static Climber retracting - ");
      System.out.format("%.2f", m_Climber.climberPot.getVoltage());
      if (holdStage) {
        System.out.print(" HOLD time: ");
        System.out.format("%.2f", Timer.getFPGATimestamp() - timeStamp);

      }
      System.out.println("");
    }

    /*
     * //TOGGLE FUNCTION WITH NO LIMITS
     * if (m_Climber.isRunning()){
     * m_Climber.stop();
     * 
     * 
     * }
     * else{
     * m_Climber.down();
     * }
     */

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Climber.stop();
    holdStage = false; // reset Hold stage

    // System.out.println("FClimber-Exec-END");
  }

  // Returns true when the command should end. True to run once.
  /*
   * Hall Effect Sensor - NOT WORKING. Switched to linear string potentiometer
   * Sensor is driven low in the presence of a magnetic field, and high impedance
   * when there is no magnet present
   * Use this as a limit switch.
   */
  @Override
  public boolean isFinished() {

    // Voltage increases as string retracts. 0" is approx 4.8V. Full extension < 1V
    if (!holdStage && m_Climber.climberVoltage() > Constants.kClimberStaticRetractedVoltage) { // limit reached arm fully
                                                                                         // retracted.

      holdStage = true;
      // if (holdStage && !prevStage){
      timeStamp = Timer.getFPGATimestamp();
      // }
      // prevStage=holdStage;

      if (Constants.kDebug) {
        System.out.print("Static Climber retracting - ");
        System.out.format("%.2f", m_Climber.climberPot.getVoltage());
        System.out.println(" - LIMIT REACHED!");
      }
    }
    if (holdStage && (Timer.getFPGATimestamp() - timeStamp > Constants.kClimberStaticHoldTIme)) {
      System.out.println("End of HOLD Stage");
      return true; // End hold routine after HoldTime expired
    } else {
      // if (Constants.kDebug){
      // System.out.print("Hold Time - ");
      // System.out.format("%.2f",Timer.getFPGATimestamp() - timeStamp);
      // System.out.println("");
      // }
      return false; // keep running
    }
  }
}
