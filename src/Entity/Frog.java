package Entity;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author NamNH
 */
public class Frog {
    //set size of panel
    private final int HEIGHT_PNL = 400;
    private final int WIDTH_PNL = 600;

    private JLabel frog;

    // distance between frog and pipe when the user starts the game
    private  int xFrog = 200;

    // distance between frog and the left side
    private int yFrog = 200;
    private int yFrog_Change = 200;//value of yFrog when motion
    //size of frog
    private final int WIDTH = 50;
    private int HEIGHT = 50;

    private float acceleration = (float) 0.2; //speed of motion down
    private int time;
    //check frog is alive
    private boolean isAlive = true;
    private boolean isClicked = false;
    //velocity of frog
    private float velocity = 50;

    public Frog(int yFrog) {
        this.yFrog = yFrog;
        yFrog_Change = yFrog;
        isAlive = true;
        //add frog image into the game
        frog = new JLabel(new ImageIcon(getClass().getResource("/img/flappy.png")));
        time = 0;
        velocity = 0;
    }

//render frog and call function move
    public void render() {
        frog.setBounds(xFrog, yFrog, WIDTH, HEIGHT);
        move();
    }
    private void move() {
        yFrog = (int) (yFrog_Change + -velocity * time + (acceleration * time * time) / 2);
        time++;
    }
    //check Frog is alive
    public boolean isIsAlive(Pipe pipeI, Pipe pipeII) {
        int maxYSafe = HEIGHT_PNL - HEIGHT;
        int maxXSafe = HEIGHT_PNL + WIDTH;
        //if frog touch the floor of panel
        if (yFrog >= maxYSafe) {
            yFrog = maxYSafe;
            isAlive = false; //lost the game
        }
        
        if(yFrog >= maxXSafe){
            yFrog= maxXSafe;
            isAlive = false;
        }
        //else if frog touch the pipe1
        if (checkCollision(pipeI)) {
            isAlive = false; // user lost the game
        } else if (checkCollision(pipeII)) {
            isAlive = false;
        }
        return isAlive;
    }

    public JLabel getFrog() {
        return frog;
    }

    public void setFrog(JLabel frog) {
        this.frog = frog;
    }

    public int getyFrog() {
        return yFrog;
    }

    public void setyFrog(int yFrog) {
        this.yFrog = yFrog;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isIsClicked() {
        return isClicked;
    }
    //set jump frog
    public void setIsClicked(boolean isClicked) {
        yFrog_Change = yFrog;
        //if frog isclick, velocity of frog jump =5
        if (isClicked) {
            velocity = 5;
        }
        time = 0;
        this.isClicked = isClicked;
    }

    public int getxFrog() {
        return xFrog;
    }
    //check touch the pipe
    private boolean checkCollision(Pipe pipe) {
        //get value of pipe top, pipe bottom
        int minXSafe = xFrog + WIDTH;
        int maxXSafe = xFrog - pipe.getWIDTH();
        int minYSafe = pipe.getHEIGHT();
        int maxYSafe = pipe.getHEIGHT() + pipe.getSPLIT() - HEIGHT;
        //(1)
        if (yFrog <= minYSafe || yFrog >= maxYSafe) {
            //collision left edge
            if (pipe.getxPipe() == minXSafe) {
                    return true;
            }
        }
        if (pipe.getxPipe() < minXSafe && pipe.getxPipe() > maxXSafe) {
            if (yFrog <= minYSafe) { // collision top pipe
                yFrog = minYSafe;
                return true;
            }
            if (yFrog >= maxYSafe) {//collision bottom pipe
                yFrog = maxYSafe;
                return true;
            }
        }
        return false;
    }

}
