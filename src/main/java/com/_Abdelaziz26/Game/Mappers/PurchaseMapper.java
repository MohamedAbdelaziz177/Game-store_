package com._Abdelaziz26.Game.Mappers;

import com._Abdelaziz26.Game.DTOs.Game.GameCardDto;
import com._Abdelaziz26.Game.DTOs.Purchase.AdminPurchaseDto;
import com._Abdelaziz26.Game.DTOs.Purchase.ReadPurchaseDto;
import com._Abdelaziz26.Game.Model.Game;
import com._Abdelaziz26.Game.Model.Purchase;
import com._Abdelaziz26.Game.Repositories.GameRepository;
import com._Abdelaziz26.Game.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseMapper {

    //private final GameRepository gameRepository;
    //private final UserRepository userRepository;

    public ReadPurchaseDto toDto(Purchase purchase) {
        return ReadPurchaseDto.builder()
                .userEmail(purchase.getUser().getEmail())
                .amount(purchase.getAmount())
                .createdAt(purchase.getCreatedAt())
                .status(purchase.getStatus())
                .gameId(purchase.getGame().getId())
                .gameName(purchase.getGame().getName())
                .build();


    }

    public AdminPurchaseDto toAdminDto(Purchase purchase) {
        return AdminPurchaseDto.builder()
                .id(purchase.getId())
                .userEmail(purchase.getUser().getEmail())
                .amount(purchase.getAmount())
                .createdAt(purchase.getCreatedAt())
                .status(purchase.getStatus())
                .game(new GameCardDto(purchase.getGame().getId(),
                        purchase.getGame().getName(),
                        purchase.getGame().getImageUrl(), purchase.getGame().getPrice()))
                .build();

    }
}
