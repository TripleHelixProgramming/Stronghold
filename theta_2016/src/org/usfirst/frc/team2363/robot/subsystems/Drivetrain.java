package org.usfirst.frc.team2363.robot.subsystems; 

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
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
	private DoubleSolenoid brake = new DoubleSolenoid(BRAKE_CHANNEL_A, BRAKE_CHANNEL_B);
	
	private RobotDrive robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
	
	private AHRS ahrs;
	private Gyro gyro;
	
	public Drivetrain() {
		try {
            ahrs = new AHRS(SPI.Port.kMXP); 
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }
		leftEncoder.setDistancePerPulse(0.05947 * 1.206935);
		leftEncoder.setSamplesToAverage(12);
		leftEncoder.setMinRate(15);

		rightEncoder.setDistancePerPulse(0.05947 * 1.206935);
		rightEncoder.setSamplesToAverage(12);
		rightEncoder.setMinRate(15);
		
		gyro = new ADXRS450_Gyro();
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

	public boolean isLeftMoving() {
		return !leftEncoder.getStopped();
	}

	public boolean isRightMoving() {
		return !rightEncoder.getStopped();
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
		return (ahrs.getYaw() + gyro.getAngle()) / 2;
	}
	
	public double getGyroAngle() {
		return gyro.getAngle();
	}
	
	public void resetAngle() {
		ahrs.zeroYaw();
		gyro.reset();
	}
	
	public double getAccelZ() {
		return ahrs.getVelocityZ();
	}
	
	public double getRotationSpeed() {
		return gyro.getRate();
	}
	
	public double getAccelX() {
		return ahrs.getVelocityX();
	}
	
	public double getAccelY() {
		return ahrs.getVelocityY();
	}
	
	public double getTilt() {
		return ahrs.getPitch();
	}
	
	public boolean isTilted() {
		return getTilt() > 1 || getTilt() < -1;
	}
    public void brakeEngage() {
    	brake.set(Value.kForward);
    }
    
    public void brakeDisengage() {
    	brake.set(Value.kReverse);
    }
    
    public boolean isBrakeEngaged() {
    	return brake.get() == Value.kForward;
    }
}
