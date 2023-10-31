package com.swisscom.aiops.services;

import com.swisscom.aiops.models.KeywordTeamPair;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TeamAnalyzer {

    public static String determineTeam(List<KeywordTeamPair> records, String relevantContent) {
        final Map<String, Long> teamsWithCount = records.stream().filter(pair -> relevantContent.toLowerCase().contains(pair.getKeyword().toLowerCase()))
                .map(KeywordTeamPair::getTeam)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        final StringBuilder teamOutputBuilder = new StringBuilder();
        teamsWithCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> {
                    teamOutputBuilder.append("- ").append(entry.getKey()).append("\\\r");
                });


        return teamOutputBuilder.toString();
    }
}
