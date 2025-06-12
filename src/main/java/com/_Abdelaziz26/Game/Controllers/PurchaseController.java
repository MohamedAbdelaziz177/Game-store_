package com._Abdelaziz26.Game.Controllers;

import com._Abdelaziz26.Game.DTOs.Purchase.AdminPurchaseDto;
import com._Abdelaziz26.Game.DTOs.Purchase.PurchaseDto;
import com._Abdelaziz26.Game.DTOs.Purchase.ReadPurchaseDto;
import com._Abdelaziz26.Game.Model.User;
import com._Abdelaziz26.Game.Responses.ApiResponse;
import com._Abdelaziz26.Game.Responses.StripeResponse;
import com._Abdelaziz26.Game.Services.PurchaseService;
import com.stripe.exception.StripeException;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


        StripeResponse res = purchaseService.purchase(dto, user);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/admin")
    public ResponseEntity<ApiResponse<List<AdminPurchaseDto>>> getAdminPurchases(@AuthenticationPrincipal User user) {

        ApiResponse<List<AdminPurchaseDto>> res = new ApiResponse<>();

        List<AdminPurchaseDto> dtos = purchaseService.getAdminPurchases(user);

        res.setData(dtos);
        res.setSuccess(Boolean.TRUE);

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/my-purchases")
    public ResponseEntity<ApiResponse<List<ReadPurchaseDto>>> getMyPurchases(@AuthenticationPrincipal User user) {

        ApiResponse<List<ReadPurchaseDto>> res = new ApiResponse<>();

        List<ReadPurchaseDto> dtos = purchaseService.getMyPurchases(user);

        res.setData(dtos);
        res.setSuccess(Boolean.TRUE);

        return ResponseEntity.ok(res);
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
