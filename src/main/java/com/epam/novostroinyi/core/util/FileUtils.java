package com.epam.novostroinyi.core.util;

import com.epam.novostroinyi.core.config.ConfigUtils;
import com.epam.novostroinyi.core.exception.FileProcessingException;
import com.epam.novostroinyi.core.logger.ILogger;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.reporters.Files;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtils {

  private static ILogger logger = ConfigUtils.getLogger();

  public static List<String[]> readCsvFile(String filePath, int linesToSkip) {
    try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
      reader.skip(linesToSkip);
      return reader.readAll();
    } catch (IOException | CsvException e) {
      logger.error("Error occurred while reading CSV file {}", filePath);
      throw new FileProcessingException(e.getMessage());
    }
  }

  public static List<Map<String, String>> convertCsvListOfArraysToMaps(String filePath) {
    List<String[]> csvContent = readCsvFile(filePath, 0);
    String[] csvHeader = csvContent.get(0);
    List<String[]> csvData = csvContent.subList(1, csvContent.size());

    return csvData.stream().map(row -> {
      Map<String, String> mappedRow = new HashMap<>();
      for (int i = 0; i < row.length; i++) {
        mappedRow.put(csvHeader[i], row[i]);
      }
      return mappedRow;
    }).toList();
  }

  public static String getContentFromFile(File file) {
    try (InputStream stream = new FileInputStream(file)) {
      return Files.readFile(stream);
    } catch (IOException e) {
      logger.info("Error occurred while reading file {}", file.getName());
      throw new FileProcessingException(e.getMessage());
    }
  }

  public static String getFileAsStringByPath(String filePath) {
    File bodyForClaimCreation = new File(filePath);
    return getContentFromFile(bodyForClaimCreation);
  }
}
