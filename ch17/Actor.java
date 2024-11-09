import java.awt.Graphics;

/**
 * Graphical simulation element.
 */
public interface Actor {
    /**
     * Draws the simulation element in the context.
     * 
     * @param g graphics context
     */
    void draw(Graphics g);

    /**
     * Updates the state of the simulation element.
     */
    void step();


    void collides(Actor a);

    int getx();

    int gety();

    int getdx();

    int getdy();

    void setdx(int n);

    void setdy(int n);
}
