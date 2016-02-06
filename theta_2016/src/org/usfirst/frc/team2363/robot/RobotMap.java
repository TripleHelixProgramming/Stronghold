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
		
	//PS4 joystick axis
	public static final int LEFT_STICK_X = 0;
	public static final int LEFT_STICK_Y = 1;
	public static final int RIGHT_STICK_X = 2;
	public static final int LEFT_TRIGGER = 3;
	public static final int RIGHT_TRIGGER = 4;
	public static final int RIGHT_STICK_Y = 5;
	public static final int RIGHT_STICK_X_XBOX = 4;
	
	//Drivetrain
	public static final int FRONT_LEFT_TALON_CHANNEL = 30;
	public static final int FRONT_RIGHT_TALON_CHANNEL = 31;
	public static final int REAR_LEFT_TALON_CHANNEL = 32;
	public static final int REAR_RIGHT_TALON_CHANNEL = 33;
	public static final int DRIVE_AT_SHOOT_POSITION = 11; //LS Button
	public static final int DRIVE_AT_SHOOT_POSITION_XBOX = 9; //Still LS Button
	
	//Shooter
	public static final int SHOOTER_TALON = 20;
	public static final int SHOOTER_ENCODER = 1;
	
	//Intake
	public static final int INTAKE_TALON = 50;
	public static final int INTAKE_SOLENOID_A = 0;
	public static final int INTAKE_SOLENOID_B = 1;
	public static final int BALL_LIMIT_CHANNEL = 0;
	
	//PS4 Buttons
	public static final int DIRECTION_SHIFT = 3; //L2
	public static final int SHOOTER_OFF_BUTTON = 2; //x
	public static final int SHOOTER_ON_BUTTON = 4; //triangle
	public static final int INTAKE_DOWN = 7; //L2
	public static final int INTAKE_UP = 5; //L1
	public static final int INTAKE_IN = 6; //R1
	public static final int INTAKE_OUT = 8; //R2
	public static final int INTAKE_OVERRIDE = 12; //RS Button
	public static final int INTAKE_OVERRIDE_XBOX = 10; //Still RS Button
}
