package com._Abdelaziz26.Game.Controllers;

import com._Abdelaziz26.Game.DTOs.Notification.CreateNotificationDto;
import com._Abdelaziz26.Game.DTOs.Notification.ReadNotificationDto;
import com._Abdelaziz26.Game.Model.User;
import com._Abdelaziz26.Game.Responses.Result_.Error;
import com._Abdelaziz26.Game.Responses.Result_.Result;
import com._Abdelaziz26.Game.Services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController extends _AbdelazizController{

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<Result<List<ReadNotificationDto>, Error>> getAll(@AuthenticationPrincipal User user) {
        Result<List<ReadNotificationDto>, Error> result = notificationService.getAll(user.getId());
        return ResponseEntity.status(resolveStatus(result)).body(result);
    }

    @GetMapping("/unread")
    public ResponseEntity<Result<List<ReadNotificationDto>, Error>> getUnread(@AuthenticationPrincipal User user) {
        Result<List<ReadNotificationDto>, Error> result = notificationService.getUnreadNotifications(user.getId());
        return ResponseEntity.status(resolveStatus(result)).body(result);
    }

    @PutMapping("/{notificationId}/read")
    public ResponseEntity<Result<String, Error>> markAsRead(@AuthenticationPrincipal User user,
                                            @PathVariable Long notificationId) {
        Result<String, Error> result = notificationService.markAsRead(user.getId(), notificationId);
        return ResponseEntity.status(resolveStatus(result)).body(result);
    }

    @PostMapping("/broadcast")
    public ResponseEntity<Result<String, Error>> broadcast(@RequestParam String message) {
        Result<String, Error> result = notificationService.broadcastMessage(message);
        return ResponseEntity.status(resolveStatus(result)).body(result);
    }

    //@PostMapping
    //public Result<ReadNotificationDto, Error> addNotification(@AuthenticationPrincipal User user,
    //                                                          @RequestBody CreateNotificationDto notificationDto) {
    //    return notificationService.addNotification(notificationDto, user);
    //}
}
