package com._Abdelaziz26.WWE.DTOs.Championship;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateChampionshipDto {

    @NotNull(message = "Wrestler ID is required")
    private Long wrestlerId;

    @NotNull(message = "Title ID is required")
    private Long titleId;

    @NotNull(message = "Won date is required")
    @PastOrPresent(message = "Won date must be in the past or present")
    private Date wonDate;

    @FutureOrPresent(message = "Lost date must be in the future or present")
    private Date lostDate;

    @PositiveOrZero(message = "Days held must be positive or zero")
    private Integer daysHeld;

    private Long winningMatchId;

    private Long lossMatchId;
}
