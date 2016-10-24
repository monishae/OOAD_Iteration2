package com.tickettoride.entity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Set colors for all the routes and players involved in the game.
 * @author monishaelumalai
 *
 */
@Entity
public class Color {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer Color_id;
	
	private String Color_name;
	
	private String Color_value;
	/**
	 * @return the color_id
	 */
	public Integer getColor_id() {
		return Color_id;
	}
	/**
	 * @param color_id the color_id to set
	 */
	public void setColor_id(Integer color_id) {
		Color_id = color_id;
	}
	/**
	 * @return the color_name
	 */
	public String getColor_name() {
		return Color_name;
	}
	/**
	 * @param color_name the color_name to set
	 */
	public void setColor_name(String color_name) {
		Color_name = color_name;
	}
	/**
	 * @return the color_value
	 */
	public String getColor_value() {
		return Color_value;
	}
	/**
	 * @param color_value the color_value to set
	 */
	public void setColor_value(String color_value) {
		Color_value = color_value;
	}
	
	

}
