package com.swisscom.aiops.models;

import lombok.*;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AnalysisResponse {
    private final String teams;
}
