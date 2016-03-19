package org.usfirst.frc.team2363.robot.commands.climber;

import org.usfirst.frc.team2363.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ClimberCommand extends Command {
	
	private final double stallCurrent = 70;
	private boolean stalled;

    public ClimberCommand() {
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if (!Robot.intake.isUp()) {
//    		Robot.climber.setAnglePower(Robot.oi.getOperatorAngle() / 2);
//    		if (!Robot.climber.isClear() 
//    				|| (Robot.climber.isExtended() &&  Robot.oi.getOperatorElevator() < 0 
//    				|| Robot.climber.isRetracted() && Robot.oi.getOperatorElevator() > 0)) {
//    			Robot.climber.setElevatorPower(0);
//    		} else {
//    			Robot.climber.setElevatorPower(Robot.oi.getOperatorElevator());
//    		}
//    	} else {
//    		Robot.climber.setAnglePower(0);
//    		Robot.climber.setElevatorPower(0);
//    	}
    	
    	if (Robot.climber.getClimberCurrent() > stallCurrent) {
    		stalled = true;
    	}
    	
    	if (Math.abs(Robot.oi.getOperatorElevator()) < 0.5 && Robot.climber.getClimberCurrent() < stallCurrent) {
    		stalled = false;
    	}
    	
    	if (!Robot.intake.isUp()) {
    		Robot.climber.setAnglePower(Robot.oi.getOperatorAngle() / 2);
    		if (!Robot.climber.isClear() || stalled) {
    			Robot.climber.setElevatorPower(0);
    		} else {
    			Robot.climber.setElevatorPower(Robot.oi.getOperatorElevator());
    		}
    	} else {
    		Robot.climber.setAnglePower(0);
    		Robot.climber.setElevatorPower(0);
    	}
    	
    	SmartDashboard.putBoolean("Climber Override", false);
    	SmartDashboard.putBoolean("Climber State", true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
