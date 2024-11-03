public class EightsCard extends Card{
    public EightsCard(int rank, int suit){
        super(rank, suit);
    }

        /**
     * Checks whether this card matches another card.
     */
    public boolean match(Card card1) {
        return card1.getSuit() == this.getSuit()
            || card1.getRank() == this.getRank()
            || card1.getRank() == 8 || this.getRank() == 8;
    }

    /**
     * Calculates the card's score (penalty points).
     */
    public int scoreCard(){
        int rank = this.getRank();
        if (rank == 8) {
            return -20;
        } else if (rank > 10) {
            return -10;
        } else {
            return -rank;
        }
    
    }

}