package org.usfirst.frc.team2363.robot.commands.drivetrain;

import org.usfirst.frc.team2363.robot.Robot;

public class RotateToTarget extends RotateAtSpeed {
	
	public void initialize() {
		super.initialize();
		if (Robot.visionProcessing.getAngleToTarget().isPresent()) {
			setAngle(Robot.visionProcessing.getAngleToTarget().get());
		} else {
			setAngle(0);
		}
	}
}
