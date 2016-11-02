package simplejavagame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class SimpleJavaGame extends JFrame implements KeyListener, 
        MouseListener, Runnable{

    // Variables for double buffering
    private Image dbImage;
    private Graphics dbGraphics;
    private int mx, my, boxx, boxy, xdir, ydir;
    private int ballx = 400;
    private int bally = 300;
    
    // Constructor
    SimpleJavaGame(){
        setSize(800, 600);
        setResizable(false);
        setBackground(Color.DARK_GRAY);
        setTitle("Mehh Game 2016");
        setVisible(true);
        addMouseListener(this);
        addKeyListener(this);
        boxx = 400;
        boxy = 300;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void move(){
        boxx += xdir;
        boxy += ydir;
        
        // Basic collision detection
        if(boxx <= 20)
            boxx = 20;
        if(boxx >= 760)
            boxx = 760;
        if(boxy <= 40)
            boxy = 40;
        if(boxy >= 560)
            boxy = 560;
    }
    
    public void moveBall(){
        
        // Detects the wall
        if(ballx <= 20)
            ballx = 20;
        if(ballx >= 760)
            ballx = 760;
        if(bally <= 40)
            bally = 40;
        if(bally >= 560)
            bally = 560;
        
        // Detects the ball
        if (boxx < ballx)
            ballx += 1;
        if (boxx > ballx)
            ballx += -1;
        if (boxy < bally)
            bally += 1;
        if (boxy > bally)
            bally += -1;
    }
    
    @Override
    public void paint(Graphics g){
        dbImage = createImage(getWidth(), getHeight());
        dbGraphics = dbImage.getGraphics();
        paintComponent(dbGraphics);
        g.drawImage(dbImage, 0, 0, this);
    }
    
    public void paintComponent(Graphics g){
        moveBall();
        g.setColor(Color.GREEN);
        g.fillRect(boxx, boxy, 20, 20);
        g.setColor(new Color(0, 126, 143));
        g.fillOval(ballx, bally, 20, 20);
        repaint();
    }

    // Moves when the key is pressed
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // Checks code is it
        if(code == e.VK_UP || code == e.VK_W)
            setYdir(-1);
        if(code == e.VK_DOWN || code == e.VK_S)
            setYdir(1);
        if(code == e.VK_LEFT || code == e.VK_A)
            setXdir(-1);
        if(code == e.VK_RIGHT || code == e.VK_D)
            setXdir(1);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        // Checks code is it
        if(code == e.VK_UP || code == e.VK_W)
            setYdir(0);
        if(code == e.VK_DOWN || code == e.VK_S)
            setYdir(0);
        if(code == e.VK_LEFT || code == e.VK_A)
            setXdir(0);
        if(code == e.VK_RIGHT || code == e.VK_D)
            setXdir(0);  
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    // Setters
    public void setXdir(int xdir) {this.xdir = xdir;}
    public void setYdir(int ydir) {this.ydir = ydir;}

    // Run Method
    @Override
    public void run() {
        while(true){
            try {
                move();
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(SimpleJavaGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Creates the Class
    public static void main(String[] args) {
        SimpleJavaGame game = new SimpleJavaGame();
        Thread thread = new Thread(game);
        thread.start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ballx = e.getX();
        bally = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

}
