/*----------------------------------------------------------------------------*/
/* Copyright (c) 2022 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Indexer;
//import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

//Manual override to decrement ball count by 1 with each press of button

public class AdjustBallCountCommand extends CommandBase {

  // private final Indexer m_indexer;

  /**
   * Creates a new LoadMagazineCommand.
   */
  public AdjustBallCountCommand(final Indexer indexer) {
    super();

    // m_indexer = indexer;
    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(indexer);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // falling edge detected
    Indexer.balls = Indexer.balls - 1;

    if (Indexer.balls == -1) {
      Indexer.balls = Constants.kMaxMagazineBalls;
    }

    if (Constants.kDebug) {
      System.out.print("OVERRIDE Ball count  - Balls=");
      System.out.println(Indexer.balls);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return true;

  }
}
