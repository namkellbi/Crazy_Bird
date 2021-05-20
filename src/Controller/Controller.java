package Controller;

import Entity.Frog;
import Entity.Memory;
import Entity.Pipe;
import GUI.HappyFrog;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author NamNH
 */
public class Controller implements KeyListener {

    private HappyFrog mf = new HappyFrog();
    private Memory memory;
    private Pipe pipeI, pipeII; // declare two pipe
    private Frog frog; 

    private final int HEIGHT_PN = 400; //set height of panel
    private final int WIDTH_PNL = 600; //set width of panel
    private final int Y_FROG_START = 50; //the starting point of the frog
    private final int HEIGHT_PIPE_DEFAULT = 50;
    private final int X_PIPEI_DEFAULT = WIDTH_PNL; // distance of pipe1
    private final int X_PIPEII_DEFAULT = (int) (WIDTH_PNL * 1.5); //distance of pipe2

    private boolean isPause = false; //check user pause the game
    private int point = 0; //count score
    private Timer timer;
    private  JButton btnExit, btnPause, btnSave;
    private JLabel lblPoint;
    private JPanel pnlGame;

    public Controller() {
        initComponents();
        frog = new Frog(Y_FROG_START);
        pnlGame.add(frog.getFrog()); // add frog into the game

        pipeI = new Pipe(X_PIPEI_DEFAULT);
        pipeII = new Pipe(X_PIPEII_DEFAULT);
        //add two pipe equal 4 button 
        pnlGame.add(pipeI.getPipe1()); //top button of pipeI
        pnlGame.add(pipeI.getPipe2()); //bottom button of pipeI
        pnlGame.add(pipeII.getPipe1()); //top button of pipeII
        pnlGame.add(pipeII.getPipe2());//bottom button of pipeII
        
        //Allow user can click in the panel
        pnlGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frog.setIsClicked(true);
            }
        });
        //location of the frog after moved will be save in memory
        memory = new Memory(0, Y_FROG_START, X_PIPEI_DEFAULT, HEIGHT_PIPE_DEFAULT, X_PIPEII_DEFAULT, HEIGHT_PIPE_DEFAULT);
        timer = run();
        timer.start();
    }

    private void initComponents() {
        mf.setVisible(true);
        mf.setResizable(false);
        btnExit = mf.getBtnExit();
        btnPause = mf.getBtnPause();
        btnSave = mf.getBtnSave();
        lblPoint = mf.getLbPoint();
        pnlGame = mf.getPnlGame();
        //set size for panel game
        pnlGame.setSize(WIDTH_PNL, HEIGHT_PN);
        //shaping the panel
        pnlGame.setPreferredSize(new Dimension(WIDTH_PNL, HEIGHT_PN));
        mf.pack();
        //set Border for panel 
        pnlGame.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        
        //Focus on the button is False
        btnExit.setFocusable(false);
        btnPause.setFocusable(false);
        btnSave.setFocusable(false);

        mf.addKeyListener(this);

        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }

        });
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }

        });
    }
    //Handle button Pause
    private void btnPauseActionPerformed(ActionEvent evt) {
        //if user isPause = false, game must be stop time 
        //and setText of Button Pause is Resume
        if (!isPause) {
            timer.stop();
            btnPause.setText("Resume");
            isPause = true;
        } else {
            timer.start();
            btnPause.setText("Pause");
            isPause = false;
        }
    }
 //Handle button Save the game
    private void btnSaveActionPerformed(ActionEvent evt) {
        //Save all event of the game into memory
        memory.setPoint(point);
        memory.setxFrog(frog.getxFrog());
        memory.setxPipeI(pipeI.getxPipe());
        memory.setxPipeII(pipeII.getxPipe());
        memory.setHeightPipeI(pipeI.getHEIGHT());
        memory.setHeightPipeII(pipeII.getHEIGHT());
    }

    private void btnExitActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Timer run() {
        //process time-moving of frog
        Timer timerX = new Timer(18, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //render all of pipe and render frog onto panel screen 
                pipeI.render();
                pipeII.render();
                frog.render();
                updatePoint(); //update point 
                // frog is dead
                if (!frog.isIsAlive(pipeI, pipeII)) {
                    pipeI.render();
                    pipeII.render();
                    frog.render();
                    updatePoint();
                    timer.stop();
                    //user can choice in one of two case when lose game
                    int choice = JOptionPane.showConfirmDialog(null, "Do you want to continue saved game?");
                    switch (choice) {
                        //if user choice "Yes", The game will re-start the point when saved
                        case 0:
                            restartGame(memory.getPoint(), memory.getxFrog(),
                                    memory.getxPipeI(), memory.getHeightPipeI(),
                                    memory.getxPipeII(), memory.getHeightPipeII());
                            break;
                            
                        //if user choice "No", set default all value of the game    
                        case 1:
                            restartGame(0, Y_FROG_START, X_PIPEI_DEFAULT, HEIGHT_PIPE_DEFAULT,
                                    X_PIPEII_DEFAULT, HEIGHT_PIPE_DEFAULT);
                            break;
                        default:
                            System.exit(0);
                    }
                }
            }
        });
        return timerX;
    }
    //Update score after  frog fly the pipe
    public void updatePoint() {
        //get width of two pipe
        int rightCornPipeI = pipeI.getxPipe() + pipeI.getWIDTH();
        int rightCornPipeII = pipeII.getxPipe() + pipeII.getWIDTH();
        //confirm if frog = width of pipe1 or = width of pipe2, then count point+1
        if (frog.getxFrog() == rightCornPipeI || frog.getxFrog() == rightCornPipeII) {
            point++;
            lblPoint.setText(point + ""); // set a new point
        }
    }
    //process when user lose the game
    public void restartGame(int rpoint, int rxFrog, int xPipeI, int heightPipeI, int xPipeII, int heightPipeII) {
        timer.stop();
        //clear all component
        pnlGame.removeAll();
        pnlGame.revalidate();
        pnlGame.repaint();
        //set default value
        point = rpoint;
        lblPoint.setText(point + "");
        frog = new Frog(rxFrog);
        pnlGame.add(frog.getFrog());

        pipeI = new Pipe(xPipeI, heightPipeI);
        pipeII = new Pipe(xPipeII, heightPipeII);

        pnlGame.add(pipeI.getPipe1());
        pnlGame.add(pipeI.getPipe2());
        pnlGame.add(pipeII.getPipe1());
        pnlGame.add(pipeII.getPipe2());

        pnlGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frog.setIsClicked(true);
            }
        });
        timer.start(); 
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            frog.setIsClicked(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
