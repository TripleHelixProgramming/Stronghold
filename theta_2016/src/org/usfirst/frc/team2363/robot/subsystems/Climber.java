package org.usfirst.frc.team2363.robot.subsystems;

import static org.usfirst.frc.team2363.robot.RobotMap.*;

import org.usfirst.frc.team2363.robot.commands.climber.ClimberCommand;
import org.usfirst.frc.team2363.robot.commands.climber.ClimberNothingCommand;
import org.usfirst.frc.team2363.robot.commands.climber.ClimberOverrideCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
    
	//Talons
	private CANTalon angleMotor = new CANTalon(CLIMBER_ANGLE);
	private CANTalon elevatorMotorA = new CANTalon(CLIMBER_ELEVATOR_A);
	private CANTalon elevatorMotorB = new CANTalon(CLIMBER_ELEVATOR_B);
	
	//Pneumatics
//	private DoubleSolenoid solenoid = new DoubleSolenoid(CLIMBER_PNEUMATICS_EXTEND, CLIMBER_PNEUMATICS_RETRACT);
	
	//Encoders
	private Encoder elevatorEncoder = new Encoder(CLIMBER_ELEVATOR_ENCODER_A, CLIMBER_ELEVATOR_ENCODER_B, true, EncodingType.k4X);
	
	public Climber() {
		angleMotor.configEncoderCodesPerRev(1);
		angleMotor.reverseSensor(true);
		angleMotor.setEncPosition(0);
		elevatorEncoder.setDistancePerPulse(0.0082);
	}
    
    public void setAnglePower(double power) {
    	angleMotor.set(power);
    }

    public void setElevatorPower(double power) {
    	if (elevatorMotorA.getOutputCurrent() < 100
    			&&  elevatorMotorB.getOutputCurrent() < 100) {
    		elevatorMotorA.set(-power);
    		elevatorMotorB.set(power);
    	} else {
    		elevatorMotorA.set(0);
    		elevatorMotorB.set(0);
    	}
    }
    
//    private void hookExtend() {
//    	solenoid.set(Value.kForward);
//    }
    
//    private void hookRetract() {
//    	solenoid.set(Value.kReverse);
//    }
    
/*    public void hookToggle() {
    	if (solenoid.get() == Value.kForward) {
    		hookRetract();
    	} else {
    		hookExtend();
    	}
    }
*/    
    public boolean isUp() {
    	return getAngle() > 105;
    }
    
    public boolean isClear() {
    	return getAngle() > 17;
    }
    
    public boolean isDown() {
    	return getAngle() < 5;
    }
    
    public double getAngle() {
    	return angleMotor.getPosition() / (23.75 * 1024) * 360;
    }
    
    public boolean isExtended() {
    	return elevatorEncoder.getDistance() > 2.15;
    }
    
    public boolean isRetracted() {
    	return elevatorEncoder.getDistance() < 0.5;
    }
    
    public double getExtendDistance() {
    	return elevatorEncoder.getDistance();
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ClimberOverrideCommand());
    }
    
    public double getClimberCurrent() {
    	return Math.max(elevatorMotorA.getOutputCurrent(), elevatorMotorB.getOutputCurrent());
    }
}

