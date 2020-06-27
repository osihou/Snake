import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JPanel implements Runnable {
    private Random r;

    public Point spoint;
    public int n = 6;
    public boolean gameover = false;
    ArrayList<Point > points = new ArrayList<Point>();
    public int a = 0;

    public KeyHandler kh;
    private Apple apple;

    public boolean tk = false;


    public int x1 = 0,y1 = 0;
    public int m1 = 0,n1 = 0;
    private int score = 0;
    private int bestscore = 0;

    public SnakeGame() {
        kh = new KeyHandler();
        apple = new Apple();
        reset();
    }
    private void reset() {
        r = new Random();

        int headx = r.nextInt(33)+3;
        int heady = r.nextInt(33)+8;

        int ml,nl;

        points.add(0, new Point(headx,heady));

        for(int i = 1; i<n;i++) {

            ml = r.nextInt(3)-1;
            nl = 0;
            if (ml == 0) nl = r.nextInt(2)*2-1;

            points.add(i, new Point(points.get(i-1).x+ml,points.get(i-1).y+nl));
        }
        repaint();
        spoint  = new Point(0,0);
        score = 0;
    }
    public void paint(Graphics g) {

        if(!apple.isThereAnApple()) {
            boolean gco = true;
            boolean tco;
            while(true) {
                tco =false;
                apple.atRandomLocation();
                for (int i = 0; i<n ; i++) {
                    if(apple.atLocation(points.get(i).x,points.get(i).y)) tco = true;
                }
                if(tco == false) { break;}
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
        for(int i = 0 ; i< n; i++ ) {
            spoint = points.get(i);

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
        n = 6;
        gameover = true;
        reset();
        try { Thread.sleep(2500); }
        catch(InterruptedException ex) {}

    }


    public void run() {
        while (true) {


            if((apple.atLocation(points.get(0).x,points.get(0).y))||
               (apple.atLocation(points.get(1).x,points.get(1).y))||
               (apple.atLocation(points.get(2).x,points.get(2).y))) {

                apple.thereIsNotAnApple();

                score = score +10;
                n = n+1;
                points.add(n-1,new Point(m1,n1));
            }

            for(int i =1 ; i<n; i++) if((points.get(0).x == points.get(i).x)&&(points.get(0).y == points.get(i).y)) gameover();


            for(int i = 0; i<38; i++) if((points.get(0).x <= 0)||(points.get(0).y <= 5)||(points.get(0).x >= 38)||(points.get(0).y >= 43)) gameover();


            int o1 = points.get(0).x - points.get(1).x;
            int o2 = points.get(0).y - points.get(1).y;
            ////*
            int x2 = 0;
            int x3 = 0;
            int y3 = 0;
            int y2 = 0;

            m1 = points.get(n-1).x;
            n1 = points.get(n-1).y;

            y2 = points.get(0).y;
            x2 = points.get(0).x;

            points.get(0).y = points.get(0).y+o2;
            points.get(0).x = points.get(0).x+o1;

            for(int i = 1 ; i< n; i++ ) {

                y3 = points.get(i).y;
                points.get(i).y = y2 ;
                y2=y3;
                x3 = points.get(i).x;
                points.get(i).x = x2 ;
                x2=x3;
            }
            //*/
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
                if(a == 38) { ky = -1;if((points.get(0).y - points.get(1).y) == 1) {ky = 1;} }

                if(a == 40) { ky = 1; if((points.get(0).y - points.get(1).y) == -1) {ky = -1;}}

                if(a == 39) { kx = 1; if((points.get(0).x - points.get(1).x) == -1) {kx = -1;} }

                if(a == 37) { kx = -1; if((points.get(0).x - points.get(1).x) == 1) {kx = 1;} }

                int x2 = 0;
                int x3 = 0;
                int y3 = 0;
                int y2 = 0;

                m1 = points.get(n-1).x;
                n1 = points.get(n-1).y;

                y2 = points.get(0).y;
                x2 = points.get(0).x;

                points.get(0).y = points.get(0).y+ky;
                points.get(0).x = points.get(0).x+kx;

                for(int i = 1 ; i< n; i++ ) {

                    y3 = points.get(i).y;
                    points.get(i).y = y2 ;
                    y2=y3;
                    x3 = points.get(i).x;
                    points.get(i).x = x2 ;
                    x2=x3;
                }
            }
            tk = true;
        }
        public void keyReleased(KeyEvent e) {}
        public void keyTyped(KeyEvent e) {}

    }
}
