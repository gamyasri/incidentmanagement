package com.swisscom.aiops.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swisscom.aiops.models.increquest.IncRequest;
import com.swisscom.aiops.models.increquest.Incident;
import com.swisscom.aiops.models.increquest.IncidentWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

class AnalysisControllerTest {

    private final AnalysisController controller = new AnalysisController();
    private static final String TEST_FILES_DIR = ResourceUtils.CLASSPATH_URL_PREFIX;

    @Test
    void analysisOnHookPayload() throws IOException, URISyntaxException {
        final IncRequest incRequest = buildData("hook_payload.json");

        final String teamOutput = controller.analyseInc(incRequest);

        System.out.println(teamOutput);
    }

    @Test
    void getTeam() throws IOException, URISyntaxException {
        IncRequest incRequest = IncRequest.builder()
                .incident(IncidentWrapper.builder()
                        .incident(Incident.builder()
                                .summary("solvency call not working")
                                .notes("side grade and Activation")
                                .build())
                        .build())
                .build();
        final String teamOutput = controller.analyseInc(incRequest);

        System.out.println(teamOutput);
    }

    private static IncRequest buildData(final String fileName) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            final File file = ResourceUtils.getFile(TEST_FILES_DIR + fileName);
            return mapper.readValue(file, IncRequest.class);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}