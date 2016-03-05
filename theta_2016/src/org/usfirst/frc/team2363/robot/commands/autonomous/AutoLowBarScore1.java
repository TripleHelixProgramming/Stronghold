package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.Robot;


/**
 *
 */
public class AutoLowBarScore1 extends DriveStraightCommand {

    public AutoLowBarScore1() {
    	requires(Robot.drivetrain);
    	setTimeout(10);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	super.initialize();
    	Robot.drivetrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.arcadeDrive(-.60, getCorrectedTurn());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut() || Robot.drivetrain.getLeftPosition() >= 150 || Robot.drivetrain.getRightPosition() >= 150;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	Robot.intake.down();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
