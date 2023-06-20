package com.epam.novostroinyi.ui.junit.listener;

import static com.epam.novostroinyi.core.util.FileUtils.deserializeFile;
import static com.epam.novostroinyi.core.util.FileUtils.saveByteArrayToFile;
import static com.epam.novostroinyi.core.util.WebDriverUtils.takeScreenshot;

import com.epam.novostroinyi.core.config.ConfigUtils;
import com.epam.novostroinyi.core.exception.FileProcessingException;
import com.epam.novostroinyi.core.reporter.Reporter;
import java.io.File;
import java.io.IOException;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class UiTestListener extends RunListener {

  @Override
  public void testFailure(Failure failure) {
    Reporter reporter = ConfigUtils.getReporter();
    try {
      byte[] screenshotBytes = takeScreenshot();
      String outputPath = ".\\output\\screenshots\\screenshot";
      String filename = failure.getTestHeader() + ".png";
      saveByteArrayToFile(screenshotBytes, outputPath, filename);
      File screenshot = deserializeFile(outputPath + "\\" + filename);
      reporter.addFileAttachment("Screenshot", screenshot);
    } catch (IOException e) {
      throw new FileProcessingException("Could not save screenshot");
    }
  }
}
