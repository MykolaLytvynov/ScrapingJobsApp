package ua.mykola.service;

import jakarta.servlet.http.HttpServletResponse;
import ua.mykola.entity.Job;
import ua.mykola.entity.LaborFunction;

import java.util.List;

/**
 * Job Service
 */
public interface JobService {
    /**
     * Save the new jobs to the database
     *
     * @param jobs - the new jobs
     * @return The saved jobs
     */
    List<Job> saveAll(List<Job> jobs);

    /**
     * Get List of the all jobs
     *
     * @return List of the all jobs
     */
    List<Job> findAll();

    /**
     * Get List of Jobs by LaborFunction
     *
     * @param laborFunction - Labor Function
     * @return List of found Jobs
     */
    List<Job> findByFunction(LaborFunction laborFunction);

    /**
     * Download csv file with jobs by function
     *
     * @param response - response helps to send data back to web browser
     * @param laborFunction - labor function, like MARKETING_AND_COMMUNICATIONS
     */
    void downloadCsvByFunction(HttpServletResponse response, LaborFunction laborFunction);

    /**
     * Check url of vacancy in the DB, if one exists then will return true
     *
     * @param pageUrl - URL leading to the page with the vacancy
     * @return boolean - true, if url exists in the DB
     */
    boolean isExistByPageUrl(String pageUrl);
}
