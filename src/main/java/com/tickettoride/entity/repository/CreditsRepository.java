package com.tickettoride.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tickettoride.entity.model.Credits;
/**
 * 
 * @author monishaelumalai
 *
 */
@Repository
public interface CreditsRepository extends JpaRepository<Credits, Integer>{

}
