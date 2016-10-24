package com.tickettoride.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tickettoride.entity.model.GameStats;
@Repository
public interface GameStatsRepository extends JpaRepository<GameStats, Integer>{
	
	

}
