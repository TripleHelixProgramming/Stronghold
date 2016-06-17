package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.commands.intake.IntakePosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LowBarGroup extends CommandGroup {
    
    public  LowBarGroup() {
    	addSequential(new IntakePosition(true));
    	addSequential(new WaitCommand(2));
    	addSequential(new AutoLowBarCommand());
    }
}
