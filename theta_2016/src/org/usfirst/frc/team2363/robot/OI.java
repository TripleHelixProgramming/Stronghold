package org.usfirst.frc.team2363.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static org.usfirst.frc.team2363.robot.RobotMap.*;

import org.usfirst.frc.team2363.robot.subsystems.Intake.IntakeState;
import org.usfirst.frc.team2363.robot.commands.*;
import org.usfirst.frc.team2363.robot.commands.climber.ClimberAngle;
import org.usfirst.frc.team2363.robot.commands.climber.ClimberElevator;
import org.usfirst.frc.team2363.robot.commands.climber.ClimberOffCommand;
import org.usfirst.frc.team2363.robot.commands.climber.ToggleHookCommand;
import org.usfirst.frc.team2363.robot.commands.intake.IntakeMovement;
import org.usfirst.frc.team2363.robot.commands.intake.IntakePosition;
import org.usfirst.frc.team2363.robot.commands.shooter.ShooterCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private Joystick ps4Controller;
	private Joystick operatorController;
	private Joystick operatorRumble;
	private static final double DRIVE_AT_SHOOT_POSITION_SPEED = -0.10;
	
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
		
		//Operator Controller
		JoystickButton intakeInOp = new JoystickButton(operatorController, SQUARE);
		intakeInOp.whileHeld(new IntakeMovement(IntakeState.IN));
		JoystickButton intakeDownOp = new JoystickButton(operatorController, X);
		intakeDownOp.whenPressed(new IntakePosition(true));
		JoystickButton intakeUpOp = new JoystickButton(operatorController, TRIANGLE);
		intakeUpOp.whenPressed(new IntakePosition(false));
		JoystickButton intakeOutOp = new JoystickButton(operatorController, CIRCLE);
		intakeOutOp.whileHeld(new IntakeMovement(IntakeState.OUT));
		JoystickButton shooterOn = new JoystickButton(operatorController, L1);
		shooterOn.whenPressed(new ShooterCommand(true));
		JoystickButton shooterOff = new JoystickButton(operatorController, L2);
		shooterOff.whenPressed(new ShooterCommand(false));
		JoystickButton hookToggle = new JoystickButton(operatorController, PS);
		hookToggle.whenPressed(new ToggleHookCommand());
		JoystickButton activateClimberAngle = new JoystickButton(operatorController, L3);
		activateClimberAngle.whenPressed(new ClimberAngle());
		JoystickButton activateClimberElevator = new JoystickButton(operatorController, R3);
		activateClimberElevator.whenPressed(new ClimberElevator());
		JoystickButton deactivateClimber = new JoystickButton(operatorController, TOUCHPAD);
		deactivateClimber.whenPressed(new ClimberOffCommand());
	}
	
	public double getThrottle () {
		return ps4Controller.getRawAxis(LEFT_STICK_Y);
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
		return ps4Controller.getRawAxis(RIGHT_STICK_X);
	}
	
	public boolean getIntakeOverride() {
		return ps4Controller.getRawButton(L3);
	}
		
	public int getPOV() {
		return operatorController.getPOV();
	}
	
	public double getOperatorAngle() {
		return operatorController.getRawAxis(LEFT_STICK_Y);
	}
	
	public double getOperatorElevator() {
		return operatorController.getRawAxis(RIGHT_STICK_Y);
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

