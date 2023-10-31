package com.swisscom.aiops.models.increquest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class IncRequest {
    private final IncidentWrapper incident;
}
