package org.usfirst.frc.team2363.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import static org.usfirst.frc.team2363.robot.RobotMap.*;

import org.usfirst.frc.team2363.robot.commands.ShooterCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private Joystick ps4Controller;
	
	public OI() {
		ps4Controller = new Joystick(PS4_PORT);
		JoystickButton shooterOn = new JoystickButton(ps4Controller, SHOOTER_ON_BUTTON);
		shooterOn.whenPressed(new ShooterCommand(true));
		JoystickButton shooterOff = new JoystickButton(ps4Controller, SHOOTER_OFF_BUTTON);
		shooterOff.whenPressed(new ShooterCommand(false));
	}
	
	public double getThrottle () {
//		return -ps4Controller.getRawAxis(LEFT_STICK_Y);
		if(ps4Controller.getRawAxis(RIGHT_TRIGGER) > 0) {
			return ps4Controller.getRawAxis(LEFT_STICK_Y);
		} else {
			return -ps4Controller.getRawAxis(LEFT_STICK_Y);
		}
	}
	
	public double getTurn() {
		return ps4Controller.getRawAxis(RIGHT_STICK_X) * getTurnScaling(getThrottle());
	}

	public static double getTurnScaling(double x) {
		return -Math.abs(LOW_SPEED_SCALING - HIGH_SPEED_SCALING) * x + LOW_SPEED_SCALING;
	}
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

