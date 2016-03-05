package org.usfirst.frc.team2363.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import static org.usfirst.frc.team2363.robot.Robot.drivetrain;

import org.usfirst.frc.team2363.robot.commands.autonomous.AutoFlipflop;
import org.usfirst.frc.team2363.robot.commands.autonomous.AutoLowBarCommand;
import org.usfirst.frc.team2363.robot.commands.autonomous.AutoMote;
import org.usfirst.frc.team2363.robot.commands.autonomous.AutoRampart;
import org.usfirst.frc.team2363.robot.commands.autonomous.AutoRockWall;
import org.usfirst.frc.team2363.robot.commands.autonomous.AutoRoughTerrain;
import org.usfirst.frc.team2363.robot.commands.autonomous.FlipflopGroup;
import org.usfirst.frc.team2363.robot.commands.autonomous.LowBarScoreGroup;
import org.usfirst.frc.team2363.robot.commands.autonomous.PortGroup;
import org.usfirst.frc.team2363.robot.commands.autonomous.TestGyroCommand;
import org.usfirst.frc.team2363.robot.commands.drivetrain.JoystickDrive;
import org.usfirst.frc.team2363.robot.commands.shooter.ShooterCommand;
import org.usfirst.frc.team2363.robot.subsystems.Climber;
import org.usfirst.frc.team2363.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2363.robot.subsystems.Intake;
import org.usfirst.frc.team2363.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static Drivetrain drivetrain;
	public static Shooter shooter;
	public static PowerDistributionPanel pdp;
	public static Intake intake;
	public static Climber climber;

    Command autonomousCommand;
    SendableChooser chooser;
    CameraServer server;
    
    public Robot() {
    	drivetrain = new Drivetrain();
    	shooter = new Shooter();
    	intake = new Intake();
    	pdp = new PowerDistributionPanel();
    	chooser = new SendableChooser();
    	server = CameraServer.getInstance();
        server.setQuality(50);
        server.startAutomaticCapture("cam0");
        climber = new Climber();
	}
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		SmartDashboard.putData(Scheduler.getInstance());
		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(shooter);
		SmartDashboard.putData(intake);
		
		chooser.addObject("rough terrain autonomous", new AutoRoughTerrain());
		chooser.addObject("low bar autonomous", new AutoLowBarCommand());
		chooser.addObject("rock wall autonomous", new AutoRockWall());
		chooser.addObject("mote autonomous", new AutoMote());
		chooser.addObject("flipflop autonomous", new FlipflopGroup());
		chooser.addObject("rampart autonomous", new AutoRampart());
		chooser.addObject("port autonomous", new PortGroup());
		chooser.addObject("low bar shoot autonomous", new LowBarScoreGroup());
		chooser.addDefault("Default", new JoystickDrive());
		
		SmartDashboard.putData("autonomous chooser", chooser);
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    	shooter.off();
    }
	
	public void disabledPeriodic() {
		Robot.oi.turnOffRumble();
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("POV", Robot.oi.getPOV());
		SmartDashboard.putNumber("AccelZ", drivetrain.getAccelZ());
		SmartDashboard.putNumber("AccelX", drivetrain.getAccelX());
		SmartDashboard.putNumber("AccelY", drivetrain.getAccelY());
		SmartDashboard.putNumber("Climber Angle", Robot.climber.getAngle());
		SmartDashboard.putNumber("Climber Extend Distance", Robot.climber.getExtendDistance());
		SmartDashboard.putNumber("Climber Angle", Robot.climber.getAngle());
		SmartDashboard.putNumber("Climber Extend Distance", Robot.climber.getExtendDistance());
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
        
        Robot.drivetrain.resetEncoders();
        Robot.drivetrain.brakeDisengage();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("Angle", Robot.drivetrain.getAngle());
        SmartDashboard.putNumber("Left DT Position", Robot.drivetrain.getLeftPosition());
		SmartDashboard.putNumber("Right DT Position", Robot.drivetrain.getRightPosition());
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        Robot.drivetrain.resetEncoders();
        Robot.drivetrain.brakeDisengage();
        Robot.climber.state = false;
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        if (shooter.isAtSpeed()) {
        	Robot.oi.turnOnRumble();
        } else {
        	Robot.oi.turnOffRumble();
        }
        SmartDashboard.putNumber("Shooter RPM", shooter.getRPM());
        SmartDashboard.putNumber("Shooter Current", pdp.getCurrent(3));
        SmartDashboard.putNumber("Front Left Drive Motor", pdp.getCurrent(0));
        SmartDashboard.putNumber("Front Right Drive Motor", pdp.getCurrent(1));
        SmartDashboard.putNumber("Rear Left Drive Motor", pdp.getCurrent(2));
        SmartDashboard.putNumber("Rear Right Drive Motor", pdp.getCurrent(3));
        SmartDashboard.putBoolean("Has Ball", intake.hasBall());
        SmartDashboard.putBoolean("Intake Override", oi.getIntakeOverride());
		SmartDashboard.putNumber("Left DT Position", Robot.drivetrain.getLeftPosition());
		SmartDashboard.putNumber("Right DT Position", Robot.drivetrain.getRightPosition());
		SmartDashboard.putNumber("Angle", Robot.drivetrain.getAngle());
		SmartDashboard.putNumber("AccelZ", drivetrain.getAccelZ());
		SmartDashboard.putNumber("AccelX", drivetrain.getAccelX());
		SmartDashboard.putNumber("AccelY", drivetrain.getAccelY());
		SmartDashboard.putNumber("Tilt", drivetrain.getTilt());
		SmartDashboard.putBoolean("Climber State", Robot.climber.state);
		SmartDashboard.putBoolean("Drivetrain Brake", Robot.drivetrain.isBrakeEngaged());
		SmartDashboard.putNumber("Climber Angle", Robot.climber.getAngle());
		SmartDashboard.putNumber("Climber Extend Distance", Robot.climber.getExtendDistance());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
