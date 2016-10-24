package com.tickettoride.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tickettoride.entity.model.Route;
/**
 * 
 * @author monishaelumalai
 *
 */
@Repository
public interface RouteRepository extends
JpaRepository<Route, Integer> {

	@Query("select u from Route u where u.route_id = ?1")
	Route findByRouteid(Integer route_id);

}
