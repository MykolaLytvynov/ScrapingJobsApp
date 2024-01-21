package ua.mykola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.mykola.entity.Job;
import ua.mykola.entity.LaborFunction;

import java.util.List;

/**
 * JpaRepository to work with Job entity
 */
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    /**
     * Get List of Jobs by LaborFunction
     *
     * @param function - Labor Function
     * @return List of found Jobs
     */
    List<Job> findJobsByLaborFunction(LaborFunction function);

    /**
     * Check url of vacancy in the DB, if one exists then will return true
     *
     * @param pageUrl - URL leading to the page with the vacancy
     * @return boolean - true, if url exists in the DB
     */
    boolean existsByPageUrl(String pageUrl);
}
