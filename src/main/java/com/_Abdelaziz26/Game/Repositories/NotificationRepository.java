package com._Abdelaziz26.Game.Repositories;

import com._Abdelaziz26.Game.DTOs.Whishlist.WishListItemDto;
import com._Abdelaziz26.Game.Enums.NotificationType;
import com._Abdelaziz26.Game.Model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    public List<Notification> findAllByUser_Id(Long userId);
    public List<Notification> findAllByUser_IdAndType(Long userId, NotificationType type);
    public List<Notification> findAllByUser_IdAndRead(Long userId, boolean read);
    public boolean existsByIdAndUser_Id(Long id, Long userId);
}
