package com.tickettoride.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tickettoride.entity.model.DestinationCard;

@Repository
public interface DestinationCardRepository extends JpaRepository<DestinationCard, Integer> {
	
	@Query("select u from DestinationCard u where u.cardId = ?1")
	DestinationCard findByDestinationCardId(int cardId);
}
