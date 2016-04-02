package org.usfirst.frc.team2363.robot.subsystems;

import static org.usfirst.frc.team2363.robot.RobotMap.*;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Shooter extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private CANTalon motor1 = new CANTalon(SHOOTER_TALON_1);
	private CANTalon motor2 = new CANTalon(SHOOTER_TALON_2);
//	private Encoder encoder = new Encoder(SHOOTER_ENCODER_A, SHOOTER_ENCODER_B, true, EncodingType.k1X);
	private final DoubleSolenoid hood = new DoubleSolenoid(SHOOTER_HOOD_A, SHOOTER_HOOD_B);
	
	private BangBang bangBang = new BangBang();
	private static final double SPEED = 5000;
	private static final double CONVERTED_SPEED = 60 / (SPEED * 120.0);

	private boolean running;

	public Shooter() {
//		encoder.setSamplesToAverage(120);
//		encoder.setDistancePerPulse(1.0/120);
		motor1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		bangBang.start();
	}

	public void initDefaultCommand() {     	
	}
	
	public void openHood() {
		hood.set(Value.kForward);
	}
	
	public void closeHood() {
		hood.set(Value.kReverse);
	}

	public double getRPM() {
//		if (encoder.getStopped()) {
//			return 0;
//		}
//		return Math.abs(encoder.getRate() * 60.0);
		return Math.abs(motor1.getEncVelocity() / 13.66);
	}

	public void on() {
		running = true;
//		DriverStation.reportError("ON", false);
		//    	motor.set(-1);
	}

	public void off() {
		running = false;
//		DriverStation.reportError("OFF", false);
		//    	motor.set(0);
	}

	public boolean isAtSpeed() {
		return getRPM() > SPEED - 25 && getRPM() < SPEED + 25;
	}

	private class BangBang extends Thread {

		@Override
		public void run() {
			while (true) {
				double rpm = getRPM();
//				DriverStation.reportError("" + encoder.getPeriod(), false);
    			if ((rpm < SPEED || rpm == 0) && running) {
//				if (running) {
					motor1.set(1);
					motor2.set(1);
				} else {
//					motor1.set(0);
//					motor2.set(0);
				}
				try {
					sleep(10);
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
