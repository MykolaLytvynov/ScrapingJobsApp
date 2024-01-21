package ua.mykola.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.mykola.entity.Job;
import ua.mykola.entity.LaborFunction;
import ua.mykola.service.JobService;

import java.util.List;

/**
 * Controller of jobs
 */
@RestController
@RequestMapping("/techstars")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    /**
     * Get all jobs from the DB
     *
     * @return all jobs from the DB
     */
    @GetMapping("/all")
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobService.findAll();
        return ResponseEntity.ok().body(jobs);
    }

    /**
     * Get the jobs by function from the DB
     *
     * @param function - labor function
     * @return found jobs
     */
    @GetMapping("/by-function")
    public ResponseEntity<List<Job>> getJobsByFunction(@RequestParam String function) {
        List<Job> jobs = jobService.findByFunction(LaborFunction.valueOf(function));
        return ResponseEntity.ok().body(jobs);
    }

    /**
     * Download csv file with jobs by function
     *
     * @param response - response helps to send csv file to web browser
     * @param laborFunction - labor function, like MARKETING_AND_COMMUNICATIONS
     */
    @GetMapping("/download-csv")
    public void downloadCsvByFunction(HttpServletResponse response, @RequestParam String function) {
        jobService.downloadCsvByFunction(response, LaborFunction.valueOf(function));
    }
}
