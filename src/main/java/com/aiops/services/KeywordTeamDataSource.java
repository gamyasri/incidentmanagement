package com.aiops.services;

import com.aiops.models.KeywordTeamPair;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KeywordTeamDataSource {

    private static final String COMMA_DELIMITER = ";";

    public static List<KeywordTeamPair> getKeywordTeamPairs() throws URISyntaxException, FileNotFoundException {
        List<KeywordTeamPair> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(getFileFromResource("AIOps_Keywords.csv"))) {
            while (scanner.hasNextLine()) {
                List<String> recordFromLine = getRecordFromLine(scanner.nextLine());
                records.add(KeywordTeamPair.builder()
                        .keyword(recordFromLine.get(0))
                        .team(recordFromLine.get(2))
                        .build());
            }
        }
        return records;
    }

    public static String getTeamsMessageTemplate() throws URISyntaxException {
        try {
            Resource resource = new ClassPathResource("teams_message.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()),1024);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
            br.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new IllegalArgumentException("file not found! " + e);
        }
    }

    private static File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = KeywordTeamDataSource.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }

    }

    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
}
