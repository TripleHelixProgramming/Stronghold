package org.usfirst.frc.team2363.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
	private Joystick operatorController;
	private static final double DRIVE_AT_SHOOT_POSITION_SPEED = -0.10;
	
	public OI() {
		//Controllers
		ps4Controller = new Joystick(PS4_PORT);
		operatorController = new Joystick(OPERATOR_PORT);
		
		//PS4 Controller

		JoystickButton intakeIn = new JoystickButton(ps4Controller, INTAKE_IN);
		intakeIn.whileHeld(new IntakeMovement(IntakeState.IN));
		JoystickButton intakeDown = new JoystickButton(ps4Controller, INTAKE_DOWN);
		intakeDown.whenPressed(new IntakePosition(true));
		JoystickButton intakeUp = new JoystickButton(ps4Controller, INTAKE_UP);
		intakeUp.whenPressed(new IntakePosition(false));
		JoystickButton intakeOut = new JoystickButton(ps4Controller, INTAKE_OUT);
		intakeOut.whileHeld(new IntakeMovement(IntakeState.OUT));
		
		//Operator Controller
		JoystickButton intakeInOp = new JoystickButton(operatorController, INTAKE_IN_OP);
		intakeInOp.whileHeld(new IntakeMovement(IntakeState.IN));
		JoystickButton intakeDownOp = new JoystickButton(operatorController, INTAKE_DOWN_OP);
		intakeDownOp.whenPressed(new IntakePosition(true));
		JoystickButton intakeUpOp = new JoystickButton(operatorController, INTAKE_UP_OP);
		intakeUpOp.whenPressed(new IntakePosition(false));
		JoystickButton intakeOutOp = new JoystickButton(operatorController, INTAKE_OUT_OP);
		intakeOutOp.whileHeld(new IntakeMovement(IntakeState.OUT));
		JoystickButton shooterOn = new JoystickButton(operatorController, SHOOTER_ON_BUTTON);
		shooterOn.whenPressed(new ShooterCommand(true));
		JoystickButton shooterOff = new JoystickButton(operatorController, SHOOTER_OFF_BUTTON);
		shooterOff.whenPressed(new ShooterCommand(false));
		
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
	
	public int getPOV() {
		return operatorController.getPOV();
	}
	
	public void turnOnRumble() {
		ps4Controller.setRumble(RumbleType.kLeftRumble, 1);
		ps4Controller.setRumble(RumbleType.kRightRumble, 1);
		operatorController.setRumble(RumbleType.kLeftRumble, 1);
		operatorController.setRumble(RumbleType.kRightRumble, 1);
		SmartDashboard.putBoolean("At Speed", true);
	}
	
	public void turnOffRumble() {
		ps4Controller.setRumble(RumbleType.kLeftRumble, 0);
		ps4Controller.setRumble(RumbleType.kRightRumble, 0);
		operatorController.setRumble(RumbleType.kLeftRumble, 0);
		operatorController.setRumble(RumbleType.kRightRumble, 0);
		SmartDashboard.putBoolean("At Speed", false);
	}
}

