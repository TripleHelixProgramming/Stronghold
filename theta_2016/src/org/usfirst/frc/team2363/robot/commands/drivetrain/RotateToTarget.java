package org.usfirst.frc.team2363.robot.commands.drivetrain;

import org.usfirst.frc.team2363.robot.Robot;

public class RotateToTarget extends RotateAtSpeed {
	
	public void initialize() {
		super.initialize();
		this.setAngle(Robot.visionProcessing.getAngleToTarget());
	}
}
