package com.tickettoride.entity.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer playerId;

	
	private String playerName;

	
	private Date createdDate;

//	@JoinColumn(name="hs_GameID") 
//	private GameStats hsGameID;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTrainCars() {
		return trainCars;
	}

	public void setTrainCars(int trainCars) {
		this.trainCars = trainCars;
	}

	@OneToOne(targetEntity = Color.class)
	private Color playerColor;

	private boolean isPlaying;

	@OneToMany(targetEntity=TrainCard.class)
	private List<TrainCard> trainCards;

	@OneToMany(targetEntity=DestinationCard.class)
	private List<DestinationCard> destCard;
	
	private int score;
	
	private int trainCars;

	/**
	 * @return the playerId
	 */
	public Integer getPlayerId() {
		return playerId;
	}

	/**
	 * @param playerId
	 *            the playerId to set
	 */
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @param playerName
	 *            the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the hsGameID
	 */
//	public GameStats getHsGameID() {
//		return hsGameID;
//	}
//
//	/**
//	 * @param hsGameID
//	 *            the hsGameID to set
//	 */
//	public void setHsGameID(GameStats hsGameID) {
//		this.hsGameID = hsGameID;
//	}

	/**
	 * @return the playerColor
	 */
	public Color getPlayerColor() {
		return playerColor;
	}

	/**
	 * @param playerColor
	 *            the playerColor to set
	 */
	public void setPlayerColor(Color playerColor) {
		this.playerColor = playerColor;
	}

	/**
	 * @return the handCards
	 */

	

	/**
	 * @return the trainCards
	 */
	public List<TrainCard> getTrainCards() {
		return trainCards;
	}

	/**
	 * @param trainCards
	 *            the trainCards to set
	 */
	public void setTrainCards(List<TrainCard> trainCards) {
		this.trainCards = trainCards;
	}

	/**
	 * @return the destCard
	 */
	public List<DestinationCard> getDestCard() {
		return destCard;
	}

	/**
	 * @param destCard
	 *            the destCard to set
	 */
	public void setDestCard(List<DestinationCard> destCard) {
		this.destCard = destCard;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

}
