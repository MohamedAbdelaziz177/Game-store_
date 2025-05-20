package com._Abdelaziz26.WWE.DTOs.Match;

import com._Abdelaziz26.WWE.DTOs.Participant.CreateParticipantDto;
import com._Abdelaziz26.WWE.Enums.MatchType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

public class CreateMatchDto {

    @NotNull(message = "Event ID is required")
    private Long eventId;

    @Enumerated(EnumType.STRING)
    private MatchType matchType;

    @NotNull(message = "Start time is required")
    @FutureOrPresent(message = "Start time must be in the present or future")
    private Date startTime;

    @Positive(message = "Duration must be positive")
    private Integer durationMinutes;

    @NotNull(message = "Participants are required")
    @Size(min = 2)
    private List<CreateParticipantDto> participants;

}
