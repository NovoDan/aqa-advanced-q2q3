package com.epam.novostroinyi.ui.testng.listener;

import static com.epam.novostroinyi.core.util.FileUtils.deserializeFile;
import static com.epam.novostroinyi.core.util.FileUtils.saveByteArrayToFile;
import static com.epam.novostroinyi.core.util.WebDriverUtils.takeScreenshot;

import com.epam.novostroinyi.core.config.ConfigUtils;
import com.epam.novostroinyi.core.exception.FileProcessingException;
import com.epam.novostroinyi.core.reporter.Reporter;
import java.io.File;
import java.io.IOException;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class UiTestNgListener extends TestListenerAdapter {

  @Override
  public void onTestFailure(ITestResult failure) {
    Reporter reporter = ConfigUtils.getReporter();
    try {
      byte[] screenshotBytes = takeScreenshot();
      String outputPath = "output/screenshots";
      String filename = failure.getName() + ".png";
      saveByteArrayToFile(screenshotBytes, outputPath, filename);
      File screenshot = deserializeFile(outputPath + "\\" + filename);
      reporter.addFileAttachment("Screenshot", screenshot);
    } catch (IOException e) {
      throw new FileProcessingException("Could not save screenshot");
    }
  }
}
