/*----------------------------------------------------------------------------*/
/* Copyright (c) 2022 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be
 * declared globally (i.e. public static). Do not put anything functional in
 * this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final boolean kDebug = true;

    //CAN Bus Device IDs
    public static final int CAN_MOTOR_LEFT_FRONT_PORT = 30;      // SparkMAX with NEO Brushless
    public static final int CAN_MOTOR_LEFT_REAR_PORT = 31;       // SparkMAX with NEO Brushless
    public static final int CAN_MOTOR_RIGHT_FRONT_PORT = 32;     // SparkMAX with NEO Brushless
    public static final int CAN_MOTOR_RIGHT_REAR_PORT = 33;      // SparkMAX with NEO Brushless
    public static final int CAN_MOTOR_INTAKE_PORT = 16; // G
    public static final int CAN_MOTOR_INDEXER_PORT = 10; // A
    public static final int CAN_MOTOR_SHOOTER_PORT = 40;         // TalonSRX
    public static final int CAN_MOTOR_STATIC_CLIMBER_PORT = 35;  // SparkMAX with NEO Brushless
    public static final int CAN_MOTOR_ACTIVE_CLIMBER_PORT = 34;  // SparkMAX with NEO Brushless
    public static final int CAN_MOTOR_DART_PORT = 41; //41       // TalonSRX
    // ANalog INput
    public static final int CLIMBER_STATIC_POT_PORT = 3;
    public static final int CLIMBER_ACTIVE_POT_PORT = 2;
    public static final int DART_POT_PORT = 1;
    // Joystick Buttons
    public static final int JOYSTICK_BUTTON_SHOOT = 1;
    public static final int JOYSTICK_BUTTON_AIM = 2;
    public static final int JOYSTICK_BUTTON_INTAKE_FWD = 7;
    public static final int JOYSTICK_BUTTON_INTAKE_REV = 8;
    public static final int JOYSTICK_BUTTON_INDEXER_ADV = 5;
    public static final int JOYSTICK_BUTTON_INDEXER_REV = 3;
    public static final int JOYSTICK_BUTTON_LOAD_MAGAZINE = 4;
    public static final int JOYSTICK_BUTTON_SHOOTER_MOTOR = 11;
    public static final int JOYSTICK_BUTTON_CLIMBER_UP = 9;
    public static final int JOYSTICK_BUTTON_CLIMBER_DOWN = 10;
    public static final int JOYSTICK_BUTTON_BALL_COUNTER = 6;
    // Pneumatic Ports
    public static final int DSOL_PORT_FWD = 0;
    public static final int DSOL_PORT_REV = 1;

    // XBOX Buttons
    public static final int xboxGreenButton = 1;
    public static final int xboxRedButton = 2;
    public static final int xboxBlueButton = 3;
    public static final int xboxYellowButton = 4;
    public static final int xboxLBButton = 5;
    public static final int xboxRBButton = 6;
    public static final int xboxStartButton = 7;
    public static final int xboxMenuButton = 8;
    public static final int xboxLStickButton = 9;
    public static final int xboxRStickButton = 10;

    // DIO Ports

    public static final int SHOOTER_ENCODER_DIO_PORT_A = 8; // Reversed B? //Yellow=Signal Orange=+5V
    public static final int SHOOTER_ENCODER_DIO_PORT_B = 9; // Blue Wire=Signal, black=Ground
    public static final int LIDAR_DIO_PORT = 7;
    public static final int DART_UPPER_LIMIT_DIO_PORT = 6;
    public static final int DART_MID_UPPER_LIMIT_DIO_PORT = 5;
    public static final int DART_MID_LOWER_LIMIT_DIO_PORT = 4;
    public static final int DART_LOWER_LIMIT_DIO_PORT = 3;

    public static final int SWITCH_S1_SHOOTER_DIO_PORT = 2;
    public static final int SWITCH_S2_MID_INDEX_DIO_PORT = 1;
    //public static final int SWITCH_S3_START_INDEX_DIO_PORT = 3;
    public static final int SWITCH_S4_INTAKE_DIO_PORT = 0;

    // USB Ports
    public static final int JOYSTICK_PORT = 1;
    public static final int XBOX_CONTROLLER_PORT = 2;

    // Drive Motor Related Constants
    public static final double kMoveLimit = 1.0; // Maximum Move Overide Limit
    public static final double kTurnLimit = 1.0; // Maximum Turn Overide Limit

    public static final double kTurnScaling = 0.8; // Scale value for turns

    public static final double kMoveMinThreshold = 0.1;
    public static final double kTurnMinThreshold = 0.1;
    // Targeting Settings
    public static final double kTargetMove = 0.05; // scaling constant
    public static final double kTargetMoveMax = 0.6;
    public static final double kTargetMoveMinThreshold = 0.4;

    public static final double kTargetTurn = 0.03; // scaling constant
    public static final double kTargetTurnMax = 0.4; // Squared result(~.16) Max turn speed during target acquisition
    public static final double kTargetTurnMinThreshold = 0.3;

    public static final double kMinXTargetOffset = 2.5; // Min offset angle to be on target
    public static final double kMinYTargetOffset = 0.5; // Min offset angle to be on target

    // LIDAR Settings
    public static final double LidarOffset = 8; // CM?
    public static final double LidarSetDistance = 4.3; // feet
    public static final double LidarSetTolerance = 0.25; // feet
    public static final double kLidarMoveMinThreshold = 0.4;

    // Intake Motor Settings
    public static final double kIntakeSpeed = 0.80; // Speed of Intake Motor

    // Dart Motor Settings
    public static final double kDartSpeed = 0.65; //  was .65
    public static final double kDartForwardVoltage = 2.0; //POT Voltage
    public static final double kDartReverseVoltage = 4.0; //POT Voltage
    public static final int kTimeoutMs = 0;
    public static final int kPIDLoopIdx = 0;
    public static final int kSlotIdx = 0;

    // Climber Static Motor Settings
    public static final double kClimberStaticSpeed = 0.65; // PWM Speed of Climber Motor
    public static final double kClimberStaticHoldTIme = 0.0; // Seconds to engage hold routine (small amount to climber PWM)
    public static final double kClimberStaticHoldSpeed = 0.15; // PWM Speed during "hold" period
    public static final double kClimberStaticRetractedVoltage = 4.22; // POT Voltage when climber fully retracted (down)
    public static final double kClimberStaticExtendedVoltage = 1.85; // Set point for Climber POT when extended
    // Climber Active Motor Settings
    public static final double kClimberActiveSpeed = 0.65; // PWM Speed of Climber Motor
    public static final double kClimberActiveHoldTIme = 0.0; // Seconds to engage hold routine (small amount to climber PWM)
    public static final double kClimberActiveHoldSpeed = 0.15; // PWM Speed during "hold" period
    public static final double kClimberActiveRetractedVoltage = 4.0; // POT Voltage when climber fully retracted (down)
    public static final double kClimberActiveExtendedVoltage = 1.3; // Set point for Climber POT when extended

    // Indexer Encoder

    public static final double kIndexerSpeed = 0.80; // Speed of the Indexer Motor
    public static final double kEncRevolutions = 1.0 - kIndexerSpeed / 10.0; // # of revolution
    public static final double kEncPPR = 7.0; // ENcoder pulses per revolution
    public static final double kEncGearRatio = 27.0; // kEnc*number of revolutions

    // Magazine Ball Counts
    public static final int kMaxMagazineBalls = 2;
    public static final int kInitMagazineBalls = 1;

    // Shooter Constants
    // Non-PID Shooter - fixed input
    public static final double kShooterSpeed = .65; // was 0.65 Fixed spped input 0-1
    // PID Values
    public static final double kShooter_P = .1;
    public static final double kShooter_I = 0;
    public static final double kShooter_D = 0.;

    // Rated freespin is 5310. With mounted motor and 1:1.92 gearbox & 4 stealth
    // wheels ~8000rpm
    // public static final double kShooterTargetRPS = 5310*1.92*kShooterSpeed/60;
    // //convert RPM to RPS to set Target
    public static final double kShooterMaxRPM = 8000;
    public static final double kShooterTargetRPS = kShooterMaxRPM * kShooterSpeed / 60; // convert RPM to RPS to set
                                                                                        // Target
    public static final double kShooterToleranceRPS = 10; // was 200/60???

    // E4T Mini Optical Encoder counts per revoluio (CPR)=360
    // WHD Wheel Diameter=4"
    // Distance per pulse = PI*WHD/CPR
    // Rotations per pulse = 1/CPR

    public static final int kShooterEncoderPPR = 360; // 20 pulses per channel per revolution (CIM Encoder), 360 CPR for
                                                      // E4T Mini Optical Encoder
    public static final double kShooterEncoderDistancePerPulse = 1.0 / (double) kShooterEncoderPPR; // Distance units
                                                                                                    // will be rotations
    // public static final double kShooterEncoderDistancePerPulse =
    // Math.PI*4.0/360.0; // Distance units with 4" Wheel

    public static final double kShooterFreeRPS = 5310 * 1.92 / 60; // Maximum free spin with 1.92:1 Flyer Gearbox
    public static final double kSVolts = 1.0 / kShooterEncoderPPR;
    public static final double kVVoltSecondsPerRotation = 1.3 * 12.0 / kShooterFreeRPS; // Should have value 12V at free
                                                                                        // speed...
    public static final double kShooterMotorRampDown = 1.0;// number of seconds to ramp down after last ball

    // Color Wheel Constants
    public static final double kColorWheelSpeed = 1.0; // Speed of the Color WHeel Motor
    public static final double kColorWheelDiameter = 3.0; // Diameter of compliant wheel installed on Color WHeel motor
    public static final double kFRCColorWheelDiameter = 32.0; // Diameter of FRC color wheel at competition

    public static final double kColorWheelEncRevolutions = 3.5 * (kFRCColorWheelDiameter / kColorWheelDiameter); // # of
                                                                                                                 // revolution
                                                                                                                 // of
                                                                                                                 // CW
                                                                                                                 // Motor
                                                                                                                 // to
                                                                                                                 // spin
                                                                                                                 // FRC
                                                                                                                 // wheel
                                                                                                                 // times.
    // public static final double kColorWheelEncRevolutions = 4.25; //TESTING,
    // replace with above
    public static final double kColorWheelEncPPR = 7.0; // ENcoder pulses per revolution
    public static final double kColorWheelEncGearRatio = 27.0; // kEnc*number of revolutions



}
