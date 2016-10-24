package com.tickettoride.entity.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


@Entity
public class GameStats {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer gameId;
	

	private String gameName;
	

	private Date createdTime;
	

	private Date finishTime;
	
	
	private boolean isNewGame;
	
	private boolean isPlaying;
	
	private boolean isEnded;
	
	@OneToMany(targetEntity=Player.class)
	List<Player> players;

	/**
	 * @return the gameId
	 */
	public int getGameId() {
		return gameId;
	}

	/**
	 * @param gameId the gameId to set
	 */
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	/**
	 * @return the gameName
	 */
	public String getGameName() {
		return gameName;
	}

	/**
	 * @param gameName the gameName to set
	 */
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	/**
	 * @return the createdTime
	 */
	public Date getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * @return the finishTime
	 */
	public Date getFinishTime() {
		return finishTime;
	}

	/**
	 * @param finishTime the finishTime to set
	 */
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	/**
	 * @return the players
	 */
//	public List<Player> getPlayers() {
//		return players;
//	}
//
//	/**
//	 * @param players the players to set
//	 */
//	public void setPlayers(List<Player> players) {
//		this.players = players;
//	}

	/**
	 * @param gameId the gameId to set
	 */
	public void setGameId(Integer gameId) {
		this.gameId = gameId;
		
	}

	/**
	 * @return the isNewGame
	 */
	public boolean isNewGame() {
		return isNewGame;
	}

	/**
	 * @param isNewGame the isNewGame to set
	 */
	public void setNewGame(boolean isNewGame) {
		this.isNewGame = isNewGame;
	}

	/**
	 * @return the isPlaying
	 */
	public boolean isPlaying() {
		return isPlaying;
	}

	/**
	 * @param isPlaying the isPlaying to set
	 */
	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	/**
	 * @return the isEnded
	 */
	public boolean isEnded() {
		return isEnded;
	}

	/**
	 * @param isEnded the isEnded to set
	 */
	public void setEnded(boolean isEnded) {
		this.isEnded = isEnded;
	}


	
	

}
