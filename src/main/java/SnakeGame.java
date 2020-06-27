import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGame extends JPanel implements Runnable {


    public Point spoint;
    public int n = 6;
    public boolean gameover = false;

    public int a = 0;

    public KeyHandler kh;
    private Apple apple;
    private Snake snake;

    public boolean tk = false;


    public int x1 = 0,y1 = 0;

    private int score = 0;
    private int bestscore = 0;

    public SnakeGame() {
        kh = new KeyHandler();

        reset();
    }
    private void reset() {
        repaint();
        apple = new Apple();
        snake = new Snake();
        spoint  = new Point(0,0);
        score = 0;
    }
    public void paint(Graphics g) {

        if(!apple.isThereAnApple()) {
            while(true) {
                apple.atRandomLocation();
                if(!snake.eatApple(apple)) break;
            }
            apple.thereIsAnApple();
        }

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 390, 420);

        if(tk) {
            g.setColor(Color.RED);
            g.drawString(String.format("SCORE: %d\t BESTSCORE: %d", score, bestscore), 10, 10);
        }

        if(apple.isThereAnApple()) {
            g.setColor(Color.RED);

            x1 = apple.getAppleX()*10;
            y1 = apple.getAppleY()*10-50;

            g.fill3DRect(x1, y1, 10, 10,true);
        }
        for(int i = 0 ; i < snake.getLength(); i++ ) {
            spoint = snake.getPoint(i);

            x1 = spoint.x*10;
            y1 = spoint.y*10-50;

            if( i == 0) { g.setColor(Color.BLACK);}
            else g.setColor(Color.GREEN);


            g.fill3DRect(x1, y1, 10, 10, true);
        }
        if(gameover) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, 390, 420);
            g.setColor(Color.RED);

            g.fill3DRect(80 +20,80 +10, 10, 30, true);
            g.fill3DRect(80+10 +20,80 , 20, 10, true);
            g.fill3DRect(80+10 +20,80+10+30 , 20, 10, true);
            g.fill3DRect(80+20 +20,80+20 , 20, 10, true);
            g.fill3DRect(80+30 +20,80+30 , 10, 10, true);

            g.fill3DRect(80+60 +20,80 , 10, 10, true);
            g.fill3DRect(80+50 +20,80+10 , 10, 40, true);
            g.fill3DRect(80+60 +20,80+20 , 10, 10, true);
            g.fill3DRect(80+70 +20,80+10 , 10, 40, true);

            g.fill3DRect(80+90 +20,80+10 , 10, 40, true);
            g.fill3DRect(80+100 +20,80 , 10, 10, true);
            g.fill3DRect(80+110 +20,80+10 , 10, 10, true);
            g.fill3DRect(80+120 +20,80 , 10, 10, true);
            g.fill3DRect(80+130 +20,80+10 , 10, 40, true);

            g.fill3DRect(80+150 +20,80 , 10, 50, true);
            g.fill3DRect(80+160 +20,80 , 20, 10, true);
            g.fill3DRect(80+160 +20 ,80 +20, 20, 10, true);
            g.fill3DRect(80+160 +20 ,80 +40, 20, 10, true);


            g.fill3DRect(80+40 +10,80 +60, 10, 10, true);
            g.fill3DRect(80+40 ,80 +70, 10, 30, true);
            g.fill3DRect(80+40+10 ,80 +100, 10, 10, true);
            g.fill3DRect(80+40+20 ,80 +70, 10, 30, true);

            g.fill3DRect(80+40 +40,80 +60,10, 40, true);
            g.fill3DRect(80+50+40 ,80 +100, 10, 10, true);
            g.fill3DRect(80+60+40 ,80 +60, 10, 40, true);

            g.fill3DRect(80+40+80 ,80 +60, 10, 50, true);
            g.fill3DRect(80+40+90 ,80 +60, 20, 10, true);
            g.fill3DRect(80+40+90 ,80 +60+20, 20, 10, true);
            g.fill3DRect(80+40+90 ,80 +60+40, 20, 10, true);

            g.fill3DRect(80+40+120 ,80 +60, 10, 50, true);
            g.fill3DRect(80+40+130 ,80 +60, 10, 10, true);
            g.fill3DRect(80+40+140 ,80 +70, 10, 10, true);
            g.fill3DRect(80+40+130 ,80 +80, 10, 10, true);
            g.fill3DRect(80+40+140 ,80 +90, 10, 20, true);

            gameover = false;
        }
        for(int i = 0; i<38; i++) {

            g.setColor(Color.BLACK);
            g.drawLine(10+10*i, 10, 10+10*i, 380);
            g.drawLine(10, 10+10*i, 380, 10+10*i);
        }


    }
    public void gameover() {
        if (score > bestscore) bestscore = score;
        gameover = true;
        reset();
        try { Thread.sleep(2000); }
        catch(InterruptedException ex) {}

    }


    public void run() {
        while (true) {

            if(snake.eatApple(apple)) {
                apple.thereIsNotAnApple();
                score = score +10;
                snake.increaseLength();
            }

            if(snake.collided()) gameover();
            if(snake.outOfBorder()) gameover();


            int o1 = snake.getXDirection();
            int o2 = snake.getYDirection();

            snake.saveLastPoint();
            snake.setPoints(o1, o2);

            repaint();
            try { Thread.sleep(200); }
            catch(InterruptedException ex) {}
        }


    }
    public class KeyHandler implements KeyListener {


        public void keyPressed(KeyEvent e) {
            a= 0;
            a = e.getKeyCode();
            int kx = 0;
            int ky = 0;

            if(a != 0) {
                if(a == 38) { ky = -1;if(snake.getYDirection() == 1) {ky = 1;} }

                if(a == 40) { ky = 1; if(snake.getYDirection()  == -1) {ky = -1;}}

                if(a == 39) { kx = 1; if(snake.getXDirection()  == -1) {kx = -1;} }

                if(a == 37) { kx = -1; if(snake.getXDirection()  == 1) {kx = 1;} }

                snake.saveLastPoint();
                snake.setPoints(kx, ky);
            }
            tk = true;
        }
        public void keyReleased(KeyEvent e) {}
        public void keyTyped(KeyEvent e) {}

    }
}
