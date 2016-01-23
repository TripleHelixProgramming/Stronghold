package org.usfirst.frc.team2363.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import static org.usfirst.frc.team2363.robot.RobotMap.*;

import org.usfirst.frc.team2363.robot.commands.drivetrain.JoystickDrive;

public class Drivetrain extends Subsystem {
	
	private SpeedController frontLeft = new CANTalon(FRONT_LEFT_TALON_CHANNEL);
	private SpeedController frontRight = new CANTalon(FRONT_RIGHT_TALON_CHANNEL);
	private SpeedController rearLeft = new CANTalon(REAR_LEFT_TALON_CHANNEL);
	private SpeedController rearRight = new CANTalon(REAR_RIGHT_TALON_CHANNEL);
	
	private RobotDrive robotDrive = new RobotDrive(frontLeft, frontRight, rearLeft, rearRight);
	
	public void arcadeDrive(double throttle, double turn) {
		robotDrive.arcadeDrive(throttle, turn);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new JoystickDrive());
		
	}
	
}
