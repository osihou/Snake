import java.util.Random;

public class Apple {

    private Random random;
    private int appleX;
    private int appleY;
    private boolean exists;

    public Apple(){
        exists = false;
        random = new Random();
    }

    public boolean isThereAnApple(){
        return exists;
    }
    public void thereIsAnApple(){
        exists = true;
    }

    public void thereIsNotAnApple(){
        exists = false;
    }

    public void atRandomLocation(){
        appleX = random.nextInt(33)+3;
        appleY = random.nextInt(33)+8;
    }

    public boolean atLocation(int x, int y){
        if((x == appleX) && (y == appleY)){
            return true;
        } else return false;
    }

    public int getAppleX() {
        return appleX;
    }

    public int getAppleY() {
        return appleY;
    }
}
