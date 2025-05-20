package com._Abdelaziz26.WWE.DTOs.EventImage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadEventImageDto {

    private Long id;

    private String url;

    private Integer displayOrder;
}
