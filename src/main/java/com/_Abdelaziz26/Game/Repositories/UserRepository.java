package com._Abdelaziz26.Game.Repositories;

import com._Abdelaziz26.Game.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
