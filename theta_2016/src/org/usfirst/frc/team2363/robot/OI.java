package org.usfirst.frc.team2363.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import static org.usfirst.frc.team2363.robot.RobotMap.*;

import org.usfirst.frc.team2363.robot.subsystems.Intake.IntakeState;
import org.usfirst.frc.team2363.robot.commands.*;
import org.usfirst.frc.team2363.robot.commands.intake.IntakeMovement;
import org.usfirst.frc.team2363.robot.commands.intake.IntakePosition;
import org.usfirst.frc.team2363.robot.commands.shooter.ShooterCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private Joystick ps4Controller;
	private Joystick operatorControl;
	private static final double DRIVE_AT_SHOOT_POSITION_SPEED = -0.10;
	
	public OI() {
		ps4Controller = new Joystick(PS4_PORT);
		JoystickButton shooterOn = new JoystickButton(ps4Controller, SHOOTER_ON_BUTTON);
		shooterOn.whenPressed(new ShooterCommand(true));
		JoystickButton shooterOff = new JoystickButton(ps4Controller, SHOOTER_OFF_BUTTON);
		shooterOff.whenPressed(new ShooterCommand(false));
		operatorControl = new Joystick(OPERATOR_PORT);
		operatorControl.setOutput(5, Robot.shooter.isAtSpeed());
		JoystickButton intakeIn = new JoystickButton(ps4Controller, INTAKE_IN);
//		intakeIn.whileHeld(new IntakeCommand(IntakeState.IN, true));
		intakeIn.whileHeld(new IntakeMovement(IntakeState.IN));
		JoystickButton intakeDown = new JoystickButton(ps4Controller, INTAKE_DOWN);
//		intakeDown.whileHeld(new IntakeCommand(IntakeState.OFF, true));
		intakeDown.whenPressed(new IntakePosition(true));
		JoystickButton intakeUp = new JoystickButton(ps4Controller, INTAKE_UP);
		intakeUp.whenPressed(new IntakePosition(false));
		JoystickButton intakeOut = new JoystickButton(ps4Controller, INTAKE_OUT);
//		intakeOut.whileHeld(new IntakeCommand(IntakeState.OUT, true));
		intakeOut.whileHeld(new IntakeMovement(IntakeState.OUT));
	}
	
	public double getThrottle () {
//		return -ps4Controller.getRawAxis(LEFT_STICK_Y);
//		if(ps4Controller.getRawAxis(RIGHT_TRIGGER) > 0) {
//			return -ps4Controller.getRawAxis(LEFT_STICK_Y);
//		} else {
//			return ps4Controller.getRawAxis(LEFT_STICK_Y);
//		}
		if (isXbox()) {
			if (ps4Controller.getRawButton(DRIVE_AT_SHOOT_POSITION_XBOX)) {
				return -0.10;
			} else {
				return ps4Controller.getRawAxis(LEFT_STICK_Y);
			} 
		} else {
			if (ps4Controller.getRawButton(DRIVE_AT_SHOOT_POSITION)) {
				return -0.10;
			} else {
				return ps4Controller.getRawAxis(LEFT_STICK_Y);
			}
		}
		
	}
	
	public double getTurn() {
		if (isXbox()){
			return ps4Controller.getRawAxis(RIGHT_STICK_X_XBOX);
		} else {
			return ps4Controller.getRawAxis(RIGHT_STICK_X);
		}
			
	}
	
	public boolean getIntakeOverride() {
		if (isXbox()) {
			return ps4Controller.getRawButton(INTAKE_OVERRIDE_XBOX);
		} else {
			return ps4Controller.getRawButton(INTAKE_OVERRIDE);
		}
	}
	
	public boolean isXbox() {
		return ps4Controller.getIsXbox();
	}
	
	public void turnOnRumble() {
		ps4Controller.setRumble(RumbleType.kLeftRumble, 1);
		ps4Controller.setRumble(RumbleType.kRightRumble, 1);
//		ps4Controller.setOutput(1, true);
//		ps4Controller.setOutput(2, true);
//		ps4Controller.setOutput(3, true);
//		ps4Controller.setOutput(4, true);
//		ps4Controller.setOutput(5, true);
//		ps4Controller.setOutput(6, true);
//		ps4Controller.setOutput(7, true);
//		ps4Controller.setOutput(8, true);
//		ps4Controller.setOutput(9, true);
//		ps4Controller.setOutput(10, true);
//		ps4Controller.setOutput(11, true);
//		ps4Controller.setOutput(12, true);
//		ps4Controller.setOutput(13, true);
//		ps4Controller.setOutput(14, true);
//		ps4Controller.setOutput(15, true);
//		ps4Controller.setOutput(16, true);
//		ps4Controller.setOutput(17, true);
//		ps4Controller.setOutput(18, true);
//		ps4Controller.setOutput(19, true);
//		ps4Controller.setOutput(20, true);
//		ps4Controller.setOutput(21, true);
//		ps4Controller.setOutput(22, true);
//		ps4Controller.setOutput(23, true);
//		ps4Controller.setOutput(24, true);
//		ps4Controller.setOutput(25, true);
//		ps4Controller.setOutput(26, true);
//		ps4Controller.setOutput(27, true);
//		ps4Controller.setOutput(28, true);
//		ps4Controller.setOutput(29, true);
//		ps4Controller.setOutput(30, true);
//		ps4Controller.setOutput(31, true);
//		ps4Controller.setOutput(32, true);
	}

}

