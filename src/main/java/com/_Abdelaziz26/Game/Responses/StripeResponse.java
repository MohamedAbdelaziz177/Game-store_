package com._Abdelaziz26.Game.Responses;

import com._Abdelaziz26.Game.Enums.PurchaseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StripeResponse {

    private PurchaseStatus status;

    private String sessionId;

    private String sessionUrl;
}
