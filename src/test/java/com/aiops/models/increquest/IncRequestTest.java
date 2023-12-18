package com.aiops.models.increquest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class IncRequestTest {
    private static final String TEST_FILES_DIR = ResourceUtils.CLASSPATH_URL_PREFIX;
    @Test
    void parse() {
        final IncRequest incRequest = buildData("hook_payload.json");

        assertThat(incRequest).isNotNull();
        System.out.println(incRequest);
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