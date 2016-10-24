package com.tickettoride.entity.model;

import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class GameData {
	
	@Id
	private int GameDataID;
	
	@OneToMany(targetEntity=Player.class)
	private List<Player> players;
	private int turn;
	
	@OneToMany(targetEntity=DestinationCard.class, mappedBy="cardId", fetch=FetchType.EAGER)
	private List<DestinationCard> deckDestinationCards;
	@OneToMany(targetEntity=TrainCard.class)
	private List<TrainCard> deckTrainCards;
	private HashMap<Integer, Route> UsedRoutes;
	
	@OneToMany(targetEntity=TrainCard.class)
	private List<TrainCard> faceUpCards;
	
	private int turnType;
	
	private boolean faceUpTurnBool;
	
	/**
	 * @return the gameDataID
	 */
	public int getGameDataID() {
		return GameDataID;
	}
	/**
	 * @param gameDataID the gameDataID to set
	 */
	public void setGameDataID(int gameDataID) {
		GameDataID = gameDataID;
	}
	/**
	 * @return the faceUpTurnBool
	 */
	public boolean isFaceUpTurnBool() {
		return faceUpTurnBool;
	}
	/**
	 * @param faceUpTurnBool the faceUpTurnBool to set
	 */
	public void setFaceUpTurnBool(boolean faceUpTurnBool) {
		this.faceUpTurnBool = faceUpTurnBool;
	}
	public List<TrainCard> getFaceUpCards() {
		return faceUpCards;
	}
	public void setFaceUpCards(List<TrainCard> faceUpCards) {
		this.faceUpCards = faceUpCards;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public List<DestinationCard> getDeckDestinationCards() {
		return deckDestinationCards;
	}
	public void setDeckDestinationCards(List<DestinationCard> deckDestinationCards) {
		this.deckDestinationCards = deckDestinationCards;
	}
	public List<TrainCard> getDeckTrainCards() {
		return deckTrainCards;
	}
	public void setDeckTrainCards(List<TrainCard> deckTrainCards) {
		this.deckTrainCards = deckTrainCards;
	}
	public HashMap<Integer, Route> getUsedRoutes() {
		return UsedRoutes;
	}
	public void setUsedRoutes(HashMap<Integer, Route> usedRoutes) {
		UsedRoutes = usedRoutes;
	}
	public int getTurnType() {
		return turnType;
	}
	public void setTurnType(int turnType) {
		this.turnType = turnType;
	}

}
