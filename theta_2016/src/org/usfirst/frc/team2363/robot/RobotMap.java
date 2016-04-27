package org.usfirst.frc.team2363.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	//Turn Scaling 
	public static final double HIGH_SPEED_SCALING = 0.4;
	public static final double LOW_SPEED_SCALING = 0.9;
	
	//Controllers
	public static final int PS4_PORT = 0;
	public static final int OPERATOR_PORT = 1;
	public static final int RUMBLE_PORT = 5;
		
	//PS4 joystick axis
	public static final int LEFT_STICK_X = 0;
	public static final int LEFT_STICK_Y = 1;
	public static final int RIGHT_STICK_X = 2;
	public static final int LEFT_TRIGGER = 3;
	public static final int RIGHT_TRIGGER = 4;
	public static final int RIGHT_STICK_Y = 5;
	
	//PS4 Buttons
	public static final int SQUARE = 1;
	public static final int X = 2;
	public static final int CIRCLE = 3;
	public static final int TRIANGLE = 4;
	public static final int L1 = 5;
	public static final int R1 = 6;
	public static final int L2 = 7;
	public static final int R2 = 8;
	public static final int SHARE = 9;
	public static final int OPTIONS = 10;
	public static final int L3 = 11;
	public static final int R3 = 12;
	public static final int PS = 13;
	public static final int TOUCHPAD = 14;
	
	//Drivetrain
	public static final int FRONT_LEFT_TALON_CHANNEL = 30;
	public static final int FRONT_RIGHT_TALON_CHANNEL = 31;
	public static final int REAR_LEFT_TALON_CHANNEL = 32;
	public static final int REAR_RIGHT_TALON_CHANNEL = 33;
	public static final int DRIVE_AT_SHOOT_POSITION = 11; //LS Button
	public static final int LEFT_DRIVE_ENCODER_A = 3;
	public static final int LEFT_DRIVE_ENCODER_B = 4;
	public static final int RIGHT_DRIVE_ENCODER_A = 5;
	public static final int RIGHT_DRIVE_ENCODER_B = 6;
	public static final int BRAKE_CHANNEL_A = 1;
	public static final int BRAKE_CHANNEL_B = 6;
	
	//Shooter
	public static final int SHOOTER_TALON_1 = 60;
	public static final int SHOOTER_TALON_2 = 61;
	public static final int SHOOTER_ENCODER_A = 9;
	public static final int SHOOTER_ENCODER_B = 2;
	public static final int SHOOTER_HOOD_A = 5;
	public static final int SHOOTER_HOOD_B = 2;
	public static final int CAMERA_RELAY = 0;
	public static final int FLASHLIGHT_RELAY = 1;
	
	//Intake
	public static final int INTAKE_TALON = 50;
	public static final int INTAKE_SOLENOID_A = 7;
	public static final int INTAKE_SOLENOID_B = 0;
	public static final int BALL_LIMIT_CHANNEL = 1;
	
	
	//Climber
	public static final int CLIMBER_ANGLE = 20;
	public static final int CLIMBER_ELEVATOR_A = 40;
	public static final int CLIMBER_ELEVATOR_B = 41;
//	public static final int CLIMBER_PNEUMATICS_EXTEND = 2;
//	public static final int CLIMBER_PNEUMATICS_RETRACT = 5;
	public static final int CLIMBER_ELEVATOR_ENCODER_A = 7;
	public static final int CLIMBER_ELEVATOR_ENCODER_B = 8;
}
