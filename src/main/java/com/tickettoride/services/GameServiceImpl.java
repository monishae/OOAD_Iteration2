package com.tickettoride.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.tickettoride.entity.model.DestinationCard;
import com.tickettoride.entity.model.GameData;
import com.tickettoride.entity.model.Maps;
import com.tickettoride.entity.model.Player;
import com.tickettoride.entity.model.Route;
import com.tickettoride.entity.model.TrainCard;
import com.tickettoride.entity.repository.GameStatsRepository;
import com.tickettoride.entity.repository.PlayerRepository;



public class GameServiceImpl implements GameService {
	
	@Autowired
	private GameStatsRepository gameRepo;
	
	@Autowired
	private PlayerRepository playerRepo;

	private Random randomGenerator;

	//GameData gameData = new GameData();
	
	
	

	@Autowired
	public void startGame(List<Player> players, int mapId, GameData gameData) {

		

		gameData.setPlayers(players);
		gameData.setTurn(0);
		gameData.setFaceUpTurnBool(false);
		// Set Color to player !!!! set in UI

	
		Maps map = new Maps();
		// get map from mapId from DB
		
		//Create train card Deck
		List<TrainCard> lst = new ArrayList(); //get this lst from repository
		gameData.setDeckTrainCards(createTrainCards());
		//Create Destination card Deck
		//gameData.setDeckDestinationCards(map.getMap_destinationCards());
		// Set other 5 face up cards
		gameData.setFaceUpCards(createFaceCardList(gameData));
		
		for (Player p : players) {
			//set train cars
			p.setTrainCars(20);
			p.setTrainCards(initialTrainCards(gameData));
			p.setDestCard(initialDestCards(gameData));
			playerRepo.save(p);

		}
		
		//update game data repo...		
		//Loaded return call...so that UI knows..
	}

	private List<DestinationCard> initialDestCards(GameData gameData) {
		// TODO Auto-generated method stub
		int index;
		List<DestinationCard> dCards = new ArrayList<DestinationCard>();
		for(int i=0;i<3;i++)
		{
			index = randomGenerator.nextInt(gameData.getDeckDestinationCards().size());
			DestinationCard dCard = gameData.getDeckDestinationCards().get(index);
			gameData.getDeckDestinationCards().remove(index);
			dCards.add(dCard);
		}
		return dCards;
	}

	private List<TrainCard> initialTrainCards(GameData gameData) {
		// TODO Auto-generated method stub
		int index;
		List<TrainCard> tCards = new ArrayList<TrainCard>();
		for(int i=0;i<5;i++)
		{
			index = randomGenerator.nextInt(gameData.getDeckTrainCards().size());
			TrainCard tCard = gameData.getDeckTrainCards().get(index);
			gameData.getDeckTrainCards().remove(index);
			tCards.add(tCard);
		}
		return tCards;
	}

	private List<TrainCard> createFaceCardList(GameData gameData) {
		// TODO Auto-generated method stub
		int index;
		//List<TrainCard> tCards = new ArrayList<TrainCard>();
		for(int i=0;i<5;i++)
		{
			index = randomGenerator.nextInt(gameData.getDeckTrainCards().size());
			TrainCard tCard = gameData.getDeckTrainCards().get(index);
			gameData.getDeckTrainCards().remove(index);
			gameData.getFaceUpCards().add(tCard);
		}
		return gameData.getFaceUpCards();
	}

	private List<TrainCard> createTrainCards() {
		// TODO Auto-generated method stub
		List<TrainCard> tCards = new ArrayList<TrainCard>();
		//use enum instead of array
		String[] myStringArray = {"JOKER","RED","ORANGE","YELLOW","GREEN","BLUE","PURPLE","BLACK","WHITE"};
		for(int i=0;i<myStringArray.length;i++)
		{
			for(int j=0;j<12;j++)  //12 Train cards for each colour
			{
				TrainCard tcard = new TrainCard();
				tcard.setCardType(myStringArray[i]);
				tCards.add(tcard);
			}
		}
		for(int i=0;i<2;i++)   //+2 joker card, so total 14
		{
			TrainCard tcard = new TrainCard();
			tcard.setCardType(myStringArray[0]);
			tCards.add(tcard);
		}
		
		return tCards;
	}

	public void playerTurn(int playerId, int type,GameData gameData) {
		
		if(type ==  1){
			//Destination cards
			//Pick three destination card...
			int index;
			List<DestinationCard> dCards = new ArrayList<DestinationCard>();
			for(int i=0;i<3;i++)
			{
				index = randomGenerator.nextInt(gameData.getDeckDestinationCards().size());
				DestinationCard dCard = gameData.getDeckDestinationCards().get(index);
				gameData.getDeckDestinationCards().remove(index);
				dCards.add(dCard);
				
			}
			
			
		}
		
//		newGame.setNewGame(false);
//		newGame.setPlaying(true);
//		
//		pickDestinationCard();
//		
//		int turn =2;
//		
//		while(turn>0){
//			try {
//				wait();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//		}
//		
//		if(checkEndGame()){
//			newGame.setPlaying(false);
//			newGame.setEnded(true);
//			GameEnds();
//			
//		}
//		
//	
//		/*pickDestCard();
//		- Pick destination card{
//			KeepCard
//			ReturnCard
//		}
//
//	- Pick train card
//		- Pick train card from deck{
//				turn =2;
//		while(turn){
//			case1:pick	Card	from	Deck
//			if(joker){
//				turn 0;
//			}
//			else {
//				turn- - 
//			}
//			case2: pick Card from face
//			if(joker){
//				turn 0;
//			}
//			else {
//				turn- - 
//			}
//		}*/
//
//		
//		
//		BuildRoute();
	
	}

	
//One of These three methods are going to flow out users whole one turn..
	//UI will send u 3 IDs
	public void pickDestinationCard() {
		// TODO Auto-generated method stub
		int index;
		List<DestinationCard> dCards = new ArrayList<DestinationCard>();
		for(int i=0;i<3;i++)
		{
			//index = randomGenerator.nextInt(gameData.getDeckDestinationCards().size());
			//DestinationCard dCard = gameData.getDeckDestinationCards().get(index);
			//dCards.add(dCard);
		}
		//show these cards to user on some interface and make him select two...
		//suppose he selects cards with these two Ids
		int id1,id2;
		//remove cards with these Ids from Destination Deck and add to Players Dest deck.
	}
	
	public Route BuildRoute() {

		{
			/*- check for eligibility
			{
				-Check route-length train cars
				-check train cards of route color
				-check if route free
			}
			//should we make these also one call
			Post turn logic:
			{	
				- Subtract train cars from player(CheckForEndGame)
				- Subtract train cards from user
				-  updateRouteOwner
				- calculate points
			}*/
		}
		return null;
	}
	
	public void pickTrainCard(){
		
	}

	
	
	private boolean checkEndGame() {
		
		return false;
	}

	public void pickCardFromDeck() {

	}

	public void pickCardFromFaceUp() {

	}

	public void GameEnds() {
		

	}

	private void keepCard() {

	}

	
	
	
}
