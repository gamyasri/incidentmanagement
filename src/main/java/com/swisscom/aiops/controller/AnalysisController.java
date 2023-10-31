package com.swisscom.aiops.controller;

import com.swisscom.aiops.models.AnalysisResponse;
import com.swisscom.aiops.models.CheckRequest;
import com.swisscom.aiops.models.increquest.IncRequest;
import com.swisscom.aiops.models.KeywordTeamPair;
import com.swisscom.aiops.services.KeywordTeamDataSource;
import com.swisscom.aiops.services.TeamAnalyzer;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("inc")
public class AnalysisController {

    @PostMapping("/analysis")
    public String analyseInc(@RequestBody IncRequest incRequest) throws IOException, URISyntaxException {
        System.out.println(incRequest);
        final String relevantContent = incRequest.getIncident().getIncident().getTextContent();

        final List<KeywordTeamPair> records = KeywordTeamDataSource.getKeywordTeamPairs();
        final String teamNames = TeamAnalyzer.determineTeam(records, relevantContent);

        // TODO create Teams message with team name in it (MS Teams webhook or MS Teams channel mail address)
        return teamNames;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/check")
    public AnalysisResponse checkAnalysis(@RequestBody CheckRequest requestPayload) throws IOException, URISyntaxException {
        final String relevantContent = requestPayload.getSummary() + " " + requestPayload.getDescription();

        final List<KeywordTeamPair> records = KeywordTeamDataSource.getKeywordTeamPairs();
        final String teams = TeamAnalyzer.determineTeam(records, relevantContent);
        return AnalysisResponse.builder().teams(teams).build();
    }
}
