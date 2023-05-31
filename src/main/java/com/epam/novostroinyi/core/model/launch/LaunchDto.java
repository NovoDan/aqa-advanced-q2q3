package com.epam.novostroinyi.core.model.launch;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LaunchDto {

  private String owner;
  private boolean share;
  private String description;
  private int id;
  private String uuid;
  private String name;
  private int number;
  private long startTime;
  private long endTime;
  private long lastModified;
  private String status;
  private Statistics statistics;
  private List<Attribute> attributes;
  private String mode;
  private List<Object> analysing;
  private double approximateDuration;
  private boolean hasRetries;
  private boolean rerun;

  public class Attribute{
    private String key;
    private String value;
  }

  public class AutomationBug{
    private int total;
    private int ab001;
  }

  public class Defects{
    @SerializedName(value = "system_issue")
    private SystemIssue systemIssue;
    @SerializedName(value = "to_investigate")
    private ToInvestigate toInvestigate;
    @SerializedName(value = "automation_bug")
    private AutomationBug automationBug;
  }

  public class Executions{
    private int total;
    private int failed;
    private int passed;
  }

  public class Statistics{
    private Executions executions;
    private Defects defects;
  }

  public class SystemIssue{
    private int total;
    private int si001;
  }

  public class ToInvestigate{
    private int total;
    private int ti001;
  }

}
