/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener{
    
    private final int[] snakeXLength = new int[700];
    private final int[] snakeYLength = new int[700];
    
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    
    private ImageIcon titleImage;
    private ImageIcon rightMouth;
    private ImageIcon leftMouth;
    private ImageIcon upMouth;
    private ImageIcon downMouth;
    private ImageIcon snakeImage;
    private ImageIcon enemy;
    //...
    private final Timer timer;
    private final int delay = 100;
    
    private int lengthOfSnake = 3;
    private int move = 0;
    
    private final int[] enemyXPos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
    private final int[] enemyYPos = {75, 100, 125, 150, 175, 200, 225,  250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
    
    //khởi tạo vị trí đầu tiên của mồi, random.
    private final Random random = new Random();
    private int xPos = random.nextInt(34); //34 là tổng cái đống số của enemyXPos ở trên 
    private int yPos = random.nextInt(23); //same
    
    private int score = 0;
    
    public GamePlay() {
        //bắt key
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        //set time
        timer = new Timer(delay, this);
        timer.start();
        
    }
    // too lazy to download images ... :(
    public void paint (Graphics g) {
        
        //check xem có rắn di chuyển chưa để khởi tạo rắn tại vị trí mặc định
        if (move == 0) {
            snakeXLength[2] = 50;
            snakeXLength[1] = 75;
            snakeXLength[0] = 100;
            
            snakeYLength[2] = 100;
            snakeYLength[1] = 100;
            snakeYLength[0] = 100;
        }
        
        //set màu viền title
        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);
        //set title
        titleImage = new ImageIcon(getClass().getClassLoader().getResource("snaketitle.jpg"));
        titleImage.paintIcon(this, g, 25, 11);
        //set màu viền cho phần chơi
        g.setColor(Color.white);
        g.drawRect(24, 74, 850, 575);
        //set màu cho backround
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);
        //set score
        g.setColor(Color.yellow);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 700, 30);
        //set ten
