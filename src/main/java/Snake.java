import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Snake {
    private Random random;

    private int LAST_X;
    private int LAST_Y;

    private int SNAKE_LENGTH;
    ArrayList<Point > points = new ArrayList<>();

    public Snake(){
        SNAKE_LENGTH = 6;
        random = new Random();
        int headX = random.nextInt(33)+3;
        int headY = random.nextInt(33)+8;

        points.add(0, new Point(headX,headY));

        int X_OFFSET,Y_OFFSET;

        for(int i = 1; i<SNAKE_LENGTH;i++) {

            X_OFFSET = random.nextInt(3)-1;

            Y_OFFSET = 0;
            if (X_OFFSET == 0) Y_OFFSET = random.nextInt(2)*2-1;

            points.add(i, new Point(points.get(i-1).x+X_OFFSET,points.get(i-1).y+Y_OFFSET));
        }
    }

    public void increaseLength(){
        SNAKE_LENGTH += 1;
        points.add(SNAKE_LENGTH-1,new Point(LAST_X,LAST_Y));
    }

    public boolean outOfBorder(){
        return (points.get(0).x <= 0) || (points.get(0).y <= 5) || (points.get(0).x >= 38) || (points.get(0).y >= 43);
    }

    public boolean collided(){
        for (int i = 1; i < SNAKE_LENGTH; i++ ){
            if((points.get(0).x == points.get(i).x)&&(points.get(0).y == points.get(i).y)){
                return true;
            }
        }
        return false;
    }

    public int getXDirection(){
        return  points.get(0).x - points.get(1).x;
    }

    public int getYDirection(){
        return  points.get(0).y - points.get(1).y;
    }

    public void saveLastPoint(){
        LAST_X = points.get(SNAKE_LENGTH-1).x;
        LAST_Y = points.get(SNAKE_LENGTH-1).y;
    }

    public void setPoints(int dirX, int dirY ){
        int x3, y3;

        int y2 = points.get(0).y;
        int x2 = points.get(0).x;

        points.get(0).y = points.get(0).y+dirY;
        points.get(0).x = points.get(0).x+dirX;

        for(int i = 1 ; i< SNAKE_LENGTH; i++ ) {

            y3 = points.get(i).y;
            points.get(i).y = y2 ;
            y2=y3;
            x3 = points.get(i).x;
            points.get(i).x = x2 ;
            x2=x3;
        }
    }

    public boolean eatApple(Apple apple){
        for(int i = 0; i < SNAKE_LENGTH; i++){
            if((points.get(i).x == apple.getAppleX()) && (points.get(i).y == apple.getAppleY())){
                return true;
            }
        }

        return false;
    }

    public Point getPoint(int i){
        return points.get(i);
    }

    public int getLength(){
        return SNAKE_LENGTH;
    }




}
