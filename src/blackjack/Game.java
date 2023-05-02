package blackjack;

import java.util.Random;
import java.util.Scanner;

public class Game {
    boolean draw=false;
    protected Player players[]=new Player[4];
    public static Card card_deck[]=new Card[52];
    static int HighScore=0;//<=21

    public Game()//empty constructor  
    {
       
    }
 
    public boolean isDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }
    public int getHighScore() {
        return HighScore;
    }

    public static Card[] getCard_deck() {
        return card_deck;
    }

    
    public void create_Cards(Card x[])
    {        
         /*
        Card (suit,rank,value)
        i/13 to change the suit every 13 picked card
        i%13 to get rank from 0:12 every 13 picked card
        i%13 to get value from 1:13 every 13 picked card
        (images value in a seperated case - always10-)
        */
        for (int i =0;i<52;i++){
            if ((i>9&&i<=12)||(i>22&&i<=25)||(i>35&&i<=38)||(i>48&&i<=51))
            {
               x[i]=new Card(i/13,i%13,10); 
            }
            else
            {
                x[i]=new Card(i/13,i%13,(i%13)+1);
                
            }
        }
    }
    
    public Card pickCard(Card cards[])
    {
        Random rand = new Random();
        Card x ;
        int randIndex=rand.nextInt(52);
        while(cards[randIndex]==null)//the case if the card are picked before
        {
            randIndex=rand.nextInt(52);
        }
        x=new Card (cards[randIndex]);
        cards[randIndex]=null;
        return x;
    }
    
    public void updateHighScore(int playerScore)
    {
        if (playerScore==21)
        {
            if (this.HighScore==21)
                this.draw=true;
            else
            {
              this.HighScore=playerScore;
              this.draw=false;
            }
        }
        else if (playerScore>HighScore&&playerScore<21)
        {
            this.HighScore=playerScore;
            this.draw=false;
        }
        else if (playerScore==HighScore)
        {
            this.draw=true;
        }
        
    }
    
    public void setplayerInfo(Player []players)
    {
        String name ;
        Scanner input=new Scanner(System.in);
        for (int i=0;i<3;i++)
        {
            System.out.printf("Enter player %d name : ",i+1);
            name=input.next();
            players[i]=new Player (name);
            for(int t=0;t<2;t++)
            {
                players[i].add_card(pickCard(getCard_deck()));
            }
        }
        players[3]=new Player ("Dealer");
         for(int t=0;t<2;t++)
            {
                players[3].add_card(pickCard(getCard_deck()));
            }

    }
}
