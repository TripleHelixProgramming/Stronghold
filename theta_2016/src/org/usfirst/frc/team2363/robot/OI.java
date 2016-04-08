package org.usfirst.frc.team2363.robot;

import static org.usfirst.frc.team2363.robot.RobotMap.CIRCLE;
import static org.usfirst.frc.team2363.robot.RobotMap.L1;
import static org.usfirst.frc.team2363.robot.RobotMap.L2;
import static org.usfirst.frc.team2363.robot.RobotMap.LEFT_STICK_Y;
import static org.usfirst.frc.team2363.robot.RobotMap.OPERATOR_PORT;
import static org.usfirst.frc.team2363.robot.RobotMap.PS;
import static org.usfirst.frc.team2363.robot.RobotMap.PS4_PORT;
import static org.usfirst.frc.team2363.robot.RobotMap.R1;
import static org.usfirst.frc.team2363.robot.RobotMap.R2;
import static org.usfirst.frc.team2363.robot.RobotMap.RIGHT_STICK_X;
import static org.usfirst.frc.team2363.robot.RobotMap.RIGHT_STICK_Y;
import static org.usfirst.frc.team2363.robot.RobotMap.RUMBLE_PORT;
import static org.usfirst.frc.team2363.robot.RobotMap.SQUARE;
import static org.usfirst.frc.team2363.robot.RobotMap.TOUCHPAD;
import static org.usfirst.frc.team2363.robot.RobotMap.TRIANGLE;
import static org.usfirst.frc.team2363.robot.RobotMap.X;

