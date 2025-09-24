package com._Abdelaziz26.Game.Controllers;

import com._Abdelaziz26.Game.Responses.Result_.Error;
import com._Abdelaziz26.Game.Responses.Result_.ErrorType;
import com._Abdelaziz26.Game.Responses.Result_.Errors;
import com._Abdelaziz26.Game.Responses.Result_.Result;
import org.springframework.http.HttpStatus;

public abstract class _AbdelazizController {

    protected HttpStatus resolveStatus(Result<?, Error> result) {
        if (result.isSuccess() && result.getError().equals(Errors.None()))
            return HttpStatus.OK;

        ErrorType type = result.getError().getType();
        return switch (type) {
            case NOT_FOUND_ERR      -> HttpStatus.NOT_FOUND;
            case BAD_REQUEST_ERR,
                 VALIDATION_ERR    -> HttpStatus.BAD_REQUEST;
            case UNAUTHORIZED_ERR   -> HttpStatus.UNAUTHORIZED;
            case INTERNAL_SERVER_ERR-> HttpStatus.INTERNAL_SERVER_ERROR;
            default                 -> HttpStatus.OK;
        };
    }
}

