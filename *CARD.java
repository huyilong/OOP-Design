Inner Join

Inner join is the most common type of Join which 
is used to combine the rows from two tables and create 
a result set containing only such records that are present in 
both the tables based on the joining condition (predicate).

Inner join returns rows when there is at least one match in both tables

If none of the record matches between two tables, 
then INNER JOIN will return a NULL set. 



Outer Join, on the other hand, will return matching rows 
from both tables as well as any unmatched rows from one or 
both the tables (based on whether it is single outer or full outer join respectively).

Outer Join can be full outer or single outer

Notice in our record set that there is no employee in the department 
5 (Logistics). Because of this if we perform inner join, 
then Department 5 does not appear in the above result. 
However in the below query we perform an outer join (dept left outer join emp), 
and we can see this department.

SQL JOIN allows us to “lookup” records on other table based on the given conditions between two tables. For example, if we have the department ID of each employee, then we can use this department ID of the employee table to join with the department ID of department table to lookup department names.

UNION operation allows us to add 2 similar data sets to create resulting data set that contains all the data from the source data sets. Union does not require any condition for joining. For example, if you have 2 employee tables with same structure, you can UNION them to create one result set that will contain all the employees from both of the tables.


public enum Suit{
	Club(0),
	Diamond(1),
	Heart(2),
	Spade(3)

}

What would be the best approach when designing the following classes applying design patterns?

Deck - addCard, deal, shuffle, getTopCard, removeTopCard, removeAllCards
Hand - addCard, removeCard, getCard,removeAllCards
DiscardPile - addCard, getTopCard, removeTopCard, removeAllCards
MeldPile - addCard, removeAllCards


Example of Case 1: Using this to disambiguate variable references. 
In Java setter methods, we commonly pass in an argument with the same 
name as the private member variable we are attempting to set. 
We then assign the argument x to this.x.

public abstract class Card{
	protected int value;
	protected Suit suit;
	private boolean used; // is it used by someone right now?



	public Card(int v, Suit s){
		value = v;
		suit = s;
	}
	//in this case there is no need to disambiguate variable references!!!
	public int getValue(){
		return value;
	}
	public Suit getSuit(){
		return suit;
	}

	public boolean isAvailabel(){
		return !used;
	}

	public void markAvailabel(){
		used = false;
	}

	public void unmarkAvailable(){
		used = true;
	}
}

//deck could has the subclass of card
//implement deck with generics but restricted to the typr of T to Card
public class Deck <T extends Card>{
	private ArrayList<T>/card cards;
	private int dealtIndex = 0;//mark the card dealed so far to continue

	public void setDeck(ArrayList<Card> cards){
		this.cards = cards;
	}

	public void shuffle(){}
	public int remainedCards(){
		return cards.size()-dealtIndex;
	}

	public Card/T dealCard(){
		//single card
	}

	public Card[]/T[] dealHand(){
		//an array of cards
	}
}


public class Hand{
	protected ArrayList<Card> cards = new ArrayList<>();
	private int score;

	public int score(){
		score=0;
		for(Card c: cards){
			score += c.value();
		}
		return score;
	}



	public void addCard(Card c){
		cards.add(c);
	}
}



class Player() {
    protected String name;
    protected Deck hand = new Deck();

    public void addCard(Card c) {
        this.hand.addCard(c);
    }

    // .....
}

class Dealer() extends Player {
    private Deck deck;

    public Dealer(int deckSize) {
        this.deck = new Deck(deckSize);
    }

    public void deal(Player[] players, int numberOfCards) {
        for (player in players) {
            for (int i=0; i<numberOfCards; i++) {
                player.addCard(this.deck.getTopCard());
            } 
        }
    }

    // .....
}

