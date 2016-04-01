package org.usfirst.frc.team2363.robot.subsystems;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 *
 */
public class VisionProcessing {
    
    private final NetworkTable table;
    private final CameraServer server;
    private final USBCamera camera;
    
    private final int RES_X = 240;
    private final double VIEWING_ANGLE = 61;
    
    public VisionProcessing() {
        table = NetworkTable.getTable("GRIP/myContoursReport");
        camera = new USBCamera("cam0");
        camera.setBrightness(0);
        
        server = CameraServer.getInstance();
        server.setQuality(50);
        server.startAutomaticCapture(camera);
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
    
    public double getAngleToTarget() {
    	return ((centerX() / RES_X) * VIEWING_ANGLE) - (VIEWING_ANGLE / 2);
    }
}

