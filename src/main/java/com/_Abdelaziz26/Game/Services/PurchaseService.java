package com._Abdelaziz26.Game.Services;

import com._Abdelaziz26.Game.DTOs.Purchase.PurchaseDto;
import com._Abdelaziz26.Game.Model.Game;
import com._Abdelaziz26.Game.Model.User;
import com._Abdelaziz26.Game.Repositories.GameRepository;
import com._Abdelaziz26.Game.Repositories.UserRepository;
import com._Abdelaziz26.Game.Responses.StripeResponse;
import com._Abdelaziz26.Game.Utility.StripeService;
import com.stripe.exception.StripeException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final StripeService stripeService;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public PurchaseDto checkout(Long id, @AuthenticationPrincipal User user) {

        Game game = gameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Game not found"));

        PurchaseDto purchaseDto = new PurchaseDto();

        purchaseDto.setGameName(game.getName());
        purchaseDto.setPrice(game.getPrice());
        purchaseDto.setId(id);

        return purchaseDto;
    }

    public StripeResponse confirmPurchase(PurchaseDto purchaseDto, @AuthenticationPrincipal User user) throws StripeException
    {
        return stripeService.createStripeSession(purchaseDto);
    }

}
