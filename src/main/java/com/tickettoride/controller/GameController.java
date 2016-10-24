package com.tickettoride.controller;


import com.tickettoride.entity.model.*;
import com.tickettoride.entity.repository.*;
import com.tickettoride.services.GameService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.hibernate.proxy.EntityNotFoundDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * A class to test interactions with the MySQL database using the MapsDao class.
 *
 * @author netgloo
 */
@Controller
public class GameController {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
	private final static Logger LOGGER = Logger
			.getLogger(GameController.class.getName());

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public String test() {
		return "Hi";
	}
	
  /**
   * /create  --> Create a new user and save it in the database.
   * 
   * @param email Maps's email
   * @param name Maps's name
   * @return A string describing if the user is succesfully created or not.
   */
  @RequestMapping(value="/create/{id}",method=RequestMethod.GET)
  @ResponseBody
  public String create(@PathVariable long id) {
    try {
    	Maps maps = new Maps();

//    	maps.setId(0);
        mapsDao.save(maps);
      }
      catch (Exception ex) {
        return "Error updating the user: " + ex.toString();
      }
  return "User succesfully updated!";
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------

  @Autowired
  private MapsRepository mapsDao;
  
  
	
	private Random randomGenerator;
	
	private PlayerRepository playerRepo;
	private DestinationCardRepository dCardRepo;
	private TrainCardRepository tCardRepo;
	private GameDataRepository gameDataRepo;
	private RouteRepository routeRepo;
	
	GameData gameData = new GameData();
	
	private GameService gameService;
  
  @RequestMapping(value = "/playerTurnDestCards/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public List<DestinationCard> playerTurnDestCards(@PathVariable(value = "id") Integer id,
			@RequestBody Player player) {
		
		//Pick three destination card...
		int index;
		List<DestinationCard> dCards = new ArrayList<DestinationCard>();
		gameData.setTurnType(0);
		for(int i=0;i<3;i++)
		{
			index = randomGenerator.nextInt(gameData.getDeckDestinationCards().size());
			DestinationCard dCard = gameData.getDeckDestinationCards().get(index);			
			dCards.add(dCard);			
		}
		
		return dCards;
		//player.setPlayerId(id);
		
	}
	
	@RequestMapping(value = "/selectedDestCard/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity selectedDestCard(@PathVariable(value = "id") Integer id,
			@RequestBody DestinationCard cardId) {
		
		//Select destination card
			Player player = new Player();
			DestinationCard dCard = dCardRepo.findByDestinationCardId(id);
			player = playerRepo.findByPlayerId(gameData.getTurn());
			List<DestinationCard> dCardList = player.getDestCard();
			dCardList.add(dCard);
			player.setDestCard(dCardList);
			gameData.getDeckDestinationCards().remove(cardId);
			playerRepo.save(player);
			endTurn();
			return new ResponseEntity(HttpStatus.OK);
		//player.setPlayerId(id);
		
	}
	
	private void endTurn()
	{
		gameDataRepo.save(gameData);
	}

	@RequestMapping(value = "/playerTurnTraincards_one", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public String playerTurnTraincards_one() {
		gameData.setTurnType(1);
		//get random train card.. hand me de denge..remove from deck
		//refresh his hand UI
		int index;
		Player player = new Player();
		player = playerRepo.findByPlayerId(gameData.getTurn());
		index = randomGenerator.nextInt(gameData.getDeckTrainCards().size());
		TrainCard tCard = gameData.getDeckTrainCards().get(index);
		List<TrainCard> tCardList = player.getTrainCards();
		tCardList.add(tCard);
		player.setTrainCards(tCardList);
		playerRepo.save(player);
		gameData.getDeckTrainCards().remove(tCard);
		return "Choose another card from deck";	
	}
	
	
	@RequestMapping(value = "/playerTurnTraincards_two", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity playerTurnTraincards_two() {
		//get random train card.. hand me de denge..remove from deck
		//refresh his hand UI
		int index;
		Player player = new Player();
		player = playerRepo.findByPlayerId(gameData.getTurn());
		index = randomGenerator.nextInt(gameData.getDeckTrainCards().size());
		TrainCard tCard = gameData.getDeckTrainCards().get(index);
		List<TrainCard> tCardList = player.getTrainCards();
		tCardList.add(tCard);
		player.setTrainCards(tCardList);
		playerRepo.save(player);
		gameData.getDeckTrainCards().remove(tCard);
		endTurn();
		return new ResponseEntity(HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/playerTurnFaceUpCard/{CardId}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public String playerTurnFaceUpCard(int cardId) {
		//so if comes here first time..if its JOKER endTurn..if not joker..set faceUpTurnBool = true and send msg to select one more card frm face up.
		//if comes second time.. i e faceUpTurnBool == true, we will allow him to choose anythn other than joker & clear faceUpTurnBool = false and endTurn
		TrainCard tCard = tCardRepo.findByCardId(cardId);
		List<TrainCard> faceUpCards = gameData.getFaceUpCards();
		Player player = new Player();
		player = playerRepo.findByPlayerId(gameData.getTurn());
		List<TrainCard> tCardList = player.getTrainCards();
		
		if(!gameData.isFaceUpTurnBool())  //First Time
		{			
			if(tCard.getCardType().equals("JOKER"))  //if JOKER.. process and end turn..
			{
				//add joker to the hand of the player & remove from faceup and add a card to faceup from trainDeck and remove it from train deck
				
				tCardList.add(tCard);
				player.setTrainCards(tCardList);
				faceUpCards.remove(cardId);
				
				// adding one card from train deck to faceupCards
				int index = randomGenerator.nextInt(gameData.getDeckTrainCards().size());
				TrainCard tCard1 = gameData.getDeckTrainCards().get(index);
				gameData.getDeckTrainCards().remove(tCard1);
				faceUpCards.add(tCard1);
				gameData.setFaceUpCards(faceUpCards);
				endTurn();
				return "Success";
			}
			else{ //some other card
				gameData.setFaceUpTurnBool(true);
				tCardList.add(tCard);
				player.setTrainCards(tCardList);
				faceUpCards.remove(cardId);
				int index = randomGenerator.nextInt(gameData.getDeckTrainCards().size());
				TrainCard tCard1 = gameData.getDeckTrainCards().get(index);
				gameData.getDeckTrainCards().remove(tCard1);
				faceUpCards.add(tCard1);
				gameData.setFaceUpCards(faceUpCards);
				gameDataRepo.save(gameData);   // Save changes to game data, Turn hasnt ended though..
				return "Get one more card from Train Deck";
			}
		}
		else{   //Second Card selection..
			if(!tCard.getCardType().equals("JOKER"))
			{
				gameData.setFaceUpTurnBool(false);
				tCardList.add(tCard);
				player.setTrainCards(tCardList);
				faceUpCards.remove(cardId);
				int index = randomGenerator.nextInt(gameData.getDeckTrainCards().size());
				TrainCard tCard1 = gameData.getDeckTrainCards().get(index);
				gameData.getDeckTrainCards().remove(tCard1);
				faceUpCards.add(tCard1);
				gameData.setFaceUpCards(faceUpCards);
				endTurn();
				return "Success";
			}
			else
			{
				return "You cant select JOKER card.";
			}
			
		}
	}
	

	@RequestMapping(value = "/buildRoute/{routeId}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public String buildroute(@PathVariable(value = "routeId") Integer routeId) {
		gameData.setTurnType(2);
		Route route = routeRepo.findByRouteid(routeId);
		Player player = new Player();
		player = playerRepo.findByPlayerId(gameData.getTurn());
		List<TrainCard> handTrainCards = player.getTrainCards();
		int noEligibleCards = 0; //number of cards eligible to built a route
		int noJoker = 0, i=0;
		if(route.getRouteOwner() == null) //route not taken
		{
			for(i=0;i<handTrainCards.size();i++)
			{
				if(handTrainCards.get(i).getCardType().equals(route.getRoute_color()))
				{
					noEligibleCards++;
				}
				if(handTrainCards.get(i).getCardType().equals("JOKER"))
				{
					noJoker++;
				}
			}
			
	
		int removedCards = 0;
		i = 0;
		if(route.getRoute_length() < (noEligibleCards+noJoker))
		{
			//Build route..
			if(route.getRoute_length() < noEligibleCards)  //Route can be built without a joker
			{
				while(removedCards < route.getRoute_length())
				{
					if(handTrainCards.get(i).getCardType().equals(route.getRoute_color()))
					{
						handTrainCards.remove(handTrainCards.get(i));					
						removedCards++;
					}
					i++;
				}		
			}
			else{ //We will need to use few JOKER cards to built a route.
				//This case needs to remove all eligible cards and (routeLength - eligible) joker cards.
				for(i=0;i<handTrainCards.size();i++)
				{
					if(handTrainCards.get(i).getCardType().equals(route.getRoute_color()))
					{
						handTrainCards.remove(handTrainCards.get(i));
					}
				}
				int noJokerCardsToRemove = 0;
				i=0;
				removedCards =0;
				noJokerCardsToRemove = route.getRoute_length() - noEligibleCards;
				while(removedCards < noJokerCardsToRemove)
				{
					if(handTrainCards.get(i).getCardType().equals("JOKER"))
					{
						handTrainCards.remove(handTrainCards.get(i));					
						removedCards++;
					}
					i++;
				}	
			}
			player.setTrainCards(handTrainCards); //update plaeyrs hand
			player.setTrainCars((player.getTrainCars() - route.getRoute_length()));
			playerRepo.save(player);
			endTurn();
			return "Succesfully Built a route";
		}
		else
		{
			return "You can't Build Route here!!";
		}	
		
	}
	else
		return "Route already taken";
}
  
} // class MapsController