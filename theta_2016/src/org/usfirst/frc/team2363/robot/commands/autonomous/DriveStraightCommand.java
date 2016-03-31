package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightCommand extends Command {
	
	private final int angle;
	private final double power;

    public DriveStraightCommand() {
    	this(-0.55);
    }
    
    public DriveStraightCommand(double power) {
    	this(0, power);
    }
    
    public DriveStraightCommand(int angle, double power) {
        requires(Robot.drivetrain);
        this.power = power;
        this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.arcadeDrive(power, getCorrectedTurn());
    }
    
    protected double getCorrectedTurn() {
    	return -(Robot.drivetrain.getAngle() - angle) * 0.07;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
