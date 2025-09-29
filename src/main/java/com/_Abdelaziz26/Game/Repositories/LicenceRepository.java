package com._Abdelaziz26.Game.Repositories;

import com._Abdelaziz26.Game.Model.Licence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LicenceRepository extends JpaRepository<Licence, Long> {
    Optional<Licence> findByKey(String key);
    Optional<Licence> findByPurchase_Id(Long purchaseId);
}
