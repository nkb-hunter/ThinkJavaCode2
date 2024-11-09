import java.awt.Graphics;

import javax.swing.JFrame;

public class MovingPolygon extends RegularPolygon {
    
    Drawing drawing;
    int xpos;
    int ypos;
    int dx;
    int dy;

    public MovingPolygon(int nsides, Drawing drawing){
        super(nsides);
        xpos = 0;
        ypos = 0;
        dx = 10;
        dy = 1;
        this.drawing = drawing;
    }

    @Override
    public void step() {
        xpos += dx;
        ypos += dy;
        if(xpos < -1 || xpos >= drawing.getWidth()){
            dx = -dx;
        }
        if(ypos < -1 || ypos >= drawing.getHeight()){
            dy = - dy;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.translate(xpos, ypos);
        super.draw(g);
        g.translate(-xpos, -ypos);
    }

    @Override
    public int getdx() {
        return dx;
    }

    @Override
    public int getdy() {
        return dy;
    }

    @Override
    public void setdx(int n) {
        dx = n;
    }

    @Override
    public void setdy(int n) {
        dy = n;
    }

    public static void main(String[] args) {
        // set up the window frame
        
        Drawing drawing = new Drawing(500, 500);
        MovingPolygon mp = new MovingPolygon(4, drawing);
        drawing.add(mp);
        JFrame frame = new JFrame("Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(drawing);
        frame.pack();
        frame.setVisible(true);

        while (true) {
            mp.step();
            drawing.repaint();
            try {
                Thread.sleep(30);  // Slow down the loop for visual effect
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        
    }
}
