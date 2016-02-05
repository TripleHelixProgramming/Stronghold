package org.usfirst.frc.team2363.robot.commands.intake;

import org.usfirst.frc.team2363.robot.Robot;
import org.usfirst.frc.team2363.robot.subsystems.Intake.IntakeState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCommand extends Command {

	private IntakeState run;
	private boolean down;
	
    public IntakeCommand(IntakeState run, boolean down) {
    	requires(Robot.intake);
    	this.run = run;
    	this.down = down;
    }

    // Called just before this Command runs the first time
    protected void initialize() { }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (run == IntakeState.IN) {
    		Robot.intake.in();
    	} else if (run == IntakeState.OUT) {
    		Robot.intake.out();
    	} else {
    		Robot.intake.off();
    	}
    	
    	if (down) {
    		Robot.intake.down();
    	} else {
    		Robot.intake.up();
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
