package org.usfirst.frc.team2363.robot.subsystems;

import static org.usfirst.frc.team2363.robot.RobotMap.*;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Shooter extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon motor = new CANTalon(SHOOTER_TALON);
	private Encoder encoder = new Encoder(SHOOTER_ENCODER, 9, false, EncodingType.k1X);
	private BangBang bangBang = new BangBang();
	private static final int SPEED = 5000;
	private static final int CONVERTED_SPEED = SPEED / 60 / 1000 / 360;
	
	private boolean running;
	
	public Shooter() {
		encoder.setSamplesToAverage(120);
		encoder.setDistancePerPulse(1.0/360);
		bangBang.start();
	}

    public void initDefaultCommand() {     	
    }
    
    public double getRPM() {
    	return encoder.getRate() * 60;
    }
    
    public void on() {
    	running = true;
    	motor.set(-1);
    }
    
    public void off() {
    	running = false;
    	motor.set(0);
    }
    
    public boolean isAtSpeed() {
    	if (getRPM() > SPEED - 25 && getRPM() < SPEED + 25) {
    		return false;
    	} else {
    		return true;
    	}
    }
    
    private class BangBang extends Thread {

/*/    	@Override
    	public void run() {
    		while (true) {
    			if (encoder.getPeriod() < CONVERTED_SPEED && running) {
    				if (running) {
    					motor.set(-0.5);
    				} else {
    					motor.set(0);
    				}
    			try {
    				sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
   			}
    		}
    	}
*/    }
}

