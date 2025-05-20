package com._Abdelaziz26.WWE.DTOs.Match;

import com._Abdelaziz26.WWE.DTOs.Participant.ReadParticipantDto;
import com._Abdelaziz26.WWE.Enums.MatchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadMatchDto {

    private Long id;

    private Long eventId;

    private MatchType matchType;

    private String stipulation;

    private Date startTime;

    private Date endTime;

    private Integer durationMinutes;

    private Integer matchOrder;

    private List<ReadParticipantDto> participants;

}
