package com._Abdelaziz26.WWE.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleTitleDto {

    private Long id;

    private String name;

    private String imageUrl;
}
