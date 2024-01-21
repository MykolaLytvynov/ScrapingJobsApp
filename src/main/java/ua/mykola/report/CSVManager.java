package ua.mykola.report;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import ua.mykola.entity.Job;

import java.io.IOException;
import java.util.List;

/**
 * Manager for working with CSV files
 */
@Component
@RequiredArgsConstructor
public class CSVManager {
    private String[] headers = new String[]{"Position", "Organization", "Function",
            "Address", "Posted Date", "Tags", "Url", "Logo"};
    private String[] propertyNames = new String[]{"positionName", "organizationTitle", "laborfunction",
            "address", "postedDate", "tags", "pageUrl", "urlLogo"};

    /**
     * Adding jobs to Csv file
     *
     * @param response - response helps to send Csv file to web browser
     * @param jobs - jobs for adding to Csv file
     */
    public void downloadCsvByFunction(HttpServletResponse response, List<Job> jobs) {
        try {
            response.setContentType("text/csv;charset=utf-8");

            ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
            csvBeanWriter.writeHeader(headers);

            for (Job job : jobs) {
                csvBeanWriter.write(job, propertyNames);
            }

            csvBeanWriter.close();
        } catch (IOException e) {
        }
    };
}
