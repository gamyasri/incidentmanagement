package com.swisscom.aiops.models;

import lombok.*;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PlaceHolderCollection {
    private final String summary;
    private final String incNumber;
    private final String teams;
    private final String createdAt;
    private final String url;
}
