package com._Abdelaziz26.Game.Controllers;

import com._Abdelaziz26.Game.Model.Licence;
import com._Abdelaziz26.Game.Model.User;
import com._Abdelaziz26.Game.Responses.Result_.Error;
import com._Abdelaziz26.Game.Responses.Result_.Result;
import com._Abdelaziz26.Game.Services.LicenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/licence")
public class LicenceController extends _AbdelazizController{

    private final LicenceService licenceService;

    @GetMapping("/get")
    public ResponseEntity<Result<String, Error>> getLicence(@RequestParam Long purchaseId,
                                                            @AuthenticationPrincipal User user)
    {
        Result<String, Error> result = licenceService.getLicence(purchaseId, user);
        return ResponseEntity.status(resolveStatus(result)).body(result);
    }

    @GetMapping("/validate")
    public ResponseEntity<Result<String, Error>> validateLicence(@RequestBody Map<String, String> mp,
                                                                 @AuthenticationPrincipal User user)
    {
        Result<String, Error> result = licenceService.validateLicence(mp.get("licenceKey"), user);
        return ResponseEntity.status(resolveStatus(result)).body(result);
    }

    @PutMapping("/revoke")
    public ResponseEntity<Result<String, Error>> revokeLicence(@RequestBody Map<String, String> mp,
                                                               @AuthenticationPrincipal User user)
    {
        Result<String, Error> result = licenceService.revokeLicence(mp.get("licenceKey"), user);
        return ResponseEntity.status(resolveStatus(result)).body(result);
    }

}
