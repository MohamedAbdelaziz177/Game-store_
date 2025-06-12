package com._Abdelaziz26.Game.Repositories;

import com._Abdelaziz26.Game.Model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    Optional<List<Purchase>> findByUser_Id(Long userId);

    Optional<List<Purchase>> findAllOrderByCreatedAtDesc();
}
