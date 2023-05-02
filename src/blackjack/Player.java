
package blackjack;



public class Player {
    private String name;
    private int score=0,numOfCards=0;
     public Card player_cards[]=new Card[11];
    private boolean lost=false,blackjack=false;
    
    
    public Player(String name) 
    {
          //  this();
            this.name = name;
            // add_card(rand,);
    }

    public int getNumOfCards() 
    {
        return numOfCards;
    }

    public int getScore() 
    {
        return score;
    }

    public boolean isLost() 
    {
        return lost;
    }

    public void setLost(boolean lost) 
    {
        this.lost = lost;
    }

    public boolean isBlackjack() 
    {
        return blackjack;
    }

    public void setBlackjack(boolean blackjack) 
    {
        this.blackjack = blackjack;
    }
   
    public String getName() 
    {
        return name;
    }
    
    public Card[] getPlayer_cards() 
    {
        return player_cards;
    } 
    
    public void printPlayer_cards() // for debugging purposes (To see taking card in each hit)
    {
        for(int i=0;i<this.numOfCards;i++)
        {
            System.out.printf("suit : %d rank :%d value : %d\n",
                    this.player_cards[i].getSuit(),this.player_cards[i].getRank(),
                    this.player_cards[i].getValue());
        }
    }


    public void add_card(Card card) 
    {
        if (this.CanPickCard()){
            this.player_cards[this.numOfCards] = new Card (card);
            this.update_score(card.getValue());
            this.numOfCards++;
        }
    }
    
    public Card getAddedCard() 
    {
        return this.player_cards[this.getNumOfCards()-1];
    }

    public void update_score(int v)
    {
        this.score+=v;
        
        if (score ==21)
            this.setBlackjack(true);
        if (score >21)
            this.setLost(true);
        
    }
    
    public boolean CanPickCard()
    {
        if (this.isLost()||this.isBlackjack())
            return false;
        return true;
    }
}
