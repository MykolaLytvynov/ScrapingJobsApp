package ua.mykola.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Job entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Job {
    /** Job id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** URL leading to the page with the vacancy */
    private String pageUrl;

    /** position name */
    private String positionName;

    /** logo (save only link to image) */
    private String urlLogo;

    /** organization title */
    private String organizationTitle;

    /** labor function */
    private LaborFunction laborFunction;

    /** vacancy address */
    private String address;

    /** posted Date */
    private LocalDate postedDate;

    /** tags as one line separated by commas */
    private String tags;
}

