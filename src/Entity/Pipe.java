package Entity;

import java.util.Random;
import javax.swing.JButton;

/**
 *
 * @author NamNH
 */
public class Pipe {
    
    private final int HEIGHT_PNL = 400;
    private final int WIDTH_PNL = 600;

    private int xPipe = WIDTH_PNL;
    private final int yPipe = 0;

    private final int WIDTH = 75;
    private int HEIGHT = 0;

    private final int MIN_HEIGHT = 30;
    private final int MAX_HEIGHT = 200;

    private final int SPLIT = 200;
    private final int X_DOWN = 5;
    private JButton pipe1, pipe2;

    public Pipe(int xPipe) {
        this.xPipe = xPipe;
        HEIGHT = randomInRange(MIN_HEIGHT, MAX_HEIGHT);
        pipe1 = new JButton();
        pipe2 = new JButton();
        pipe1.setFocusable(false);
        pipe2.setFocusable(false);
    }

    public Pipe(int xPipe, int height) {
        this.xPipe = xPipe;
        HEIGHT = height;
        pipe1 = new JButton();
        pipe2 = new JButton();
        pipe1.setFocusable(false);
        pipe2.setFocusable(false);     
    }

    public void render() {
        pipe1.setBounds(xPipe, yPipe, WIDTH, HEIGHT);
        pipe2.setBounds(xPipe, yPipe + HEIGHT + SPLIT, WIDTH, HEIGHT_PNL - (HEIGHT + SPLIT));
        update();
    }

    public void update() {
        if (xPipe <= -WIDTH) {
            xPipe = WIDTH_PNL;
            HEIGHT = randomInRange(MIN_HEIGHT, MAX_HEIGHT);
        }
        xPipe -= X_DOWN;
    }

    private int randomInRange(int MIN_HEIGHT, int MAX_HEIGHT) {
        Random rand = new Random();
        return rand.nextInt((MAX_HEIGHT - MIN_HEIGHT) + 1) + MIN_HEIGHT;
    }

    public int getxPipe() {
        return xPipe;
    }

    public void setxPipe(int xPipe) {
        this.xPipe = xPipe;
    }

   public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public JButton getPipe1() {
        return pipe1;
    }

    public void setPipe1(JButton pipe1) {
        this.pipe1 = pipe1;
    }

    public JButton getPipe2() {
        return pipe2;
    }

    public void setPipe2(JButton pipe2) {
        this.pipe2 = pipe2;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getSPLIT() {
        return SPLIT;
    }

}
