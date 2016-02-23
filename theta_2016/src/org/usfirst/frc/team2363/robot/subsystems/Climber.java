package org.usfirst.frc.team2363.robot.subsystems;

import static org.usfirst.frc.team2363.robot.RobotMap.*;

import org.usfirst.frc.team2363.robot.commands.climber.ClimberCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
    
	//Talons
	private SpeedController angleMotor = new CANTalon(CLIMBER_ANGLE);
	private SpeedController elevatorMotorA = new CANTalon(CLIMBER_ELEVATOR_A);
	private SpeedController elevatorMotorB = new CANTalon(CLIMBER_ELEVATOR_B);
	
	//Pneumatics
	private DoubleSolenoid solenoid = new DoubleSolenoid(CLIMBER_PNEUMATICS_EXTEND, CLIMBER_PNEUMATICS_RETRACT);
	
	//Toggle State Variable
	public boolean state;
    
    public void setAnglePower(double power) {
    	angleMotor.set(power);
    }
    
    public void setElevatorPower(double power) {
    	elevatorMotorA.set(-power);
    	elevatorMotorB.set(power);
    	//possible scaling equation: ((x/5)^3)*1.25*100
    }
    
    private void hookExtend() {
    	solenoid.set(Value.kForward);
    }
    
    private void hookRetract() {
    	solenoid.set(Value.kReverse);
    }
    
    public void hookToggle() {
    	if (solenoid.get() == Value.kForward) {
    		hookRetract();
    	} else {
    		hookExtend();
    	}
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ClimberCommand());
    }
}

