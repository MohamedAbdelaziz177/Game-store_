package com._Abdelaziz26.WWE.DTOs.Championship;

import com._Abdelaziz26.WWE.DTOs.Match.SimpleMatchDto;
import com._Abdelaziz26.WWE.DTOs.SimpleTitleDto;
import com._Abdelaziz26.WWE.DTOs.Wrestler.SimpleWrestlerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadChampionshipDto {

    private Long id;

    private SimpleWrestlerDto wrestler;

    private SimpleTitleDto title;

    private Date wonDate;

    private Date lostDate;

    private Integer daysHeld;

    private Boolean isCurrent = lostDate == null ||
            lostDate.after(new Date(System.currentTimeMillis()));

    private SimpleMatchDto winningMatch;

    private SimpleMatchDto lossMatch;
}
