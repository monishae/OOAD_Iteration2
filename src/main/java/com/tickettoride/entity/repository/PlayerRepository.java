package com.tickettoride.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tickettoride.entity.model.Player;
@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{
	
	Player findByPlayerId(int turn);

}


