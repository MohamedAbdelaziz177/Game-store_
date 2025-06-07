package com._Abdelaziz26.Game.Utility;

import com._Abdelaziz26.Game.DTOs.Purchase.PurchaseDto;
import com._Abdelaziz26.Game.Enums.PurchaseStatus;
import com._Abdelaziz26.Game.Responses.StripeResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StripeService {

    @Value("${stripe.success.url}")
    private static String SUCCESS_URL;

    @Value("${stripe.cancel.url}")
    private static String ERROR_URL;

    public StripeResponse createStripeSession(PurchaseDto purchaseDto) throws StripeException
    {
        SessionCreateParams params = SessionCreateParams.builder()
                .setSuccessUrl(SUCCESS_URL)
                .setCancelUrl(ERROR_URL)
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .addLineItem(
                    SessionCreateParams.LineItem.builder()
                            .setPriceData(
                                SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("usd")
                                        .setUnitAmount((purchaseDto.getPrice().longValue() * 100))
                                        .setProductData(
                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                       .setName(purchaseDto.getGameName())
                                                       .build()
                                        )
                                        .build()
                            )
                            .setQuantity(1L)
                            .build()
                )

                .build();

        Session session = Session.create(params);


        return  StripeResponse.builder()
                .sessionId(session.getId())
                .sessionUrl(session.getUrl())
                .status(PurchaseStatus.COMPLETED)
                .build();
    }
}
