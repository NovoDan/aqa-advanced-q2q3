package com.epam.novostroinyi.core.util;

import com.epam.novostroinyi.core.exception.TestErrorException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
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
}
