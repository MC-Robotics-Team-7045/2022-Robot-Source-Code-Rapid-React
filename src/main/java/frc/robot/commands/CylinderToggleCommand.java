/*----------------------------------------------------------------------------*/
/* Copyright (c) 2022 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Compressor;


public class CylinderToggleCommand extends CommandBase {
  private final Compressor m_cylinder;

   // Creates a new IntakeFwdCommand.

  public CylinderToggleCommand(Compressor cylinder) {
    super();
    m_cylinder = cylinder;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(cylinder);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //System.out.println("FIntake-Init");
    m_cylinder.retract();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    m_cylinder.toggle();

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //System.out.println("FIntake-Exec-END");
  }

  // Returns true when the command should end. True to run once.
  @Override
  public boolean isFinished() {
    return true;
  }
}
