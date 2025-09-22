package com._Abdelaziz26.Game.Responses.Result_;

public class Errors {

    public static Error NotFoundErr(String msg) {
        return new Error() {
            @Override
            public ErrorType getType() {
                return ErrorType.NOT_FOUND_ERR;
            }

            @Override
            public String getMessage() {
                return msg;
            }
        };
    }

    public static Error BadRequestErr(String msg){
        return new Error() {
            @Override
            public ErrorType getType() {
                return ErrorType.BAD_REQUEST_ERR;
            }

            @Override
            public String getMessage() {
                return msg;
            }
        };
    }

    public static Error InternalServerErr(String msg){
        return new Error() {
            @Override
            public ErrorType getType() {
                return ErrorType.INTERNAL_SERVER_ERR;
            }
            @Override
            public String getMessage() {
                return msg;
            }
        };
    }

    public static Error ValidationErr(String msg){
        return new Error() {
            @Override
            public ErrorType getType() {
                return ErrorType.VALIDATION_ERR;
            }
            @Override
            public String getMessage() {
                return msg;
            }
        };
    }

    public static Error None()
    {
        return new Error() {
            @Override
            public ErrorType getType() {
                return ErrorType.NONE;
            }
            @Override
            public String getMessage() {
                return "";
            }
        };
    }
}
