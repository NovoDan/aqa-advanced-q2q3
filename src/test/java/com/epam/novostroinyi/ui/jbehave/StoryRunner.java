package com.epam.novostroinyi.ui.jbehave;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;

import com.epam.novostroinyi.ui.jbehave.definition.LaunchStepDefinition;
import java.util.List;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

public class StoryRunner extends JUnitStories {

  public StoryRunner() {
    Embedder embedder = configuredEmbedder();
    embedder.embedderControls().doGenerateViewAfterStories(true).doIgnoreFailureInStories(false)
        .doIgnoreFailureInView(true).doVerboseFiltering(true).useThreads(5)
        .doFailOnStoryTimeout(false);
  }

  @Override
  public Configuration configuration() {
    return new MostUsefulConfiguration()
        .useStoryLoader(new LoadFromClasspath(this.getClass()))
        .useStoryReporterBuilder(new StoryReporterBuilder()
            .withCodeLocation(codeLocationFromClass(this.getClass()))
            .withFormats(CONSOLE));
  }

  @Override
  public InjectableStepsFactory stepsFactory() {
    return new InstanceStepsFactory(configuration(), new LaunchStepDefinition());
  }

  @Override
  public List<String> storyPaths() {
    return List.of("story/TestItems.story");
  }
}
