package com.stackroute.recommendationservice.model;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Step {
    private int id;
    private String stepContent;
    private String stepImage;

}
