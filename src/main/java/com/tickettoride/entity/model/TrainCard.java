package com.tickettoride.entity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class TrainCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cardId;
	
	private String cardType;
	enum cardType{JOKER,RED,ORANGE,YELLOW,GREEN,BLUE,PURPLE,BLACK,WHITE}
	/**
	 * @return the cardId
	 */
	public int getCardId() {
		return cardId;
	}
	/**
	 * @param cardId the cardId to set
	 */
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	/**
	 * @return the cardType
	 */
	public String getCardType() {
		return cardType;
	}
	/**
	 * @param cardType the cardType to set
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	};
	
}
