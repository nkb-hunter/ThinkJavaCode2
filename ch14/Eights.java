import java.util.ArrayList;
import java.util.Scanner;

/**
 * Simulates a game of Crazy Eights.
 * See https://en.wikipedia.org/wiki/Crazy_Eights.
 */
public class Eights {

    private ArrayList<Player> players = new ArrayList<Player>();
    private EightsHand drawPile;
    private EightsHand discardPile;
    private Scanner in;

    /**
     * Initializes the state of the game.
     */
    public Eights() {
        Deck deck = new Deck("Deck");
        deck.shuffle();

        // deal cards to each player
        BetterPlayer one = new BetterPlayer("Allen");
        Player two = new Player("Chris");
        Player three = new Player("Jade");
        players.add(one);
        players.add(two);
        players.add(three);
        for(Player player : players){
            deck.deal(player.getHand(), 5);
        }

        // turn one card face up
        discardPile = new EightsHand("Discards");
        deck.deal(discardPile, 1);

        // put the rest of the deck face down
        drawPile = new EightsHand("Draw pile");
        deck.dealAll(drawPile);

        // create the scanner we'll use to wait for the user
        in = new Scanner(System.in);
    }

    /**
     * Returns true if any hand is empty.
     */
    public boolean isDone() {
        for (Player player : players){
            if(player.getHand().isEmpty()){
                return true;
            }
        }
        return false;
    }

    /**
     * Moves cards from the discard pile to the draw pile and shuffles.
     */
    public void reshuffle() {
        // save the top card
        Card prev = discardPile.popCard();

        // move the rest of the cards
        discardPile.dealAll(drawPile);

        // put the top card back
        discardPile.addCard(prev);

        // shuffle the draw pile
        drawPile.shuffle();
    }

    /**
     * Returns a card from the draw pile.
     */
    public Card drawCard() {
        if (drawPile.isEmpty()) {
            reshuffle();
        }
        return drawPile.popCard();
    }

    /**
     * Switches players.
     */
    public Player nextPlayer(Player current) {
        // update for ArrayList
        Player next = players.get(0); // default first player, and stays this way if we are on the last player
        for (int i = 0; i < players.size(); i++){
            if(players.get(i) == current){
                if (i != players.size()-1){ // if it's not the last player
                    next = players.get(i+1);
                }
            }
        }
        return next;
        // original logic for two players
        // if (current == one) {
        //     return two;
        // } else {
        //     return one;
        // }
    }

    /**
     * Displays the state of the game.
     */
    public void displayState() {
        for(Player player : players){
            player.display();
        }
        discardPile.display();
        System.out.print("Draw pile: ");
        System.out.println(drawPile.size() + " cards");
        // comment out for 100 game simulation
     //   in.nextLine();
    }

    /**
     * One player takes a turn.
     */
    public void takeTurn(Player player) {
        Card prev = discardPile.lastCard();
        Card next = player.play(this, prev);
        discardPile.addCard(next);

        System.out.println(player.getName() + " plays " + next);
        System.out.println();
    }

    /**
     * Plays the game.
     */
    public Player playGame() {
        Player player = players.get(0);
        Player isWinner = player;

        // keep playing until there's a winner
        while (!isDone()) {
            displayState();
            takeTurn(player);
            player = nextPlayer(player);
        }

        // display the final score
        for(Player pl : players){
            pl.displayScore();
            if (pl.score() == 0){
                isWinner = pl;
            }
        }
        return isWinner;
    }

    /**
     * Creates the game and runs it.
     */
    public static void main(String[] args) {
        Eights eights = new Eights(); // just to get number of players
        int numPlayers = eights.players.size();
        ArrayList<Integer> scores = new ArrayList<Integer>(numPlayers);
        for(int i = 0 ; i < numPlayers; i++){
            scores.add(0);
        }
        for(int x = 0; x < 100; x++){
            System.out.println("Iteration: " + x);
            Eights game = new Eights();
            Player winner = game.playGame();
            int i = 0;
            for (Player player : game.players){
                if(winner.equals(player)){
                    scores.set(i, scores.get(i)+1);
                }
                i++;
            }
        }
        for(int i = 0; i < scores.size(); i++){
            System.out.println("Player " + (i + 1) + " won " + scores.get(i));
        }
    }

}
