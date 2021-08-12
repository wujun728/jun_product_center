package com.sanri.tools.modules.quartz.service;

import lombok.Data;
import org.quartz.JobKey;

import javax.validation.constraints.NotNull;

@Data
public class EditJobParam {
//    private JobKey jobKey;
    @NotNull
    private String name;
    @NotNull
    private String group;

    private String description;
    @NotNull
    private String className;
    private String classloaderName;
    @NotNull
    private String jobMethodName;
    @NotNull
    private String cron;

    public JobKey getJobKey(){
        return JobKey.jobKey(name,group);
    }
}
