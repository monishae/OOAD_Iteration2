package com.tickettoride.services;

import java.util.List;

import com.tickettoride.entity.model.GameData;
import com.tickettoride.entity.model.Player;
import com.tickettoride.entity.model.Route;


public interface GameService {
	
	void startGame(List<Player> players, int mapId, GameData data);
	
	void playerTurn(int playerId, int type,GameData gameData);
	
	Route BuildRoute();
	
	void GameEnds();
	

}
