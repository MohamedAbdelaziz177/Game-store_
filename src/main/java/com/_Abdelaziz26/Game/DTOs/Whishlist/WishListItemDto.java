package com._Abdelaziz26.Game.DTOs.Whishlist;

import com._Abdelaziz26.Game.DTOs.Game.GameCardDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class WishListItemDto {

    private Long id;

    private String name;

    private String imageUrl;

    private Date WishedAt;
}
