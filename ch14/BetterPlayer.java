public class BetterPlayer extends Player {

    public BetterPlayer(String name){
        super(name);
    }
    public Card play(Eights eights, Card prev) {
        Card bestChoice = null;
        int index = -1;
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.getCard(i);
            if (card.getRank() == 8) {
                return hand.popCard(i);
            }
            else if (cardMatches(card, prev)){
                if(index == -1){ // first match
                    index = i;
                }
                else if(hand.getCard(index).getRank() < card.getRank()){ // choose larger ranked card
                    index = i;
                }
            }
        }
        // there was no match
        if(index == -1){
            bestChoice = drawForMatch(eights, prev);
            return bestChoice;
        }
        // there was a match at index
        bestChoice = hand.popCard(index);
        return bestChoice;
    }
}