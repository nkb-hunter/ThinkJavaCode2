import javax.swing.JFrame;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Conway's Game of Life.
 * 
 * @author Chris Mayfield
 * @version 7.1.0
 */
public class Conway {

    private GridCanvas grid;

    /**
     * Creates a grid with a glider.
     */
    public Conway() {
        grid = new GridCanvas(5, 10, 20);
        grid.turnOn(2, 1);
        grid.turnOn(3, 2);
        grid.turnOn(3, 3);
        grid.turnOn(2, 3);
        grid.turnOn(1, 3);
        System.out.println(grid.countOn());
    }

    public Conway(String path) {
        File file = new File(path);
        try{
            Scanner scan = new Scanner(file);
            ArrayList<String> board = new ArrayList<String>();
            scan.nextLine(); // skip first line
            int dotIndex = path.lastIndexOf('.');
            String extension = path.substring(dotIndex + 1);
            while(scan.hasNextLine() && extension != "rle"){
                board.add(scan.nextLine());
            }
            if(extension.equals("rle")){
                String dimensions = scan.nextLine(); // x and y line
                String[] lines = dimensions.split(',');
                int x = Integer.parseInt(lines[0].split("\\s+")[2]);
                int y = Integer.parseInt(lines[1].split("\\s+")[2]);
                
                
                String encoding = scan.nextLine();
                String row = "";
                for(int i = 0 ; i < encoding.length; i++){
                    
                    if(encoding.charAt(i) == '$'){
                        if(row.length < x){
                            for(int i = row.length; i < x; i++){
                                row.add(".");
                            }
                        }
                        board.add(row);
                    }
                }
            }
            grid = new GridCanvas(board.size(), board.get(0).length(), 20);
            for(int i = 0; i<board.size(); i++){
                for(int j = 0; j<board.get(0).length(); j++){
                    if(board.get(i).charAt(j) == 'O'){
                        grid.turnOn(i,j);
                    }
                }
            }
        }
        catch(FileNotFoundException e){
            e.getStackTrace();
            System.exit(1);
        }
    }

    /**
     * Counts the number of live neighbors around a cell.
     * 
     * @param r row index
     * @param c column index
     * @return number of live neighbors
     */
    private int countAlive(int r, int c) {
        int count = 0;
        count += grid.test(r - 1, c - 1);
        count += grid.test(r - 1, c);
        count += grid.test(r - 1, c + 1);
        count += grid.test(r, c - 1);
        count += grid.test(r, c + 1);
        count += grid.test(r + 1, c - 1);
        count += grid.test(r + 1, c);
        count += grid.test(r + 1, c + 1);
        return count;
    }

    /**
     * Apply the update rules of Conway's Game of Life.
     * 
     * @param cell the cell to update
     * @param count number of live neighbors
     */
    private static void updateCell(Cell cell, int count) {
        if (cell.isOn()) {
            if (count < 2 || count > 3) {
                // Any live cell with fewer than two live neighbors dies,
                // as if by underpopulation.
                // Any live cell with more than three live neighbors dies,
                // as if by overpopulation.
                cell.turnOff();
            }
        } else {
            if (count == 3) {
                // Any dead cell with exactly three live neighbors
                // becomes a live cell, as if by reproduction.
                cell.turnOn();
            }
        }
    }

    /**
     * Counts the neighbors before changing anything.
     * 
     * @return number of neighbors for each cell
     */
    private int[][] countNeighbors() {
        int rows = grid.numRows();
        int cols = grid.numCols();

        int[][] counts = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                counts[r][c] = countAlive(r, c);
            }
        }
        return counts;
    }

    /**
     * Updates each cell based on neighbor counts.
     * 
     * @param counts number of neighbors for each cell
     */
    private void updateGrid(int[][] counts) {
        int rows = grid.numRows();
        int cols = grid.numCols();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Cell cell = grid.getCell(r, c);
                updateCell(cell, counts[r][c]);
            }
        }
    }

    /**
     * Simulates one round of Conway's Game of Life.
     */
    public void update() {
        int[][] counts = countNeighbors();
        updateGrid(counts);
    }

    /**
     * The simulation loop.
     */
    private void mainloop() {
        while (true) {

            // update the drawing
            this.update();
            grid.repaint();

            // delay the simulation
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // do nothing
            }
        }
    }

    /**
     * Creates and runs the simulation.
     * 
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        String title = "Conway's Game of Life";
        Conway game = new Conway();
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(game.grid);
        frame.pack();
        frame.setVisible(true);
        game.mainloop();
    }

}
