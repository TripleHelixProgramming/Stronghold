package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.commands.intake.IntakePosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LowBarScoreGroup extends CommandGroup {
    
    public  LowBarScoreGroup() {
    	addSequential(new IntakePosition(true));
    	addSequential(new WaitCommand(1));
    	addSequential(new AutoLowBarScore1());
    	addSequential(new AutoRotateTo60());
    	 addSequential(new AutoLowBarScoreAt60());
    }
}
