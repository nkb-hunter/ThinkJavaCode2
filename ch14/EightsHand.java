import java.util.ArrayList;

public class EightsHand extends Hand{
    ArrayList<EightsCard> cards;

    public EightsHand(String label){
        super(label);
        cards = new ArrayList<EightsCard>();
    }

    public EightsCard getCard(int i){
        return this.cards.get(i);
    }

    public int scoreHand(){
        int score = 0;
        for(int i = 0; i < this.size(); i++){
            EightsCard card = this.getCard(i);
            score += card.scoreCard();
        }
        return score;
    }
}