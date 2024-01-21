package ua.mykola.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.mykola.entity.Job;
import ua.mykola.entity.LaborFunction;
import ua.mykola.report.CSVManager;
import ua.mykola.repository.JobRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of JobService
 */
@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService{
    private final JobRepository jobRepository;
    private final CSVManager csvManager;

    /**
     * Save the new jobs to the database
     *
     * @param jobs - the new jobs
     * @return The saved jobs
     */
    @Override
    public List<Job> saveAll(List<Job> jobs) {
        //if jobs have been added, there is no need to add them anymore
        jobs = jobs.stream().filter(job -> !jobRepository.existsByPageUrl(job.getPageUrl()))
                .collect(Collectors.toList());

        return jobRepository.saveAll(jobs);
    }

    /**
     * Get List of the all jobs
     *
     * @return List of the all jobs
     */
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    /**
     * Get List of Jobs by LaborFunction
     *
     * @param function - Labor Function
     * @return List of found Jobs
     */
    @Override
    public List<Job> findByFunction(LaborFunction laborFunction) {
        return jobRepository.findJobsByLaborFunction(laborFunction);
    }

    /**
     * Download csv file with jobs by function
     *
     * @param response - response helps to send Csv file to web browser
     * @param laborFunction - labor function, like MARKETING_AND_COMMUNICATIONS
     */
    @Override
    public void downloadCsvByFunction(HttpServletResponse response, LaborFunction laborFunction) {
        List<Job> jobs = jobRepository.findJobsByLaborFunction(laborFunction);
        csvManager.downloadCsvByFunction(response, jobs);
    }

    /**
     * Check url of vacancy in the DB, if one exists then will return true
     *
     * @param pageUrl - URL leading to the page with the vacancy
     * @return boolean - true, if url exists in the DB
     */
    @Override
    public boolean isExistByPageUrl(String pageUrl) {
        return jobRepository.existsByPageUrl(pageUrl);
    }

}
