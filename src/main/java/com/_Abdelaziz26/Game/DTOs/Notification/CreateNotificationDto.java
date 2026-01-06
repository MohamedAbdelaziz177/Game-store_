package com._Abdelaziz26.Game.DTOs.Notification;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class CreateNotificationDto {
    private String type;
    private String content;
}
