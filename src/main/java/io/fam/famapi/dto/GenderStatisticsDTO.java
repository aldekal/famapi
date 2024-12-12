package io.fam.famapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenderStatisticsDTO {
    private long totalPersons;
    private int maleCount;
    private int femaleCount;
    private int otherCount;
}
