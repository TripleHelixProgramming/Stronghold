package org.usfirst.frc.team2363.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionProcessing extends Subsystem {
    
    NetworkTable table;
    
    public VisionProcessing() {
        table = NetworkTable.getTable("GRIP/myContoursReport");
    }
    
    public double centerX() {
    	double[] defaultValue = new double[0];
		while (true) {
			double[] centerXs = table.getNumberArray("centerX", defaultValue);
			for(double centerX : centerXs) {
				return centerX;
			}
			Timer.delay(1);
		}
    }
    
    public double centerY() {
    	double[] defaultValue = new double[0];
		while (true) {
			double[] centerYs = table.getNumberArray("centerY", defaultValue);
			for(double centerY : centerYs) {
				return centerY;
			}
			Timer.delay(1);
		}
    }
    
    public double area() {
    	double[] defaultValue = new double[0];
		while (true) {
			double[] areas = table.getNumberArray("area", defaultValue);
			for(double area : areas) {
				return area;
			}
			Timer.delay(1);
		}
    }
    
    public double height() {
    	double[] defaultValue = new double[0];
		while (true) {
			double[] heights = table.getNumberArray("height", defaultValue);
			for(double height : heights) {
				return height;
			}
			Timer.delay(1);
		}
    }
    
    public double width() {
    	double[] defaultValue = new double[0];
		while (true) {
			double[] widths = table.getNumberArray("width", defaultValue);
			for(double width : widths) {
				return width;
			}
			Timer.delay(1);
		}
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

