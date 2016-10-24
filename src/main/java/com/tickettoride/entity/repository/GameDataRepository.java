package com.tickettoride.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tickettoride.entity.model.GameData;

@Repository
public interface GameDataRepository extends JpaRepository<GameData, Integer> {

}
