package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.commands.drivetrain.BrakeCommand;
import org.usfirst.frc.team2363.robot.commands.intake.IntakePosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class PortGroup extends CommandGroup {
    
    public  PortGroup() {
    	addSequential(new IntakePosition(true));
    	addSequential(new WaitCommand(2));
    	addSequential(new AutoPort(), 2);
    	addSequential(new BrakeCommand(true));
    	addSequential(new IntakePosition(false));
    	addSequential(new BrakeCommand(false));
    	addSequential(new DriveStraightCommand(), 3);
    }
}
