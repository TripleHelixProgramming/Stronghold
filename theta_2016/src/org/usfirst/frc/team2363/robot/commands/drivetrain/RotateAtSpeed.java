package org.usfirst.frc.team2363.robot.commands.drivetrain;

import org.usfirst.frc.team2363.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RotateAtSpeed extends PIDCommand {
	
	private double angle;
	private final double P = 3;
	private final double MAX_SPEED = 40;
	
    public RotateAtSpeed() {
    	super(0, 0.001, 0);
        requires(Robot.drivetrain);
    }
    
    protected void setAngle(double angle) {
    	 this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetAngle();
//    	this.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = (angle - Robot.drivetrain.getAngle()) * P;
    	if (error > MAX_SPEED) {
    		setSetpoint(MAX_SPEED); }
    	else if (error < -MAX_SPEED) {
    		setSetpoint(-MAX_SPEED);
    	} else {
    		setSetpoint(error);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putNumber("Robot Angle", Robot.drivetrain.getAngle());
        return Math.abs(Robot.drivetrain.getAngle() - angle) < 0.5;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.arcadeDrive(0, 0);
    }

	@Override
	protected double returnPIDInput() {
		SmartDashboard.putNumber("Rotation Speed", Robot.drivetrain.getRotationSpeed());
		return Robot.drivetrain.getRotationSpeed();
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("Rotation Output", output);
		Robot.drivetrain.arcadeDrive(0, output);
	}
}
