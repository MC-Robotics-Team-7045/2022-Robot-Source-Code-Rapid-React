/*----------------------------------------------------------------------------*/
/* Copyright (c) 2022 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lidar;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import java.util.function.DoubleSupplier;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LidarAutoAlign extends CommandBase {
  DriveTrain m_driveTrain;
  Lidar m_lidar;
  DoubleSupplier m_move;
  DoubleSupplier m_throttle;

  /**
   * Creates a new LimelightAutoAlign.
   */
  public LidarAutoAlign(DriveTrain driveTrain, Lidar lidarvar, DoubleSupplier move, DoubleSupplier throttle) {

    // Use addRequirements() here to declare subsystem dependencies.
    m_driveTrain = driveTrain;
    m_lidar = lidarvar;
    m_move = move;
    m_throttle = throttle;
    addRequirements(driveTrain);
    addRequirements(lidarvar);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // m_driveTrain.manualDrive(0, 0, 1);

    // System.out.println("LL-Initialize");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double distance = m_lidar.getDistance();

    // SmartDashboard.putNumber("LIDAR Dist", distance);

    double turnAlign = 0.0 * Constants.kTargetTurn;

    double moveAlign = Constants.kLidarMoveMinThreshold;

    if (Math.abs(distance - Constants.LidarSetDistance) > Constants.LidarSetTolerance) {
      moveAlign = Constants.kLidarMoveMinThreshold * Math.signum(distance - Constants.LidarSetDistance);
    } else {
      moveAlign = 0.0;
    }

    System.out.print("Move  =");
    System.out.print(moveAlign);
    System.out.print(" Distance - ");
    System.out.format("%.2f", distance);
    System.out.println(" ft");
    // m_driveTrain.targetDrive(m_move.getAsDouble(), turnAlign,
    // m_throttle.getAsDouble());
    m_driveTrain.targetDrive(moveAlign, turnAlign, m_throttle.getAsDouble());
    // System.out.println("LL-Execute");

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.manualDrive(0, 0, 1);

    // System.out.println("LL-End/Interupted");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double distance = m_lidar.getDistance();
    // System.out.println("LL-Finished");

    // return false;

    // Stop command if target acquired and X and Y offsets are less than
    // kMinTagetOffset e.g. 0.25 degrees
    if (Math.abs(distance - Constants.LidarSetDistance) < Constants.LidarSetTolerance) {
      System.out.println("Lidar Align End");

      return true;
    } else {
      return false;
    }
  }
}
