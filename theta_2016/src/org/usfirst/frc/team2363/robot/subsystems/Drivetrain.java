package org.usfirst.frc.team2363.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static org.usfirst.frc.team2363.robot.RobotMap.*;
import edu.wpi.first.wpilibj.SPI;

import org.usfirst.frc.team2363.robot.commands.drivetrain.JoystickDrive;

import com.kauailabs.navx.frc.AHRS;

public class Drivetrain extends Subsystem {
	
	//Talons
	private SpeedController frontLeft = new CANTalon(FRONT_LEFT_TALON_CHANNEL);
	private SpeedController frontRight = new CANTalon(FRONT_RIGHT_TALON_CHANNEL);
	private SpeedController rearLeft = new CANTalon(REAR_LEFT_TALON_CHANNEL);
	private SpeedController rearRight = new CANTalon(REAR_RIGHT_TALON_CHANNEL);
	
	//Encoders
	private Encoder leftEncoder = new Encoder(LEFT_DRIVE_ENCODER_A, LEFT_DRIVE_ENCODER_B, false, EncodingType.k4X);
	private Encoder rightEncoder = new Encoder(RIGHT_DRIVE_ENCODER_A, RIGHT_DRIVE_ENCODER_B, false, EncodingType.k4X);
	
	private RobotDrive robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
	
	private AHRS ahrs;
	
	public Drivetrain() {
		try {
            ahrs = new AHRS(SPI.Port.kMXP); 
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }
		leftEncoder.setDistancePerPulse(0.01636);
		leftEncoder.setSamplesToAverage(12);

		rightEncoder.setDistancePerPulse(0.01636);
		rightEncoder.setSamplesToAverage(12);
	}
	
	public void arcadeDrive(double throttle, double turn) {
		robotDrive.arcadeDrive(throttle, turn);
	}
	
	public double getLeftPosition() {
		return leftEncoder.getDistance();
	}

	public double getRightPosition() {
		return -rightEncoder.getDistance();
	}

	public double getLeftSpeed() {
		return leftEncoder.getRate();
	}

	public double getRightSpeed() {
		return -rightEncoder.getRate();
	}

	public void resetEncoders() {

		
		
		
		
		
		rightEncoder.reset();
		leftEncoder.reset();
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoystickDrive());
		
	}
	
	public double getAngle() {
		return ahrs.getYaw();
	}
	
	public void resetAngle() {
		ahrs.zeroYaw();
	}
}
