package org.usfirst.frc.team2363.robot.commands.climber;

import org.usfirst.frc.team2363.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberCommand extends Command {

    public ClimberCommand() {
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.climber.state && !Robot.intake.isUp()) {
    		Robot.climber.setAnglePower(Robot.oi.getOperatorAngle());
//    		if (Robot.climber.isClear()) {
    			Robot.climber.setElevatorPower(Robot.oi.getOperatorElevator());
//    		} else {
//    			Robot.climber.setElevatorPower(0);
//    		}
    	} else {
    		Robot.climber.setAnglePower(0);
    		Robot.climber.setElevatorPower(0);
    	}
//    	if (Robot.oi.getHookToggle() && Robot.climber.state) {
//    		Robot.climber.hookToggle();
//    	}
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
