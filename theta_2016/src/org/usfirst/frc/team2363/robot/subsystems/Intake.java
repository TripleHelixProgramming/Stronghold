package org.usfirst.frc.team2363.robot.subsystems;

import static org.usfirst.frc.team2363.robot.RobotMap.*;

import org.usfirst.frc.team2363.robot.Robot;
import org.usfirst.frc.team2363.robot.commands.intake.IntakeCommand;
import org.usfirst.frc.team2363.robot.commands.intake.IntakeMovement;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	
	public enum IntakeState {
		IN,
		OUT,
		OFF
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon motor = new CANTalon(INTAKE_TALON);
	private DoubleSolenoid solenoid = new DoubleSolenoid(INTAKE_SOLENOID_A, INTAKE_SOLENOID_B);
	private DigitalInput ballLimit = new DigitalInput(BALL_LIMIT_CHANNEL);
	private boolean IS_UP = true;
	
	public void in() {
		motor.set(-1);
    }
	
	public void out() {
		motor.set(1);
	}
    
    public void off() {
    	motor.set(0);
    }
    
    public void up() {
    	solenoid.set(Value.kForward);
    	IS_UP = true;
    }
   
    public void down() {
    	solenoid.set(Value.kReverse);
    	IS_UP = false;
    }
    
    public boolean isUp() {
    	return IS_UP;
    }
    
    public boolean hasBall() {
    	return !ballLimit.get();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new IntakeMovement(IntakeState.OFF));
    }
}

