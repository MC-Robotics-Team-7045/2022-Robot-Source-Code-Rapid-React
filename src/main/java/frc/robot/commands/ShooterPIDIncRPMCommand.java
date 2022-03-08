/*----------------------------------------------------------------------------*/
/* Copyright (c) 2022 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.ShooterPID;
import frc.robot.Constants;

public class ShooterPIDIncRPMCommand extends CommandBase {
  private final ShooterPID m_shooter;

  /**
   * Creates a new LoadMagazineCommand.
   */
  public ShooterPIDIncRPMCommand(final ShooterPID shooter) {
    super();
    m_shooter = shooter;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    ShooterPID.overrideShooterSpeed = ShooterPID.overrideShooterSpeed + 0.05;

    if (ShooterPID.overrideShooterSpeed > 1.0) {
      ShooterPID.overrideShooterSpeed = 1.0;
    }
    if (Constants.kDebug) {
      System.out.print("Increasing ShooterPID Speed");
      System.out.println(ShooterPID.overrideShooterSpeed);
    }
    if (m_shooter.isRunning()) {
      m_shooter.setSetpoint(Constants.kShooterMaxRPM * ShooterPID.overrideShooterSpeed / 60);
      ; // Start with overrideSpeed
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
