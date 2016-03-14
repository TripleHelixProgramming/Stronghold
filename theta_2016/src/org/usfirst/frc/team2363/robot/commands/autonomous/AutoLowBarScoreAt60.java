package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoLowBarScoreAt60 extends DriveStraightCommand {

    public AutoLowBarScoreAt60() {
    	super(58);
    	requires(Robot.drivetrain);
    	setTimeout(10);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.arcadeDrive(-.60, getCorrectedTurn());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut() || Robot.drivetrain.getLeftPosition() >= 135 || Robot.drivetrain.getRightPosition() >= 135;
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
