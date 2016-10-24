package com.tickettoride.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tickettoride.entity.model.Color;
/**
 * @version 
 * @author monishaelumalai
 *
 */
@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {

}
