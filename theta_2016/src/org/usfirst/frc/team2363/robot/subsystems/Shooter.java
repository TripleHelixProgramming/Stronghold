package org.usfirst.frc.team2363.robot.subsystems;

import static org.usfirst.frc.team2363.robot.RobotMap.*;

import org.usfirst.frc.team2363.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Shooter extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon motor = new CANTalon(SHOOTER_TALON);
	private Encoder encoder = new Encoder(SHOOTER_ENCODER_A, SHOOTER_ENCODER_B, false, EncodingType.k1X);
	private BangBang bangBang = new BangBang();
	private static final double SPEED = 5000;
	private static final double CONVERTED_SPEED = SPEED / 60.0 / 100.0 / 360.0;
	
	private boolean running;
	
	public Shooter() {
		encoder.setSamplesToAverage(120);
		encoder.setDistancePerPulse(1.0/120);
		bangBang.start();
	}

    public void initDefaultCommand() {     	
    }
    
    public double getRPM() {
    	return encoder.getRate() * 60.0;
    }
    
    public void on() {
    	running = true;
    	DriverStation.reportError("ON", false);
//    	motor.set(-1);
    }
    
    public void off() {
    	running = false;
    	DriverStation.reportError("OFF", false);
//    	motor.set(0);
    }
    
    public boolean isAtSpeed() {
    	if (getRPM() > SPEED - 25 && getRPM() < SPEED + 25) {
    		Robot.oi.turnOnRumble();
    		return false;    		
    	} else {
    		Robot.oi.turnOffRumble();
    		return true;
    		
    	}
    }

    private class BangBang extends Thread {

    	@Override
    	public void run() {
    		while (true) {
    			SmartDashboard.putNumber("Period", encoder.getPeriod());
//    			if (encoder.getPeriod() < CONVERTED_SPEED && running) {
    				if (running) {
    					motor.set(-1);
    				} else {
    					motor.set(0);
    				}
    				try {
    					sleep(10);
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    				}
//    			}
    		}
    	}
    }
}
