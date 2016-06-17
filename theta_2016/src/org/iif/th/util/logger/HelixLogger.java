package org.iif.th.util.logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedController;

public class HelixLogger {
	
	private final Map<String, SpeedController> speedControllers = new HashMap<>();
	private final Map<String, GenericHID> hids = new HashMap<>();
	private final PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	private final Path file;
	private boolean savedTitles;
	
	public HelixLogger() {
		file = Paths.get("/home/lvuser/Log0.txt");
		try {
			cleanUpFiles();
			Files.createFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void cleanUpFiles() throws IOException {
		Files.deleteIfExists(Paths.get("/home/lvuser/Log4.txt"));
		for (int i = 3; i >= 0; i--) {
			Path oldFile = Paths.get("/home/lvuser/Log" + i + ".txt");
			if (Files.exists(oldFile)) {
				Files.move(oldFile, oldFile.resolveSibling("/home/lvuser/Log" + (i + 1) + ".txt"));
			}
		}
	}
	
	private void saveTitles() throws IOException {
		StringBuilder titles = new StringBuilder();
		titles.append("Timestamp\t");
		titles.append(getPdpTitles()).append("\t");
		titles.append(getTitles(hids)).append("\t");
		titles.append(getTitles(speedControllers)).append("\t");
		Files.write(file, Collections.singletonList(titles.toString()), StandardOpenOption.APPEND);
	}
	
	private String getTitles(Map<String, ?> map) {
		return String.join("\t", map.keySet());
	}
	
	private String getPdpTitles() {
		StringBuilder titles = new StringBuilder();
		for (int i = 0; i < 16; i++) {
			if (titles.length() != 0) {
				titles.append("\t");
			}
			titles.append("PDP").append(i);
		}
		return titles.toString();
	}
	
	public void addSpeedController(String name, SpeedController controller) {
		speedControllers.put(name, controller);
	}
	
	public void addHID(String name, GenericHID hid) {
		hids.put(name, hid);
	}

	public void saveLogs() {
		try {
			if (!savedTitles) {
				saveTitles();
				savedTitles = true;
			}
			StringBuilder data = new StringBuilder();
			data.append(Instant.now().toString()).append("\t");
			data.append(getPdpCurrents());
			data.append(getSpeedControllerValues());
			
			Files.write(file, Collections.singletonList(data.toString()), StandardOpenOption.APPEND);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getSpeedControllerValues() {
		speedControllers.values().stream().map(value -> value.get());
		StringBuilder data = new StringBuilder();
		for (SpeedController controller : speedControllers.values()) {
			data.append(controller.get()).append("\t");
		}
		return data.toString();
	}
	
	private String getPdpCurrents() {
		StringBuilder data = new StringBuilder();
		for (int i = 0; i < 16; i++) {
			data.append(pdp.getCurrent(i)).append("\t");
		}
		return data.toString();
	}
}
