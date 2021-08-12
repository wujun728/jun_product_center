package cc.mrbird.febs.server.job.controller;

import cc.mrbird.febs.common.core.entity.FebsResponse;
import cc.mrbird.febs.common.core.entity.QueryRequest;
import cc.mrbird.febs.common.core.entity.constant.StringConstant;
import cc.mrbird.febs.common.core.utils.FebsUtil;
import cc.mrbird.febs.server.job.entity.Job;
import cc.mrbird.febs.server.job.service.IJobService;
import com.wuwenze.poi.ExcelKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author MrBird
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class JobController {

    private final IJobService jobService;

    @GetMapping
    @PreAuthorize("hasAuthority('job:view')")
    public FebsResponse jobList(QueryRequest request, Job job) {
        Map<String, Object> dataTable = FebsUtil.getDataTable(this.jobService.findJobs(request, job));
        return new FebsResponse().data(dataTable);
    }

    @GetMapping("cron/check")
    public boolean checkCron(String cron) {
        try {
            return CronExpression.isValidExpression(cron);
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('job:add')")
    public void addJob(@Valid Job job) {
        this.jobService.createJob(job);
    }

    @DeleteMapping("{jobIds}")
    @PreAuthorize("hasAuthority('job:delete')")
    public void deleteJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        String[] ids = jobIds.split(StringConstant.COMMA);
        this.jobService.deleteJobs(ids);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('job:update')")
    public void updateJob(@Valid Job job) {
        this.jobService.updateJob(job);
    }

    @GetMapping("run/{jobIds}")
    @PreAuthorize("hasAuthority('job:run')")
    public void runJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        this.jobService.run(jobIds);
    }

    @GetMapping("pause/{jobIds}")
    @PreAuthorize("hasAuthority('job:pause')")
    public void pauseJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        this.jobService.pause(jobIds);
    }

    @GetMapping("resume/{jobIds}")
    @PreAuthorize("hasAuthority('job:resume')")
    public void resumeJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        this.jobService.resume(jobIds);
    }

    @PostMapping("excel")
    @PreAuthorize("hasAuthority('job:export')")
    public void export(QueryRequest request, Job job, HttpServletResponse response) {
        List<Job> jobs = this.jobService.findJobs(request, job).getRecords();
        ExcelKit.$Export(Job.class, response).downXlsx(jobs, false);
    }
}
