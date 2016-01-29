package org.usfirst.frc.team2363.robot.subsystems;

import static org.usfirst.frc.team2363.robot.RobotMap.*;

import org.usfirst.frc.team2363.robot.commands.IntakeCommand;
import org.usfirst.frc.team2363.robot.commands.IntakeMovement;

import edu.wpi.first.wpilibj.CANTalon;
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
    }
   
    public void down() {
    	solenoid.set(Value.kReverse);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new IntakeMovement(IntakeState.OFF));
    }
}

