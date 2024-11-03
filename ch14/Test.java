/**
 * Test code for Deck and Hand.
 */
public class Test {

    public static void main(String[] args) {
        Deck deck = new Deck("Deck");
        deck.shuffle();

        Hand hand = new Hand("Hand");
        deck.deal(hand, 5);
        hand.display();

        Hand drawPile = new Hand("Draw Pile");
        deck.dealAll(drawPile);
        System.out.printf("Draw Pile has %d cards.\n",
                          drawPile.size());

        BetterPlayer betterPlayer = new BetterPlayer("player");
        Eights eights = new Eights();
        Card card = new Card(7, 2);
        betterPlayer.play(eights, card);

        System.out.println("Testing EightsCard");

        EightsCard ec1 = new EightsCard(7, 2);
        EightsCard ec2 = new EightsCard(7, 3);
        EightsCard ec3 = new EightsCard(8, 1);
        EightsCard ec4 = new EightsCard(2, 0); // doesn't match anything
        System.out.println(ec1.match(ec4)); // should be false
        System.out.println(ec2.match(ec3)); // should be true
        System.out.println(ec1.match(ec2)); // should be true

        System.out.println(ec3.scoreCard()); // should be -20

        EightsHand eHand = new EightsHand("eightsHand");
        System.out.println(eHand.scoreHand());
    }

}
