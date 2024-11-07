import java.awt.Graphics;

public class MovingPolygon extends RegularPolygon {
    int xpos;
    int ypos;
    int dx;
    int dy;

    public MovingPolygon(int nsides){
        super(nsides);
        xpos = 0;
        ypos = 0;
        dx = 0;
        dy = 0;
    }

    @Override
    public void step() {

        // do nothing
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
