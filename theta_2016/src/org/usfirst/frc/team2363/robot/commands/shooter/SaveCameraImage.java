package org.usfirst.frc.team2363.robot.commands.shooter;

import org.usfirst.frc.team2363.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SaveCameraImage extends Command {

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.visionProcessing.saveCurrentImage();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

	@Override
	protected void execute() {}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}
}
