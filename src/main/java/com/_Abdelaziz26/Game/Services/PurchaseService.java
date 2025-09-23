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
import com._Abdelaziz26.Game.Responses.Result_.Error;
import com._Abdelaziz26.Game.Responses.Result_.Errors;
import com._Abdelaziz26.Game.Responses.Result_.Result;
import com._Abdelaziz26.Game.Responses.StripeResponse;
import com._Abdelaziz26.Game.Utility.StripeService;
import com.stripe.exception.StripeException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final StripeService stripeService;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;

    public Result<PurchaseDto, Error> checkout(Long id, @AuthenticationPrincipal User user) {

        Optional<Game> game = gameRepository.findById(id);

        if(game.isEmpty())
            return Result.CreateErrorResult(Errors.NotFoundErr("Game not found"));

        PurchaseDto purchaseDto = new PurchaseDto();

        purchaseDto.setGameName(game.get().getName());
        purchaseDto.setPrice(game.get().getPrice());
        purchaseDto.setId(id);

        return Result.CreateSuccessResult(purchaseDto);
    }

    public Result<StripeResponse, Error> purchase(Long gameId, @AuthenticationPrincipal User user) throws StripeException
    {
        Optional<Game> game = gameRepository.findById(gameId);

        if(game.isEmpty())
            return Result.CreateErrorResult(Errors.NotFoundErr("Game not found"));

        Purchase purchase = new Purchase();
        purchase.setGame(game.get());
        purchase.setUser(user);
        purchase.setAmount(game.get().getPrice());
        purchase.setStatus(PurchaseStatus.PENDING);
        user.getPurchases().add(purchase);

        purchaseRepository.save(purchase);
        userRepository.save(user);

        return Result
                .CreateSuccessResult(stripeService
                        .createStripeSession(new PurchaseDto(gameId, game.get().getName(), game.get().getPrice()
                        )
                        )
                );
    }

    public Result<List<ReadPurchaseDto>, Error> getMyPurchases(@AuthenticationPrincipal User user) {

        List<Purchase> purchases = purchaseRepository.findByUser_Id(user.getId()).orElse(new ArrayList<>());
        return Result.CreateSuccessResult(purchases.stream().map(purchaseMapper::toDto).toList());
    }

    public Result<List<AdminPurchaseDto>, Error> getAdminPurchases()
    {
        List<Purchase> purchases = purchaseRepository.findAllByOrderByCreatedAtDesc().orElse(new ArrayList<>());
        return Result.CreateSuccessResult(purchases.stream().map(purchaseMapper::toAdminDto).toList());
    }

}
