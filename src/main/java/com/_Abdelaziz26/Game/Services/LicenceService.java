package com._Abdelaziz26.Game.Services;

import com._Abdelaziz26.Game.Enums.LicenceStatus;
import com._Abdelaziz26.Game.Model.Licence;
import com._Abdelaziz26.Game.Model.Purchase;
import com._Abdelaziz26.Game.Model.User;
import com._Abdelaziz26.Game.Repositories.LicenceRepository;
import com._Abdelaziz26.Game.Repositories.PurchaseRepository;
import com._Abdelaziz26.Game.Responses.Result_.Error;
import com._Abdelaziz26.Game.Responses.Result_.Errors;
import com._Abdelaziz26.Game.Responses.Result_.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LicenceService {

    private final LicenceRepository licenceRepository;
    private final PurchaseRepository purchaseRepository;

    public Result<String, Error> getLicence(Long purchaseId, @AuthenticationPrincipal User user)
    {
        Optional<Purchase> purchase = purchaseRepository.findById(purchaseId);

        if(purchase.isPresent())
            return Result.CreateSuccessResult(purchase.get().getLicence().getKey());

        return this.createLicence(purchaseId, user);
    }

    private Result<String, Error> createLicence(Long purchaseId, @AuthenticationPrincipal User user)
    {
        Optional<Purchase> purchase = purchaseRepository.findById(purchaseId);

        if(purchase.isEmpty())
            return Result.CreateErrorResult(Errors.NotFoundErr("No purchase found for purchase-id " + purchaseId));

        if(!purchase.get().getUser().getId().equals(user.getId()))
            return Result.CreateErrorResult(Errors.UnauthorizedErr("U r not authorized to perform this operation"));

        Licence licence = licenceRepository.save(
                Licence.builder()
                .key(UUID.randomUUID().toString())
                .expiryDate(LocalDate.MAX)
                .user(user)
                .purchase(purchase.get())
                .build()
        );

        return Result.CreateSuccessResult(licence.getKey());
    }

    public Result<String, Error> validateLicence(String licenceKey, @AuthenticationPrincipal User user)
    {
        Optional<Licence> licence = licenceRepository.findByKey(licenceKey);

        if(licence.isEmpty())
            return Result.CreateErrorResult(Errors.NotFoundErr("Licence not found for licence-key " + licenceKey));

        if(!licence.get().getUser().getId().equals(user.getId()))
            return Result.CreateErrorResult(Errors.UnauthorizedErr("U r not authorized to perform this operation"));

        return Result.CreateSuccessResult("Licence validated!");
    }

    public Result<String, Error> revokeLicence(String licenceKey, @AuthenticationPrincipal User user)
    {
        Optional<Licence> licence = licenceRepository.findByKey(licenceKey);

        if(licence.isEmpty())
            return Result.CreateErrorResult(Errors.NotFoundErr("Licence not found for licence-key " + licenceKey));

        if(!licence.get().getUser().getId().equals(user.getId()))
            return Result.CreateErrorResult(Errors.UnauthorizedErr("U r not authorized to perform this operation"));

        licence.get().setStatus(LicenceStatus.REVOKED);

        return Result.CreateSuccessResult("Licence revoked!");
    }
}
