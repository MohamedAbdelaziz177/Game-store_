package com._Abdelaziz26.Game.Mappers;

import com._Abdelaziz26.Game.DTOs.Whishlist.WishListItemDto;
import com._Abdelaziz26.Game.Model.Wishlist;

public class WishlistMapper {

    public  WishListItemDto toDto(Wishlist wishlist){
        return WishListItemDto.builder()
                .id(wishlist.getGame().getId())
                .name(wishlist.getGame().getName())
                .imageUrl(wishlist.getGame().getImageUrl())
                .WishedAt(wishlist.getCreatedAt())
                .build();
    }
}
