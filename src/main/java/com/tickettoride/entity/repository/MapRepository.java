package com.tickettoride.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tickettoride.entity.model.Maps;
/**
 * 
 * @author monishaelumalai
 *
 */
@Repository
public interface MapRepository extends JpaRepository<Maps, Integer>  {

}
