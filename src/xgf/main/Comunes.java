package xgf.main;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Comunes {

	static void createSimulationFile(String startDateTimeFormatted, String simulationResult, File resultFile, String endDateTimeFormatted, String totalDurationSecondsMiliseconds) {
		
		List<String> contentToWrite = new ArrayList<String>();
		contentToWrite.add(startDateTimeFormatted);
		contentToWrite.add("");
		contentToWrite.add(endDateTimeFormatted);
		contentToWrite.add("");
		contentToWrite.add(totalDurationSecondsMiliseconds);
		contentToWrite.add("");
		contentToWrite.add(simulationResult);
		
		try {
			
			Files.write(resultFile.toPath(), contentToWrite, StandardCharsets.UTF_8);
			Simulador.getSimulationFilenames().add(resultFile);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	static String getDateTimeFormatted(LocalDateTime dateToFormat, String format) {
		
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
	}

	static String getSimulationPathName(String simulationType, String proteinStructureType, int currentStructure, String dateTime) {
		
		String pathName = "";
		pathName += "PROT_";
		pathName += simulationType + "_";
		pathName += proteinStructureType + "_";
		pathName += "n" + Integer.toString(currentStructure) + "_";
		pathName += dateTime;
		pathName += ".sim";
	
		return pathName;
	}

	static int returnNumber(String number) {
		
		try {
			
			return Integer.parseInt(number);
			
		} catch (NumberFormatException e) {
			
			return 0;
		}
	}

}
