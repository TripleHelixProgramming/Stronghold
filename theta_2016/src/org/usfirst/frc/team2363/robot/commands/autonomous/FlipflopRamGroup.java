package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.commands.drivetrain.BrakeCommand;
import org.usfirst.frc.team2363.robot.commands.intake.IntakePosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class FlipflopRamGroup extends CommandGroup {
    
    public  FlipflopRamGroup() {
    	addSequential(new IntakePosition(false));
//    	addSequential(new AutoFlipflop());
    	addSequential(new DriveStraightCommand(0, -0.5), 2);
//    	addSequential(new BrakeCommand(true));
    	addSequential(new IntakePosition(true));
    	addSequential(new AutoFlipflopReverse());
    	addSequential(new WaitCommand(2));
//    	addSequential(new BrakeCommand(false));
    	addSequential(new DriveStraightCommand(0, -0.75), 2);
    }
}
