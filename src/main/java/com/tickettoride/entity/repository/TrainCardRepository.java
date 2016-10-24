package com.tickettoride.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tickettoride.entity.model.TrainCard;

@Repository
public interface TrainCardRepository extends JpaRepository<TrainCard, Integer> {

	@Query("select u from TrainCard u where u.cardId = ?1")
	TrainCard findByCardId(int cardId);

}
