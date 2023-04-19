package com.example.proxy.feign.rest.currentconditions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrentConditions {
    String LocalObservationDateTime;
    Temperature Temperature;
    String MobileLink;
    String Link;
}
