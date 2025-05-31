package com._Abdelaziz26.Game.Repositories;

import com._Abdelaziz26.Game.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
