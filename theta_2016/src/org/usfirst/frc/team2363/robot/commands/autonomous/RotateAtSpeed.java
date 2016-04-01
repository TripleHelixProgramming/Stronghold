package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.Robot;
import org.usfirst.frc.team2363.robot.commands.drivetrain.RollingAverager;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RotateAtSpeed extends PIDCommand {
	
	private final double angle;
	private final double P = 3;
	private final double MAX_SPEED = 30;
	
	private final RollingAverager speedAverage = new RollingAverager(5);

    public RotateAtSpeed(double angle) {
    	super(0, 0.001, 0);
        requires(Robot.drivetrain);
        this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetAngle();
    	this.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	speedAverage.addValue(Robot.drivetrain.getRotationSpeed());
    	
    	double error = (angle - Robot.drivetrain.getGyroAngle()) * P;
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
    	SmartDashboard.putNumber("Robot Angle", Robot.drivetrain.getGyroAngle());
        return Math.abs(Robot.drivetrain.getGyroAngle() - angle) < 0.5;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	protected double returnPIDInput() {
		SmartDashboard.putNumber("Rotation Speed", speedAverage.getAverage());
		return speedAverage.getAverage();
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("Rotation Output", output);
		Robot.drivetrain.arcadeDrive(0, output);
	}
}
