package ua.mykola.parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import ua.mykola.entity.Job;
import ua.mykola.entity.LaborFunction;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Parser of jobs on the Techstars website
 */
@Component
public class TechstarsParser {
    private static final String URL = "https://jobs.techstars.com/jobs";

    /**
     * Parse jobs by function
     * Only first page of one of any job function
     *
     * @param function - labor function
     * @return parsed Jobs
     */
    public List<Job> parseJobsByFunction(LaborFunction function) throws IOException {
        List<Job> jobs = new ArrayList<>();

        String encodedFunction = getEncodedFunction(function);
        Document doc = Jsoup.connect(URL)
                .data("filter", encodedFunction)
                .get();

        Elements jobElements = doc.getElementsByClass("sc-beqWaB sc-gueYoa diHipZ MYFxR");
        for (Element jobElement : jobElements) {
            Job job = parseJobElement(jobElement, function);
            jobs.add(job);
        }

        return jobs;
    }

    /**
     * Get encoded Function for web parameter
     * Techstars uses Base64 for web parameters
     * Example - {"job_functions":["IT"]} must encode to Base64
     *
     * @param function - labor function
     * @return encoded Function
     */
    private String getEncodedFunction(LaborFunction function) {
        String laborFunction = function.getValue();
        String encodedFunction = Base64.getEncoder()
                .encodeToString(("{\"job_functions\":[\"" + laborFunction + "\"]}").getBytes());
        return encodedFunction;
    }

    /**
     * Parse element to get job
     *
     * @param jobElement - element with job
     * @param function - labor function
     * @return parsed job
     */
    private Job parseJobElement(Element jobElement, LaborFunction function) {

        String pageUrl = jobElement.getElementsByClass("sc-beqWaB iljPHq theme_only").get(0).attr("href");

        String positionName = ((TextNode) jobElement.getElementsByAttributeValue("itemprop", "title").get(0).childNode(0)).text();
        String urlLogo = jobElement.getElementsByAttributeValue("itemprop", "logo").get(0).attr("content");
        String organizationTitle = jobElement.getElementsByAttributeValue("itemprop", "name").get(0).attr("content");
        LocalDate datePosted = LocalDate.parse(jobElement.getElementsByAttributeValue("itemprop", "datePosted").get(0).attr("content"));
        String tags = getTagsByElements(jobElement.getElementsByClass("sc-dmqHEX dncTlc"));

        Elements addressElements = jobElement.getElementsByAttributeValue("itemprop", "address");
        String address;
        if (addressElements.isEmpty()) {
            address = "NOT_FOUND";
        } else {
            address = jobElement.getElementsByAttributeValue("itemprop", "address").get(0).attr("content");
        }

        return Job.builder()
                .laborFunction(function)
                .positionName(positionName)
                .pageUrl(pageUrl)
                .urlLogo(urlLogo)
                .organizationTitle(organizationTitle)
                .postedDate(datePosted)
                .address(address)
                .tags(tags)
                .build();
    }

    /**
     *  Get tags as one line separated by commas
     *
     * @param tags - job tags
     * @return tags as one line separated by commas
     */
    private String getTagsByElements(Elements tags) {
        return tags.stream()
                .map(element -> element.childNode(0))
                .map(node -> node.toString())
                .map(tag -> tag.substring(1))
                .collect(Collectors.joining(", "));
    }
}
