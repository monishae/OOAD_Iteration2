package com.tickettoride.entity.repository;

import java.util.List;

import com.tickettoride.entity.model.Maps;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface MapsRepository extends CrudRepository<Maps, Long>{

	
	//public List<Maps> list();
	
}