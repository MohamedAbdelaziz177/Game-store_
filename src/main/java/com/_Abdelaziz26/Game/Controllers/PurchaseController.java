package com._Abdelaziz26.Game.Controllers;

import com._Abdelaziz26.Game.DTOs.Purchase.PurchaseDto;
import com._Abdelaziz26.Game.Responses.StripeResponse;
import com._Abdelaziz26.Game.Services.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    public ResponseEntity<StripeResponse> Checkout(@PathVariable Long id)
    {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<StripeResponse> Order(@PathVariable Long id)
    {
        PurchaseDto createPurchaseDto = new PurchaseDto();
        createPurchaseDto.setId(id);

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
