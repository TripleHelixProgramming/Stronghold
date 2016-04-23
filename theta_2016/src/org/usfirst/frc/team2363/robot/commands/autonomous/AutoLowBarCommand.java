package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.Robot;

/**
 *
 */
public class AutoLowBarCommand extends DriveStraightCommand {

    public AutoLowBarCommand() {
    	super();
    	setTimeout(3);
    }

    @Override
    protected void initialize() {
	   super.initialize();
	   Robot.intake.down();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.arcadeDrive(-.65, getCorrectedTurn());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }
}
