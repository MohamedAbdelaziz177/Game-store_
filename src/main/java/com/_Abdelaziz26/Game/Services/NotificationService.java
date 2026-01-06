package com._Abdelaziz26.Game.Services;

import com._Abdelaziz26.Game.DTOs.Notification.CreateNotificationDto;
import com._Abdelaziz26.Game.DTOs.Notification.ReadNotificationDto;
import com._Abdelaziz26.Game.Enums.NotificationType;
import com._Abdelaziz26.Game.Model.Notification;
import com._Abdelaziz26.Game.Model.User;
import com._Abdelaziz26.Game.Repositories.NotificationRepository;
import com._Abdelaziz26.Game.Repositories.UserRepository;
import com._Abdelaziz26.Game.Responses.Result_.Error;
import com._Abdelaziz26.Game.Responses.Result_.Errors;
import com._Abdelaziz26.Game.Responses.Result_.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public Result<List<ReadNotificationDto>, Error> getAll(Long userId) {
        List<Notification> notifications = notificationRepository.findAllByUser_Id(userId);
        return Result.CreateSuccessResult( notifications.stream().map(notification ->
                ReadNotificationDto.builder()
                        .id(notification.getId())
                        .read(notification.isRead())
                        .type(notification.getType().toString())
                        .content(notification.getContent())
                        .build()
        ).toList());
    }

    public Result<List<ReadNotificationDto>, Error> getUnreadNotifications(Long userId) {

        List<Notification> notifications = notificationRepository.findAllByUser_IdAndRead(userId, false);

        return Result.CreateSuccessResult( notifications.stream().map(notification ->
                ReadNotificationDto.builder()
                .id(notification.getId())
                .read(notification.isRead())
                .type(notification.getType().toString())
                .content(notification.getContent())
                .build()
        ).toList());
    }

    public Result<String, Error> markAsRead(Long userId, Long notificationId) {

        Optional<Notification> notification = notificationRepository.findById(notificationId);

        if(notification.isEmpty())
            return Result.CreateErrorResult(Errors.NotFoundErr("Notification Not Found"));

        boolean authorized = notificationRepository.existsByIdAndUser_Id(notificationId, userId);

        if(!authorized)
            return Result.CreateErrorResult(Errors.UnauthorizedErr("You r not authorized to access this resource"));

        notification.get().setRead(true);
        notificationRepository.save(notification.get());

        return Result.CreateSuccessResult("Notification Marked as Read successfully");
    }

    public Result<String, Error> broadcastMessage(String message) {
        messagingTemplate.convertAndSend("/topic/notifications", message);
        return Result.CreateSuccessResult("Notification Broadcast Successfully");
    }

    public Result<ReadNotificationDto, Error> addNotification(CreateNotificationDto notificationDto, User user) {
        Notification notification = Notification.builder()
                .content(notificationDto.getContent())
                .type(Enum.valueOf(NotificationType.class, notificationDto.getType()))
                .user(user)
                .read(false)
                .build();

        Notification savedNotification = notificationRepository.save(notification);

        ReadNotificationDto notificationRes = ReadNotificationDto.builder()
                .id(savedNotification.getId())
                .content(savedNotification.getContent())
                .type(savedNotification.getType().toString())
                .read(false)
                .build();

        this.sendNotification(user.getUsername(), notificationRes);

        return Result.CreateSuccessResult(notificationRes);
    }

    private void sendNotification(String username, ReadNotificationDto notificationDto) {
        messagingTemplate.convertAndSendToUser(username,
                "/queue/notifications",
                notificationDto);
    }

}
