package com.aiops.controller;

import com.aiops.models.AnalysisResponse;
import com.aiops.models.CheckRequest;
import com.aiops.models.KeywordTeamPair;
import com.aiops.models.PlaceHolderCollection;
import com.aiops.models.increquest.IncRequest;
import com.aiops.services.KeywordTeamDataSource;
import com.aiops.services.TeamAnalyzer;
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
        final PlaceHolderCollection placeHolderCollection = PlaceHolderCollection.builder()
                .summary(incRequest.getIncident().getIncident().getSummary())
                .teams(teamNames)
                .incNumber(incRequest.getIncident().getIncident().getSourceIncidentId())
                .createdAt(incRequest.getIncident().getIncident().getReportedDateTime())
                .url("www.gamyasripatnayakuni.com")
                .build();
         String template = KeywordTeamDataSource.getTeamsMessageTemplate()
        .replace("{{summary}}", placeHolderCollection.getSummary())
        .replace("{{createdAt}}", placeHolderCollection.getCreatedAt())
        .replace("{{incNumber}}", placeHolderCollection.getIncNumber())
        .replace("{{url}}", placeHolderCollection.getUrl())
        .replace("{{teams}}", placeHolderCollection.getTeams());

        return template;
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
