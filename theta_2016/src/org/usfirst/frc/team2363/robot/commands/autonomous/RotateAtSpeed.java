package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RotateAtSpeed extends PIDCommand {

    public RotateAtSpeed() {
    	super(0, 0.001, 0);
        requires(Robot.drivetrain);
        setSetpoint(30);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetAngle();
    	this.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putNumber("Robot Angle", Robot.drivetrain.getGyroAngle());
        return Robot.drivetrain.getGyroAngle() > 60;
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
		SmartDashboard.putNumber("Rotation Speed", Robot.drivetrain.getRotationSpeed());
		return Robot.drivetrain.getRotationSpeed();
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("Rotation Output", output);
		Robot.drivetrain.arcadeDrive(0, output);
	}
}
