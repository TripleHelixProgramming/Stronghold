package org.iif.th.util.logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class HelixLogger {
	
	private final Map<String, PIDOutput> outputs = new HashMap<>();
	private final Map<String, PIDSource> sources = new HashMap<>();
	private final Map<String, Function<Object, Double>> dataSources = new HashMap();
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
	
//	public void addOutput(String name, PIDOutput output) {
//		outputs.put(name, output);
//	}

	public void addSource(String name, PIDSource source) {
		sources.put(name, source);
	}
	
	public void addSource(String name, Function<Object, Double> f) {
		dataSources.put(name, f);
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
			data.append(getValues());
//			data.append(getValues(sources.values(), c -> c.pidGet()));
//			data.append(getValues(outputs.values(), c -> c.));
			
			Files.write(file, Collections.singletonList(data.toString()), StandardOpenOption.APPEND);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void saveTitles() throws IOException {
		StringBuilder titles = new StringBuilder();
		titles.append("Timestamp\t");
		titles.append(getPdpTitles()).append("\t");
		titles.append(getTitles(sources)).append("\t");
		titles.append(getTitles(outputs)).append("\t");
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

	private void cleanUpFiles() throws IOException {
		Files.deleteIfExists(Paths.get("/home/lvuser/Log4.txt"));
		for (int i = 3; i >= 0; i--) {
			Path oldFile = Paths.get("/home/lvuser/Log" + i + ".txt");
			if (Files.exists(oldFile)) {
				Files.move(oldFile, oldFile.resolveSibling("/home/lvuser/Log" + (i + 1) + ".txt"));
			}
		}
	}
	
	private String getPdpCurrents() {
		StringBuilder data = new StringBuilder();
		for (int i = 0; i < 16; i++) {
			data.append(pdp.getCurrent(i)).append("\t");
		}
		return data.toString();
	}
	
	private String getValues() {
		return String.join("\t", 
				dataSources.values().stream()
				.map(object -> (object != null ? object.toString() : null))
				.collect(Collectors.toList()));
	}
	
//	private <T> String getValues(Collection<T> dataSources, Function<T, Double> f) {
//		return String.join("\t", 
//				dataSources.stream()
//				.map(f)
//				.map(object -> (object != null ? object.toString() : null))
//				.collect(Collectors.toList()));
//	}
}
