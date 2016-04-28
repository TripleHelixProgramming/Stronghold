package org.usfirst.frc.team2363.robot.subsystems;

import java.util.Date;
import java.util.Optional;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 *
 */
public class VisionProcessing {

	private final NetworkTable table;
	private final CameraServer server;
	private final USBCamera camera;
	private final AxisCamera visionCamera;

	private final int RES_X = 240;
	private final double VIEWING_ANGLE = 67;

	public VisionProcessing() {
		table = NetworkTable.getTable("GRIP/myContoursReport");
		camera = new USBCamera("cam0");
		camera.setBrightness(0);

		visionCamera = new AxisCamera("10.23.63.100");

		server = CameraServer.getInstance();
		server.setQuality(50);
		server.startAutomaticCapture(camera);
	}

	//    public double centerX() {
	//    	double[] defaultValue = new double[0];
	//		while (true) {
	//			double[] centerXs = table.getNumberArray("centerX", defaultValue);
	//			for(double centerX : centerXs) {
	//				return centerX;
	//			}
	//			Timer.delay(1);
	//		}
	//    }
	//    
	//    public double centerY() {
	//    	double[] defaultValue = new double[0];
	//		while (true) {
	//			double[] centerYs = table.getNumberArray("centerY", defaultValue);
	//			for(double centerY : centerYs) {
	//				return centerY;
	//			}
	//			Timer.delay(1);
	//		}
	//    }
	//    
	//    public double area() {
	//    	double[] defaultValue = new double[0];
	//		while (true) {
	//			double[] areas = table.getNumberArray("area", defaultValue);
	//			for(double area : areas) {
	//				return area;
	//			}
	//			Timer.delay(1);
	//		}
	//    }
	//    
	//    public double height() {
	//    	double[] defaultValue = new double[0];
	//		while (true) {
	//			double[] heights = table.getNumberArray("height", defaultValue);
	//			for(double height : heights) {
	//				return height;
	//			}
	//			Timer.delay(1);
	//		}
	//    }
	//    
	//    public double width() {
	//    	double[] defaultValue = new double[0];
	//		while (true) {
	//			double[] widths = table.getNumberArray("width", defaultValue);
	//			for(double width : widths) {
	//				return width;
	//			}
	//			Timer.delay(1);
	//		}
	//    }

	private int getLargestIndex() {
		double[] defaultValue = new double[0];
		double largestArea = 0;
		int largestIndex = -1;
		double[] areas = table.getNumberArray("area", defaultValue);
		for(int i = 0; i < areas.length; i++) {
			if (areas[i] > largestArea)
				largestArea = areas[i];
			largestIndex = i;
		}
		return largestIndex;
	}

	public double centerX() {
		double[] defaultValue = new double[0];
		double largestArea = 0;
		int largestIndex = 0;
		double[] areas = table.getNumberArray("area", defaultValue);
		if (areas.length == 0) {
			return -1;
		}
		for(int i = 0; i < areas.length; i++) {
			if (areas[i] > largestArea)
				largestArea = areas[i];
			largestIndex = i;
		}
		return table.getNumberArray("centerX", defaultValue)[largestIndex] - (table.getNumberArray("width", defaultValue)[largestIndex]) / 2;
	}

	public Optional<Double> getAngleToTarget() {
		try {
			double centerX = centerX();
			if (centerX == -1) {
				return Optional.empty();
			}
			SmartDashboard.putNumber("CenterX", centerX);
			return Optional.of(((centerX / RES_X) * VIEWING_ANGLE) - (VIEWING_ANGLE / 2));
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	public Optional<Double> getTargetHeight() {
		try {
			double[] defaultValue = new double[0];
			int index = getLargestIndex();
			if (index == -1) {
				return Optional.empty();
			}
			double height = table.getNumberArray("centerY", defaultValue)[index];
			SmartDashboard.putNumber("Target Height", height);
			return Optional.of(height);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	public void saveCurrentImage() {
		try {
			NIVision.RGBValue rgbValues = new NIVision.RGBValue();
			Image currentImage = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
			visionCamera.getImage(currentImage);
			NIVision.imaqWriteFile(currentImage, "/home/lvuser/image" + new Date() + ".jpg", rgbValues);
		} catch (Exception e) { }
	}
}

