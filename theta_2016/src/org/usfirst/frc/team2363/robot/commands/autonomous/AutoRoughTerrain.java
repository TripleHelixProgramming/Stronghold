package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.Robot;

/**
 *
 */
public class AutoRoughTerrain extends DriveStraightCommand {

    public AutoRoughTerrain() {
    	super();
    	setTimeout(2);
    }

    @Override
    protected void execute() {
    	Robot.drivetrain.arcadeDrive(-.75, getCorrectedTurn());
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
}
