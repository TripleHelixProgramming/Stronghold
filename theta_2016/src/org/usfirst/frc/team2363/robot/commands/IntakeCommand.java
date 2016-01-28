package org.usfirst.frc.team2363.robot.commands;

import org.usfirst.frc.team2363.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCommand extends Command {

	private boolean run;
	
    public IntakeCommand(boolean run) {
    	requires(Robot.intake);
    	this.run = run;
    }

    // Called just before this Command runs the first time
    protected void initialize() { }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (run) {
    		Robot.intake.on();
    	} else {
    		Robot.intake.off();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() { }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() { }
}
