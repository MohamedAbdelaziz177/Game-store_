package com._Abdelaziz26.Game.Controllers;

import com._Abdelaziz26.Game.DTOs.Purchase.PurchaseDto;
import com._Abdelaziz26.Game.Model.User;
import com._Abdelaziz26.Game.Responses.StripeResponse;
import com._Abdelaziz26.Game.Services.PurchaseService;
import com.stripe.exception.StripeException;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping("/checkout")
    public ResponseEntity<PurchaseDto> checkout(@RequestParam Long gameId, @AuthenticationPrincipal User user)
    {
        PurchaseDto dto = purchaseService.checkout(gameId, user);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/order")
    public ResponseEntity<StripeResponse> order(@RequestBody PurchaseDto dto, @AuthenticationPrincipal User user) throws StripeException {
        StripeResponse res = purchaseService.confirmPurchase(dto, user);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/success")
    public String Success()
    {
        return "success";
    }

    @GetMapping("/cancel")
    public String Cancel()
    {
        return "cancel";
    }
}