//        g.setColor(Color.white);
//        g.setFont(new Font("Arial", Font.ITALIC, 15));
//        g.drawString("Nguyen Hoai Gia Linh", 25, 25);
//        g.drawString("CNTT - Hoa Sen Uni", 50, 60);
        
        //set đầu rắn nè :>
        rightMouth = new ImageIcon(getClass().getClassLoader().getResource("rightmouth.png"));
        rightMouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);
        
        // tạo rắn
        for (int i = 0; i < lengthOfSnake; i++) {
            if (i == 0 && right) {
                rightMouth = new ImageIcon(getClass().getClassLoader().getResource("rightmouth.png"));
                rightMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
            }
            
            if (i == 0 && left) {
                leftMouth = new ImageIcon(getClass().getClassLoader().getResource("leftmouth.png"));
                leftMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
            }
            
            if (i == 0 && up) {
                upMouth = new ImageIcon(getClass().getClassLoader().getResource("upmouth.png"));
                upMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
            }
            
            if (i == 0 && down) {
                downMouth = new ImageIcon(getClass().getClassLoader().getResource("downmouth.png"));
                downMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
            }
            
            if (i != 0) {
                snakeImage = new ImageIcon(getClass().getClassLoader().getResource("snakebody.png"));
                snakeImage.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
            }
        }
        
        //ok vẽ mồi
        enemy = new ImageIcon(getClass().getClassLoader().getResource("enemy.png"));
        enemy.paintIcon(this, g, enemyXPos[xPos], enemyYPos[yPos]);
        //rắn ăn mồi
        if (enemyXPos[xPos] == snakeXLength[0] && enemyYPos[yPos] == snakeYLength[0]) {
            score++;
            lengthOfSnake++; //cộng thân rắn lên
            xPos = random.nextInt(34); //random lại vị trí của mồi
            yPos = random.nextInt(23);
        }
        //set trường hợp rắn tự ăn
        for (int i = 1; i < lengthOfSnake; i++) {
            if (snakeXLength[i] == snakeXLength[0] && snakeYLength[i] == snakeYLength[0]) {
                right = false;
                left = false;
                up = false;
                down = false;
                timer.stop();
                g.setColor(Color.red);
                g.setFont(new Font("Tahoma", Font.BOLD, 50));
                g.drawString("GAME OVER !! :D ", 250, 250);
                
                g.setColor(Color.red);
                g.setFont(new Font("Tahoma", Font.BOLD, 30));
                g.drawString("Press SPACE to play again !", 250, 400);
            }
        }
        
        g.dispose();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        
        if (right) {
            for (int i = lengthOfSnake - 1; i >= 0; i--) {
                snakeYLength[i + 1] = snakeYLength[i]; //đẩy tất cả vị trí của thân rắn sang phải 1 ô để hình của rắn đi tới
            }
            
            for (int i = lengthOfSnake; i >= 0; i--) {
                if (i == 0) {
                    snakeXLength[i] = snakeXLength[i] + 25; // cộng vị trí hình ảnh tăng để rắn đi tới
                }
                else {
                    snakeXLength[i] = snakeXLength[i - 1]; //i hết bằng ko rồi thì trừ đi cho nó bằng 0 còn lết tiếp chứ =)))
                }
                
                if (snakeXLength[i] > 850) { //nếu rắn đụng tường phải thì set giá trị về 25 để đi rắn xuất hiện ở tường bên trái
                    snakeXLength[i] = 25; 
                }
            }
            repaint(); //gọi lại method paint để vẽ lại con rắn :v
        }
        
        if (left) {
            for (int i = lengthOfSnake - 1; i >= 0; i--) {
                snakeYLength[i + 1] = snakeYLength[i]; 
            }
            
            for (int i = lengthOfSnake; i >= 0; i--) {
                if (i == 0) {
                    snakeXLength[i] = snakeXLength[i] - 25; 
                }
                else {
                    snakeXLength[i] = snakeXLength[i - 1]; 
                }
                
                if (snakeXLength[i] < 25) { 
                    snakeXLength[i] = 850; 
                }
            }
            repaint(); //gọi lại method paint để vẽ lại con rắn :v
        }
        
        if (up) {
            for (int i = lengthOfSnake - 1; i >= 0; i--) {
                snakeXLength[i + 1] = snakeXLength[i]; 
            }
            
            for (int i = lengthOfSnake; i >= 0; i--) {
                if (i == 0) {
                    snakeYLength[i] = snakeYLength[i] - 25; 
                }
                else {
                    snakeYLength[i] = snakeYLength[i - 1]; 
                }
                
                if (snakeYLength[i] < 75) { 
                    snakeYLength[i] = 625; 
                }
            }
            repaint(); //gọi lại method paint để vẽ lại con rắn :v
        }
        
        if (down) {
            for (int i = lengthOfSnake - 1; i >= 0; i--) {
                snakeXLength[i + 1] = snakeXLength[i]; 
            }
            
            for (int i = lengthOfSnake; i >= 0; i--) {
                if (i == 0) {
                    snakeYLength[i] = snakeYLength[i] + 25; 
                }
                else {
                    snakeYLength[i] = snakeYLength[i - 1]; 
                }
                
                if (snakeYLength[i] > 625) { 
                    snakeYLength[i] = 75; 
                }
            }
            repaint(); //gọi lại method paint để vẽ lại con rắn :v
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            move = 0;
            score = 0;
            lengthOfSnake = 3;
            repaint();
            timer.start();
        }
        
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            move++;
            right = true;
            
            if (!left) {
                right = true;
            }
            else {
                right = false;
                left = true;
            }
            up = false;
            down = false;
        }
        
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            move++;
            left = true;
            
            if (!right) {
                left = true;
            }
            else {
                left = false;
                right = true;
            }
            up = false;
            down = false;
        }
        
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            move++;
            up = true;
            
            if (!down) {
                up = true;
            }
            else {
                up = false;
                down = true;
            }
            right = false;
            left = false;
        }
        
        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            move++;
            down = true;
            
            if (!up) {
                down = true;
            }
            else {
                up = true;
                down = false;
            }
            right = false;
            left = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
}
