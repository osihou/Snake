
import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("SNAKE");
        SnakeGame snakeGame = new SnakeGame();

        frame.add(snakeGame);
        frame.addKeyListener(snakeGame.kh);


        frame.setLocationRelativeTo(null);
        frame.setSize(390, 420);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        new Thread(snakeGame).start();


    }




}
