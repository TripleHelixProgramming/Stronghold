package org.usfirst.frc.team2363.robot.commands.drivetrain;


public class RotateTo60 extends RotateAtSpeed {
	
	public void initialize() {
		super.initialize();
		this.setAngle(60);
	}
}
