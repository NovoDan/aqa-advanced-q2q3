package com.epam.novostroinyi.core.util;

import com.epam.novostroinyi.core.exception.TestErrorException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonUtils {

  public static List<String[]> readCsvFile(String filePath, int linesToSkip) {
    try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
      reader.skip(linesToSkip);
      return reader.readAll();
    } catch (IOException | CsvException e) {
      throw new TestErrorException(e.getMessage());
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
}
