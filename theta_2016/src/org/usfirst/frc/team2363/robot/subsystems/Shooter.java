package org.usfirst.frc.team2363.robot.subsystems;

import static org.usfirst.frc.team2363.robot.RobotMap.*;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Shooter extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private CANTalon motor1 = new CANTalon(SHOOTER_TALON_1);
	private CANTalon motor2 = new CANTalon(SHOOTER_TALON_2);
	private Counter encoder = new Counter(SHOOTER_ENCODER_A);
	private final DoubleSolenoid hood = new DoubleSolenoid(SHOOTER_HOOD_A, SHOOTER_HOOD_B);
	private Relay flashlight = new Relay(FLASHLIGHT_RELAY);
	private Relay cameraLight = new Relay(CAMERA_RELAY);
	
	private BangBang bangBang = new BangBang();
	private static final double SPEED = 6500;
	private static final double CONVERTED_SPEED = 60 / SPEED;

	private boolean running;

	public Shooter() {
//		encoder.setSamplesToAverage(12);
//		encoder.setDistancePerPulse(1.0/120);
		motor1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		bangBang.start();
	}

	public void initDefaultCommand() {     	
	}
	
	public void openHood() {
		hood.set(Value.kForward);
		cameraLight.set(Relay.Value.kForward);
		flashlight.set(Relay.Value.kReverse);
	}
	
	public void closeHood() {
		hood.set(Value.kReverse);
		cameraLight.set(Relay.Value.kReverse);
	}

	public double getRPM() {
		if (encoder.getStopped()) {
			return 0;
		}
		return Math.abs(1/encoder.getPeriod() * 60.0);
//		return Math.abs(motor1.getEncVelocity() / 13.66);
	}
	
	public void killShooter() {
		running = false;
	}

	public void on() {
		running = true;
		openHood();
		flashlight.set(Relay.Value.kForward);
		cameraLight.set(Relay.Value.kForward);
//		DriverStation.reportError("ON", false);
		//    	motor.set(-1);
	}

	public void off() {
		running = false;
		closeHood();
		flashlight.set(Relay.Value.kReverse);
		cameraLight.set(Relay.Value.kReverse);
//		DriverStation.reportError("OFF", false);
		//    	motor.set(0);
	}

	public void flashlightOn() {
		flashlight.set(Relay.Value.kForward);
	}
	
	public void flashlightOff() {
		flashlight.set(Relay.Value.kReverse);
	}
	
	public boolean isAtSpeed() {
		return getRPM() > SPEED - 25 && getRPM() < SPEED + 25;
	}

	private class BangBang extends Thread {

		@Override
		public void run() {
			while (true) {
//				DriverStation.reportError("" + encoder.getPeriod(), false);
    			if ((encoder.getPeriod() > CONVERTED_SPEED || encoder.getStopped()) && running) {
//				if (running) {
					motor1.set(1);
					motor2.set(1);
				} else {
					motor1.set(0);
					motor2.set(0);
				}
				try {
					sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void setPower(double d) {
		motor1.set(d);
		motor2.set(d);
	}
}
