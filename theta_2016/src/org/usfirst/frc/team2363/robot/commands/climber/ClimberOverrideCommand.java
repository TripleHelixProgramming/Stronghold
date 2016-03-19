package org.usfirst.frc.team2363.robot.commands.climber;

import org.usfirst.frc.team2363.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ClimberOverrideCommand extends Command {

    public ClimberOverrideCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Operator Elevator", Robot.oi.getOperatorElevator() / 2);
    	SmartDashboard.putNumber("Operator Angle", Robot.oi.getOperatorAngle());
    	Robot.climber.setAnglePower(Robot.oi.getOperatorAngle() / 2);
    	Robot.climber.setElevatorPower(Robot.oi.getOperatorElevator());
    	
    	SmartDashboard.putBoolean("Climber Override", true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
