/*----------------------------------------------------------------------------*/
/* Copyright (c) 2022 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.Autonomous;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.FireCommand;
//import frc.robot.commands.LimelightAutoAlign;
import frc.robot.commands.LidarAutoAlign;
import frc.robot.commands.LoadMagazineCommand;
import frc.robot.commands.IntakeFwdCommand;
import frc.robot.commands.IntakeRevCommand;
import frc.robot.commands.IndexerCommand;
import frc.robot.commands.IndexerRevCommand;
import frc.robot.commands.ShooterToggleCommand;
import frc.robot.commands.ShooterPIDDecRPMCommand;
import frc.robot.commands.ShooterPIDIncRPMCommand;
import frc.robot.commands.ClimberRetractCommand;
import frc.robot.commands.ClimberExtendCommand;
import frc.robot.commands.AdjustBallCountCommand;
import frc.robot.subsystems.ColorSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Lidar;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.ShooterPID;
//import frc.robot.subsystems.Shooter;  //Plain shooter with no PID
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

//import frc.robot.Constants;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot
 * (including subsystems, commands, and button mappings) should be declared
 * here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain driveTrain = new DriveTrain();

  private final Joystick joystick = new Joystick(Constants.JOYSTICK_PORT);
  // private final XboxController xbox = new
  // XboxController(Constants.XBOX_CONTROLLER_PORT);

  private final LimeLight limeLight = new LimeLight(false);
  private final Intake intake = new Intake();
  private final Indexer indexer = new Indexer();
  private final Lidar m_lidar = new Lidar();
  private final ShooterPID m_shooter = new ShooterPID();
  // private final Shooter m_shooter = new Shooter();

  // private final Compressor c_cylinder = new Compressor();

  // private final ColorWheelSubsystem m_color_wheel = new ColorWheelSubsystem();
  private final ColorSubsystem m_color = new ColorSubsystem();

  private final ClimberSubsystem m_climber = new ClimberSubsystem();

  private final CommandBase m_autonomousCommand = new Autonomous(driveTrain, m_lidar, indexer, m_shooter, intake);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    if (Constants.kDebug) {
      SmartDashboard.putData(driveTrain);
      SmartDashboard.putData(limeLight);
      SmartDashboard.putData(indexer);
      SmartDashboard.putData(m_color);
      SmartDashboard.putData(m_lidar);
      SmartDashboard.putData(intake);
      SmartDashboard.putData(m_shooter);
      SmartDashboard.putData(m_climber);
    }
    driveTrain.setDefaultCommand(
        new DriveCommand(driveTrain, () -> -joystick.getY(), () -> joystick.getZ(), () -> -joystick.getThrottle()));

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    final JoystickButton aim = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_AIM);
    final JoystickButton intakeFwd = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_INTAKE_FWD);
    final JoystickButton intakeRev = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_INTAKE_REV);
    final JoystickButton indexAdv = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_INDEXER_ADV);
    final JoystickButton indexRev = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_INDEXER_REV);
    final JoystickButton shooterMotorControl = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_SHOOTER_MOTOR);
    final JoystickButton loadMagazine = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_LOAD_MAGAZINE);
    final JoystickButton firePowerCell = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_SHOOT);
    final POVButton incShooterRPM = new POVButton(joystick, 0);
    final POVButton decShooterRPM = new POVButton(joystick, 180);
    final JoystickButton climberUp = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_CLIMBER_UP);
    final JoystickButton climberDown = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_CLIMBER_DOWN);
    final JoystickButton adjustBallCount = new JoystickButton(joystick, Constants.JOYSTICK_BUTTON_BALL_COUNTER);
    // final JoystickButton autoRotateColorWHeel = new JoystickButton(joystick,
    // Constants.JOYSTICK_BUTTON_COLOR_WHEEL_AUTO);
    // final JoystickButton cylinderExtend = new JoystickButton(joystick,
    // Constants.JOYSTICK_BUTTON_CYLINDER_FWD);
    // final JoystickButton cylinderRetract = new JoystickButton(joystick,
    // Constants.JOYSTICK_BUTTON_CYLINDER_REV);
    // final JoystickButton cylinderControl = new JoystickButton(joystick,
    // Constants.JOYSTICK_BUTTON_CYLINDER);

    // Xbox Bindings

    // final JoystickButton buttonRED = new JoystickButton(xbox,
    // Constants.joystickRedButton);
    // final JoystickButton buttonYELLOW = new JoystickButton(xbox,
    // Constants.joystickYellowButton);
    // final JoystickButton buttonGREEN = new JoystickButton(xbox,
    // Constants.joystickGreenButton);
    // final JoystickButton buttonBLUE = new JoystickButton(xbox,
    // Constants.joystickBlueButton);
    // final JoystickButton buttonLB = new JoystickButton(xbox,
    // Constants.joystickLBButton);
    // final JoystickButton buttonRB = new JoystickButton(xbox,
    // Constants.joystickRBButton);

    // Joystick COmmands
    // aim.whileHeld(new LimelightAutoAlign(driveTrain, limeLight, () ->
    // -joystick.getY(), () -> -joystick.getThrottle()));
    aim.whileHeld(new LidarAutoAlign(driveTrain, m_lidar, () -> -joystick.getY(), () -> -joystick.getThrottle()));
    intakeFwd.toggleWhenPressed(new IntakeFwdCommand(intake));
    intakeRev.toggleWhenPressed(new IntakeRevCommand(intake));
    indexAdv.toggleWhenPressed(new IndexerCommand(indexer));
    indexRev.toggleWhenPressed(new IndexerRevCommand(indexer));
    shooterMotorControl.toggleWhenPressed(new ShooterToggleCommand(m_shooter));
    loadMagazine.whenPressed(new LoadMagazineCommand(intake, indexer));
    firePowerCell.whenPressed(new FireCommand(indexer, m_shooter, intake));
    incShooterRPM.whenPressed(new ShooterPIDIncRPMCommand(m_shooter)); // POV
    decShooterRPM.whenPressed(new ShooterPIDDecRPMCommand(m_shooter)); // POV
    climberUp.toggleWhenPressed(new ClimberExtendCommand(m_climber));
    climberDown.toggleWhenPressed(new ClimberRetractCommand(m_climber));
    adjustBallCount.toggleWhenPressed(new AdjustBallCountCommand(indexer));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autonomousCommand;
  }

  public void turnOffLimelightLED() {
    limeLight.setLedMode(1); // 1 =OFF 3=ON
  }

  public void stopAllMotors() {
    driveTrain.stop();
    intake.stop();
    m_shooter.stop();
    indexer.stop();
    m_climber.stop();
  }
}
