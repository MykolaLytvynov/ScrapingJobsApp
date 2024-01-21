package ua.mykola.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.mykola.entity.Job;
import ua.mykola.entity.LaborFunction;
import ua.mykola.parsing.TechstarsParser;
import ua.mykola.service.JobServiceImpl;

import java.io.IOException;
import java.util.List;

/**
 * Controller for parsing the Techstars website
 */
@RestController
@RequestMapping("/techstars-parser")
@RequiredArgsConstructor
public class TechstarsParserController {
    private final TechstarsParser parser;
    private final JobServiceImpl jobService;

    /**
     * Parse the Techstars website By Function and save parsed jobs to the DB
     *
     * @param function - labor function
     * @return web response
     */
    @GetMapping()
    public ResponseEntity<String> parseJobsByFunction(@RequestParam String function) {
        List<Job> jobs;
        try {
            jobs = parser.parseJobsByFunction(LaborFunction.valueOf(function));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Parsing hasn't been done");
        }

        jobService.saveAll(jobs);

        return ResponseEntity.ok("Parsing has been successfully done");
    }
}
