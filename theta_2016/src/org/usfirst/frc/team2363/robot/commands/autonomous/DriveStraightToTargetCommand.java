package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightToTargetCommand extends Command {
	
	private final double power;

    public DriveStraightToTargetCommand() {
    	this(-0.55);
    }
    
    public DriveStraightToTargetCommand(double power) {
        requires(Robot.drivetrain);
        this.power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.arcadeDrive(power, getCorrectedTurn());
    }
    
    protected double getCorrectedTurn() {
    	double power = -(Robot.visionProcessing.getAngleToTarget()) * 0.07;
    	if (power > 0.3 ) {
    		power = 0.3;
    	} else if (power < -0.3) {
    		power = -0.3;
    	}
    	return power;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.visionProcessing.getTargetHeight() > 100;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
