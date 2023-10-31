package com.swisscom.aiops.models.increquest;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Incident {
    private final String summary;
    private final String notes;
    private final String publicDescription;
    private final String impact;
    private final String urgency;
    private final String priority;

    public String getTextContent() {
        return summary + " " + notes + " " + publicDescription;
    }
}
