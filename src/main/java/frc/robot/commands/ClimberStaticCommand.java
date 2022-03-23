/*----------------------------------------------------------------------------*/
/* Copyright (c) 2022 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.ClimberStaticSubsystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

//Refer to post by "gixxy" at https://www.chiefdelphi.com/t/help-creating-a-simple-arcade-drive-in-the-new-2020-command-based-framework-java/373084/7
//for setting up and using DoubleSupplier and lambda expressions
//Also: https://www.chiefdelphi.com/t/need-help-with-running-commands/373670/3  - Vision Discussion

public class ClimberStaticCommand extends CommandBase {
  private final ClimberStaticSubsystem m_climber;
  private final DoubleSupplier m_direction;


  /**
   * Creates a new DriveCommand.
   */
  public ClimberStaticCommand(ClimberStaticSubsystem climber, DoubleSupplier direction) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_climber = climber;
    m_direction = direction;

    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_direction.getAsDouble() > 0.2) {  ////deadband < 0.2
      m_climber.up();
    }
    else { 
      if (m_direction.getAsDouble()< 0.2){
      m_climber.down();
      }
      else{
        //m_climber.stop();
      }
    }
    

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climber.stop();
  }

  // Returns true when the command should end. False will continue to get
  // scheduled and run.
  @Override
  public boolean isFinished() {
    if (m_climber.isRunning() && ((m_climber.climberVoltage() > Constants.kClimberStaticRetractedVoltage) || (m_climber.climberVoltage() < Constants.kClimberStaticExtendedVoltage))){
    return true;
    }
    else {
      return false;
    }
  }
}
