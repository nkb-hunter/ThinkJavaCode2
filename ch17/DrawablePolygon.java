import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/**
 * Specialization of Polygon that has a color and the ability to draw itself.
 */
public class DrawablePolygon extends Polygon implements Actor {

    protected Color color;

    /**
     * Creates an empty polygon.
     */
    public DrawablePolygon() {
        super();
        color = Color.GRAY;
    }

    /**
     * Returns a string of point coordinates and color.
     */
    public String toString(){
        String result = "A " + color +" polygon with the following points: ";
        for(int i = 0; i < xpoints.length; i++){
            result += "(" + xpoints[i] + ", " + ypoints[i] + ")";
        }
        return result;
    }


    /**
     * Draws the polygon on the screen.
     * 
     * @param g graphics context
     */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillPolygon(this);
    }

    @Override
    public void step() {
        // do nothing
    }

    /**
     * Test code that creates a ColorPolygon.
     * 
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        DrawablePolygon p = new DrawablePolygon();
        p.addPoint(57, 110);
        p.addPoint(100, 35);
        p.addPoint(143, 110);
        p.color = Color.GREEN;
        System.out.println(p);
    }

}
