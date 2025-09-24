package com._Abdelaziz26.Game.Controllers;

import com._Abdelaziz26.Game.DTOs.Purchase.AdminPurchaseDto;
import com._Abdelaziz26.Game.DTOs.Purchase.PurchaseDto;
import com._Abdelaziz26.Game.DTOs.Purchase.ReadPurchaseDto;
import com._Abdelaziz26.Game.Model.User;
import com._Abdelaziz26.Game.Responses.Result_.Result;
import com._Abdelaziz26.Game.Responses.Result_.Error;
import com._Abdelaziz26.Game.Responses.StripeResponse;
import com._Abdelaziz26.Game.Services.PurchaseService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase")
@RequiredArgsConstructor
public class PurchaseController extends _AbdelazizController {

    private final PurchaseService purchaseService;

    @GetMapping("/checkout")
    public ResponseEntity<Result<PurchaseDto, Error>> checkout(
            @RequestParam Long gameId,
            @AuthenticationPrincipal User user) {

        Result<PurchaseDto, Error> result = purchaseService.checkout(gameId, user);
        return ResponseEntity.status(resolveStatus(result)).body(result);
    }

    @PostMapping("/order")
    public ResponseEntity<Result<StripeResponse, Error>> order(
            @RequestParam Long gameId,
            @AuthenticationPrincipal User user) throws StripeException {

        Result<StripeResponse, Error> result = purchaseService.purchase(gameId, user);
        return ResponseEntity.status(resolveStatus(result)).body(result);
    }

    @GetMapping("/admin")
    public ResponseEntity<Result<List<AdminPurchaseDto>, Error>> getAdminPurchases() {

        Result<List<AdminPurchaseDto>, Error> result = purchaseService.getAdminPurchases();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/my-purchases")
    public ResponseEntity<Result<List<ReadPurchaseDto>, Error>> getMyPurchases(
            @AuthenticationPrincipal User user) {

        Result<List<ReadPurchaseDto>, Error> result = purchaseService.getMyPurchases(user);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/cancel")
    public String cancel() {
        return "cancel";
    }
}
