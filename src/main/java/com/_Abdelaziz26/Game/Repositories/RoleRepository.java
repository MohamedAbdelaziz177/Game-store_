package com._Abdelaziz26.Game.Repositories;

import com._Abdelaziz26.Game.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
