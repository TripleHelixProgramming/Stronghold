package org.usfirst.frc.team2363.robot.commands.intake;

import org.usfirst.frc.team2363.robot.Robot;
import org.usfirst.frc.team2363.robot.subsystems.Intake.IntakeState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeMovement extends Command {

	private IntakeState run;
	
    public IntakeMovement(IntakeState run) {
    	requires(Robot.intake);
    	this.run = run;
    }

    // Called just before this Command runs the first time
    protected void initialize() { }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (run == IntakeState.IN) {
    		if (!Robot.intake.isUp() && !Robot.intake.hasBall() || Robot.intake.isUp()) {
    			Robot.intake.in();
    		} else {
    			Robot.intake.off();
    		}
    	} else if (run == IntakeState.OUT && !Robot.intake.isUp()) {
    		Robot.intake.out();
    	} else {
    		Robot.intake.off();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() { }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() { }
}
