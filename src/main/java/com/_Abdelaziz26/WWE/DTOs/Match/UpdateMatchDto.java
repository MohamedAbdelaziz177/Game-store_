package com._Abdelaziz26.WWE.DTOs.Match;

import com._Abdelaziz26.WWE.DTOs.Participant.UpdateParticipantDto;
import com._Abdelaziz26.WWE.Enums.MatchType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMatchDto {

    @Enumerated(EnumType.STRING)
    private MatchType matchType;

    @Size(max = 100, message = "Stipulation must be less than 100 characters")
    private String stipulation;

    @FutureOrPresent(message = "Start time must be in the present or future")
    private Date startTime;

    @Positive(message = "Duration must be positive")
    private Integer durationMinutes;

    @Size(min = 2, message = "At least one participant required")
    private List<UpdateParticipantDto> participants;

}
