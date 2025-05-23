package com._Abdelaziz26.Game.DTOs.Purchase;

import com._Abdelaziz26.Game.DTOs.Game.GameCardDto;
import com._Abdelaziz26.Game.Enums.PurchaseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminPurchaseDto {

    private Long id;

    private PurchaseStatus status;

    private double amount;

    private Date createdAt;

    private GameCardDto game;

    private String userEmail;
}
