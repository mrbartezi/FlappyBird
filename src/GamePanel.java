import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    private boolean running = false;
    private Thread thread;
    private Random random;
    private int pipeGap = 300, count = 0, speed = 0, acceleration = -20, pos = -275, fps = 60, colX1 = 800,
            colX2 = colX1 + pipeGap , colX3 = colX2 + pipeGap, colY1, colY2, colY3, gapSize = 160, gameSpeed = 3,
            score = 0;


    public GamePanel(){
        setBackground(new Color(0,170,200));

        Dimension dim = getPreferredSize();
        dim.height = 600;
        dim.width = 800;
        setPreferredSize(dim);

        setFont(new Font("Arial", Font.PLAIN, 33));

        addKeyListener(this);
        setFocusable(true);

        random = new Random();

        colY1 = random.nextInt(400 - gapSize);
        colY2 = random.nextInt(400 - gapSize);
        colY3 = random.nextInt(400 - gapSize);

        thread = new Thread(this);
        start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(new Color(250,250,190));
        Rectangle2D grass = new Rectangle2D.Double(0,525,800,75);
        g2.fill(grass);


        pos = pos + speed;


        if(-pos > 570) {
            speed = 0;
            pos = -570;
        }
        else if(-pos < 0) {
            speed = 0;
            pos = 0;
        }

        if(colX1< -60) {
            colX1 = colX3 + pipeGap;
            colY1 = random.nextInt(400 - gapSize);
        }

        if(colX2< -60) {
            colX2 = colX1 + pipeGap;
            colY2 = random.nextInt(400 - gapSize);
        }

        if(colX3< -60) {
            colX3 = colX2 + pipeGap;
            colY3 = random.nextInt(400 - gapSize);
        }


        g2.setPaint(new Color(10,90,20));
        Rectangle2D rect1 = new Rectangle2D.Double(colX1,colY1 - 500,60,600);
        Rectangle2D rect2 = new Rectangle2D.Double(colX1,colY1 + 100 + gapSize,60,600);

        Rectangle2D rect3 = new Rectangle2D.Double(colX2,colY2 - 500,60,600);
        Rectangle2D rect4 = new Rectangle2D.Double(colX2,colY2 + 100 + gapSize,60,600);

        Rectangle2D rect5 = new Rectangle2D.Double(colX3,colY3 - 500,60,600);
        Rectangle2D rect6 = new Rectangle2D.Double(colX3,colY3 + 100 + gapSize,60,600);



        g2.fill(rect1);
        g2.fill(rect2);
        g2.fill(rect3);
        g2.fill(rect4);
        g2.fill(rect5);
        g2.fill(rect6);

        g2.setPaint(Color.BLACK);
        g2.draw(rect1);
        g2.draw(rect2);
        g2.draw(rect3);
        g2.draw(rect4);
        g2.draw(rect5);
        g2.draw(rect6);


        g2.setPaint(Color.YELLOW);
        Ellipse2D birdBody = new Ellipse2D.Double(385,-pos,40,30);

        g2.fill(birdBody);
        g2.draw(birdBody);

        g2.setPaint(Color.BLACK);
        g2.drawString(score + "", 380,100);
        g2.setPaint(Color.WHITE);
        g2.drawString(score + "", 381,101);

    }

    public void run() {
        while(running) {
            try {
                Thread.sleep(1000/fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            if(count%(fps/10) == 0) {
                speed += acceleration/10;
            }
            colX1 -= gameSpeed;
            colX2 -= gameSpeed;
            colX3 -= gameSpeed;

            if((colX1 <= 385 && colX1 > 385 - gameSpeed) || (colX2 <= 385 && colX2 > 385 - gameSpeed) || (colX3 <= 385 && colX3 > 385 - gameSpeed)) {
                score++;
            }

            if(colX1 <= 423 && colX1 >= 327) {
                if((-pos <= colY1 + 100)||(-pos >= colY1 + 70 + gapSize)) {
                    stop();
                }
            }
            if(colX2 <= 423 && colX2 >= 327) {
                if( -pos <= colY2 + 100 || -pos >= colY2 + 70 + gapSize) {
                    stop();
                }
            }
            if(colX3 <= 423 && colX3 >= 327) {
                if((-pos <= colY3 + 100)||(-pos >= colY3 + 70 + gapSize)) {
                    stop();
                }
            }

            repaint();
        }
    }

    public void start() {
        running = true;
        thread.start();
    }
    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        speed = 8;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
