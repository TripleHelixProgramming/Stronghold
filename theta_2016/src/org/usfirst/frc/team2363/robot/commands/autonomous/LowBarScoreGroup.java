package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.commands.drivetrain.BrakeCommand;
import org.usfirst.frc.team2363.robot.commands.drivetrain.RotateTo60;
import org.usfirst.frc.team2363.robot.commands.intake.IntakeMovement;
import org.usfirst.frc.team2363.robot.commands.intake.IntakePosition;
import org.usfirst.frc.team2363.robot.commands.shooter.ShooterCommand;
import org.usfirst.frc.team2363.robot.commands.shooter.ShooterHoodCommand;
import org.usfirst.frc.team2363.robot.subsystems.Intake.IntakeState;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LowBarScoreGroup extends CommandGroup {
    
    public  LowBarScoreGroup() {
    	addSequential(new IntakePosition(true));
    	addSequential(new WaitCommand(1));
    	addSequential(new AutoLowBarScore1(), 6);
    	addSequential(new BrakeCommand(true));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new BrakeCommand(false));
    	addParallel(new ShooterHoodCommand(true));
    	addParallel(new IntakePosition(false));
    	addSequential(new RotateTo60());
    	addSequential(new BrakeCommand(true));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new BrakeCommand(false));
    	addSequential(new DriveStraightToTargetCommand(), 5);
    	addSequential(new BrakeCommand(true));
    	addParallel(new ShooterCommand(true));
    	addSequential(new WaitCommand(2));
    	addSequential(new IntakeMovement(IntakeState.IN));
//    	addSequential(new IntakeMovement(IntakeState.OUT));
    }
}
