package blackjack;

import java.util.Scanner;


public class BlackJack {

    public static void main(String[] args) 
    {
        GUI gui = new GUI();
        
        Game game=new Game();
        game.create_Cards(game.card_deck);
        game.setplayerInfo(game.players);//set names & two cards
        gui.runGUI( game.card_deck, game.players[0].player_cards,
                game.players[1].player_cards, game.players[2].player_cards, 
                game.players[3].player_cards );
        
        for (int i=0;i<3;i++)//playing the game
        {
            playerTurn(game,i,gui)  ;
        }
        dealerTurn(game,gui);
        get_theWinner(game);
        System.out.printf("Game has been Ended To Exit Close the GUI Window");
    }   
   
    public static void playerTurn(Game game,int i,GUI gui)//static to use in main(which is static)
    {
        int playingKey=1;
        Scanner input =new Scanner(System.in);
        System.out.printf("It's %s turn \n",game.players[i].getName());
        System.out.printf("%s score is : %d \n",game.players[i].getName(),game.players[i].getScore());
        if (game.getHighScore()!=21)
        {
            System.out.printf("Enter 1 to Hit \n"+ "Enter 2 to Stand\n");
            playingKey= input.nextInt();
            while (game.players[i].CanPickCard()&&playingKey==1)
            {
                game.players[i].add_card(game.pickCard(game.card_deck));
                gui.updatePlayerHand(game.players[i].getAddedCard(),i);
            
                if (game.players[i].getScore()>21)
                {
                    System.out.printf("%s is BUSTED with score = %d \n",game.players[i].getName(),game.players[i].getScore());
                    break;
                }
                else if (game.players[i].getScore()==21)
                {
                   System.out.printf("%s has a black jack \n",game.players[i].getName());
                    break;
                } 
                else
                {
                    System.out.printf("Your score is : %d \n",game.players[i].getScore());
                    System.out.printf("Enter 1 to Hit \n"+ "Enter 2 to Stand\n");
                    playingKey= input.nextInt();
                 }
            }
        }
        else//one of the players get black jack in his hand
        {
            System.out.printf("THERE IS A BLACK JACK , YOU CANNOT STAND NOW!\n"
                                +"GET BLACK JACK or YOU WILL LOSE\n");
            System.out.printf("Enter any number to Hit \n");
            playingKey= input.nextInt();
            while (game.players[i].CanPickCard())
            {
                game.players[i].add_card(game.pickCard(game.card_deck));
                gui.updatePlayerHand(game.players[i].getAddedCard(),i);
            
                if (game.players[i].getScore()>21)
                {
                    System.out.printf("%s is BUSTED with score = %d \n",game.players[i].getName(),game.players[i].getScore());
                    break;
                }
                else if (game.players[i].getScore()==21)
                {
                   System.out.printf("%s has a black jack \n",game.players[i].getName());
                    break;
                } 
                else
                {
                    System.out.printf("Your score is : %d \n",game.players[i].getScore());
                    System.out.printf("Enter any number to Hit \n");
                    playingKey= input.nextInt();
                }
            }
        }
        game.updateHighScore(game.players[i].getScore());
    }
   
    public static void dealerTurn(Game game,GUI gui)
    {
        Scanner input =new Scanner(System.in);
        System.out.printf("It's %s turn \n",game.players[3].getName());
        System.out.printf("%s score is : %d \n",game.players[3].getName(),game.players[3].getScore());
        boolean PalyerHits =false;
        while (game.players[3].CanPickCard()&&game.players[3].getScore()<=game.getHighScore())
            //dealer score is't black jack -->play till win or lose
        {
            PalyerHits=true;
            game.players[3].add_card(game.pickCard(game.card_deck));
            
            gui.updateDealerHand(game.players[3].getAddedCard(),game.card_deck);
            if (game.players[3].getScore()>21)
            {
                System.out.printf("DEALER is BUSTED with score = %d \n",game.players[3].getScore());
                break;
            }
            else if (game.players[3].getScore()==21)
            {
                System.out.printf("DEALER has a black jack \n",game.players[3].getScore());
                break;
            }
            else 
            {
                System.out.printf("Dealer score is : %d \n",game.players[3].getScore());
            }
        }
        
        if (!PalyerHits)//dealer won by his initial cards
        {
            gui.updateDealerHand(game.players[3].getAddedCard(),game.card_deck);
        }
        game.updateHighScore(game.players[3].getScore());
    }
    
    public static void get_theWinner(Game game)
    {
        if (game.getHighScore()==0)
        {
            System.out.printf("THERE IS NO WINNERS  , all players are busted");
        }
        else if (game.isDraw())
       {
           System.out.printf("Draw! ,THERE IS NO WINNERS  ");
       }
       else
       {
           for (int i=0;i<=3;i++)
           {
               if (game.getHighScore()==game.players[i].getScore())
            {
                System.out.printf("%s won!\n",game.players[i].getName());
                break;
            } 
           }
       }
        return;
    }
}
