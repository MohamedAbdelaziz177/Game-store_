package com._Abdelaziz26.WWE.DTOs.Participant;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateParticipantDto {

    @NotNull(message = "Wrestler ID is required")
    private Long wrestlerId;

    private Boolean isWinner = false;

}
