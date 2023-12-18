package com.aiops.models;

import lombok.*;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CheckRequest {
    private final String id;
    private final String description;
    private final String summary;
}
