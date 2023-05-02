
package blackjack;


public class Card {
    private final int suit,rank,value;

    public Card(int suit, int rank, int value) 
    {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public Card(Card c) //copy constructor
    {
        this(c.suit,c.rank,c.value);
    }

    public int getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public int getRank() {
        return rank;
    }
    
}

