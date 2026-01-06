package com._Abdelaziz26.Game.DTOs.Notification;

import com._Abdelaziz26.Game.Enums.NotificationType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ReadNotificationDto {

    private Long id;

    private boolean read;

    private String type;

    private String content;
}
