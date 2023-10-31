package com.swisscom.aiops.models;

import lombok.*;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class KeywordTeamPair {
    private final String keyword;
    private final String team;
}
