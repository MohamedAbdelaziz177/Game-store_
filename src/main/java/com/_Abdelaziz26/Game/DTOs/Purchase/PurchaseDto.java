package com._Abdelaziz26.Game.DTOs.Purchase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {

    private Long id;

    private String GameName;

    private Double Price;
}
