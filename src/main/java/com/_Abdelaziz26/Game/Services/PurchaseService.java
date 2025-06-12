package com._Abdelaziz26.Game.Services;

import com._Abdelaziz26.Game.DTOs.Purchase.AdminPurchaseDto;
import com._Abdelaziz26.Game.DTOs.Purchase.PurchaseDto;
import com._Abdelaziz26.Game.DTOs.Purchase.ReadPurchaseDto;
import com._Abdelaziz26.Game.Enums.PurchaseStatus;
import com._Abdelaziz26.Game.Mappers.PurchaseMapper;
import com._Abdelaziz26.Game.Model.Game;
import com._Abdelaziz26.Game.Model.Purchase;
import com._Abdelaziz26.Game.Model.User;
import com._Abdelaziz26.Game.Repositories.GameRepository;
import com._Abdelaziz26.Game.Repositories.PurchaseRepository;
import com._Abdelaziz26.Game.Repositories.UserRepository;
import com._Abdelaziz26.Game.Responses.StripeResponse;
import com._Abdelaziz26.Game.Utility.StripeService;
import com.stripe.exception.StripeException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final StripeService stripeService;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;

    public PurchaseDto checkout(Long id, @AuthenticationPrincipal User user) {

        Game game = gameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Game not found"));

        PurchaseDto purchaseDto = new PurchaseDto();

        purchaseDto.setGameName(game.getName());
        purchaseDto.setPrice(game.getPrice());
        purchaseDto.setId(id);

        return purchaseDto;
    }

    public StripeResponse purchase(PurchaseDto purchaseDto, @AuthenticationPrincipal User user) throws StripeException
    {
        Game game = gameRepository.findById(purchaseDto.getId()).orElseThrow(() ->
                new EntityNotFoundException("Game not found"));

        Purchase purchase = new Purchase();
        purchase.setGame(game);
        purchase.setUser(user);
        purchase.setAmount(purchaseDto.getPrice());
        purchase.setStatus(PurchaseStatus.PENDING);
        user.getPurchases().add(purchase);

        purchaseRepository.save(purchase);
        userRepository.save(user);


        return stripeService.createStripeSession(purchaseDto);
    }

    public List<ReadPurchaseDto> getMyPurchases(@AuthenticationPrincipal User user) {

        List<Purchase> purchases = purchaseRepository.findByUser_Id(user.getId()).orElse(new ArrayList<>());

        return purchases.stream().map(purchaseMapper::toDto).toList();
    }

    public List<AdminPurchaseDto> getAdminPurchases(@AuthenticationPrincipal User user)
    {
        List<Purchase> purchases = purchaseRepository.findAllOrderByCreatedAtDesc().orElse(new ArrayList<>());

        return purchases.stream().map(purchaseMapper::toAdminDto).toList();
    }




}