import org.usfirst.frc.team2363.robot.commands.climber.ClimberCommand;
import org.usfirst.frc.team2363.robot.commands.climber.ClimberOverrideCommand;
import org.usfirst.frc.team2363.robot.commands.drivetrain.BrakeCommand;
import org.usfirst.frc.team2363.robot.commands.intake.IntakeMovement;
import org.usfirst.frc.team2363.robot.commands.intake.IntakePosition;
import org.usfirst.frc.team2363.robot.commands.shooter.ShooterCommand;
import org.usfirst.frc.team2363.robot.commands.shooter.ShooterFlashlight;
import org.usfirst.frc.team2363.robot.subsystems.Intake.IntakeState;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private Joystick ps4Controller;
	private Joystick operatorController;
	private Joystick operatorRumble;
	
	public OI() {
		//Controllers
		ps4Controller = new Joystick(PS4_PORT);
		operatorController = new Joystick(OPERATOR_PORT);
		operatorRumble = new Joystick(RUMBLE_PORT);
		
		//PS4 Controller
		JoystickButton intakeIn = new JoystickButton(ps4Controller, R1);
		intakeIn.whileHeld(new IntakeMovement(IntakeState.IN));
		JoystickButton intakeDown = new JoystickButton(ps4Controller, L2);
		intakeDown.whenPressed(new IntakePosition(true));
		JoystickButton intakeUp = new JoystickButton(ps4Controller, L1);
		intakeUp.whenPressed(new IntakePosition(false));
		JoystickButton intakeOut = new JoystickButton(ps4Controller, R2);
		intakeOut.whileHeld(new IntakeMovement(IntakeState.OUT));
		JoystickButton toggleBrake = new JoystickButton(ps4Controller, X);
		toggleBrake.whenPressed(new BrakeCommand(true));
		toggleBrake.whenReleased(new BrakeCommand(false));
		
		//Operator Controller
		JoystickButton intakeInOp = new JoystickButton(operatorController, SQUARE);
		intakeInOp.whileHeld(new IntakeMovement(IntakeState.IN));
		JoystickButton intakeDownOp = new JoystickButton(operatorController, X);
		intakeDownOp.whenPressed(new IntakePosition(true));
		JoystickButton intakeUpOp = new JoystickButton(operatorController, TRIANGLE);
		intakeUpOp.whenPressed(new IntakePosition(false));
		JoystickButton intakeOutOp = new JoystickButton(operatorController, CIRCLE);
		intakeOutOp.whileHeld(new IntakeMovement(IntakeState.OUT));
		JoystickButton shooterOn = new JoystickButton(operatorController, R1);
		shooterOn.whenPressed(new ShooterCommand(true));
		JoystickButton shooterOff = new JoystickButton(operatorController, R2);
		shooterOff.whenPressed(new ShooterCommand(false));
//		JoystickButton toggleClimberState = new JoystickButton(operatorController, TOUCHPAD);
//		toggleClimberState.toggleWhenPressed(new ClimberCommand());
		JoystickButton toggleClimberOvrride = new JoystickButton(operatorController, PS);
		toggleClimberOvrride.toggleWhenPressed(new ClimberOverrideCommand());
//		JoystickButton toggleBrakeOp = new JoystickButton(operatorController, L2);
//		toggleBrakeOp.whenPressed(new BrakeCommand(true));
//		toggleBrakeOp.whenReleased(new BrakeCommand(false));
		JoystickButton flashlightOp = new JoystickButton(operatorController, L2);
		flashlightOp.whenPressed(new ShooterFlashlight(true));
		flashlightOp.whenReleased(new ShooterFlashlight(false));
	}
	
	public double getThrottle () {
		return ps4Controller.getRawAxis(LEFT_STICK_Y);
//		if (ps4Controller.getRawAxis(LEFT_STICK_Y) >= 0) {
//			return Math.pow(ps4Controller.getRawAxis(LEFT_STICK_Y), 2);
//		} else {
//			return -Math.pow(ps4Controller.getRawAxis(LEFT_STICK_Y), 2);
//		}
/*		if (isXbox()) {
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
		}*/
		
	}
	
	public double getTurn() {
		return (ps4Controller.getRawAxis(RIGHT_STICK_X) * 0.9);
//		if (ps4Controller.getRawAxis(RIGHT_STICK_X) >= 0) {
//			return Math.pow(ps4Controller.getRawAxis(RIGHT_STICK_X), 1.5);
//		} else {
//			return -Math.pow(Math.abs(ps4Controller.getRawAxis(RIGHT_STICK_X)), 1.5);
//		}
	}
		
	public int getPOV() {
		return operatorController.getPOV();
	}
	
	public double getOperatorAngle() {
//		return operatorController.getRawAxis(LEFT_STICK_Y);
		if (operatorController.getRawAxis(LEFT_STICK_Y) >= 0) {
			return Math.pow(operatorController.getRawAxis(LEFT_STICK_Y), 2);
		} else {
			return -Math.pow(operatorController.getRawAxis(LEFT_STICK_Y), 2);
		}
	}
	
	public double getOperatorElevator() {
//		return operatorController.getRawAxis(RIGHT_STICK_Y);
		if (operatorController.getRawAxis(RIGHT_STICK_Y) >= 0) {
			return Math.pow(operatorController.getRawAxis(RIGHT_STICK_Y), 2);
		} else {
			return -Math.pow(operatorController.getRawAxis(RIGHT_STICK_Y), 2);
		}
	}
	
	public boolean getBrakeToggle() {
		return ps4Controller.getRawButton(X);
	}
	
	public boolean getIntakeOverride() {
		return operatorController.getRawButton(L1);
	}
	
	public void turnOnRumble() {
		operatorRumble.setRumble(RumbleType.kLeftRumble, 1);
		operatorRumble.setRumble(RumbleType.kRightRumble, 1);
		SmartDashboard.putBoolean("At Speed", true);
	}
	
	public void turnOffRumble() {
		operatorRumble.setRumble(RumbleType.kLeftRumble, 0);
		operatorRumble.setRumble(RumbleType.kRightRumble, 0);
		SmartDashboard.putBoolean("At Speed", false);
	}
}

