package g9varB;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Field extends JPanel {
    private boolean pause = false;
    private int stateErase = 0;
    //инициирование перерисовки окна
    private ArrayList<StreamBall> Balls = new ArrayList(10);
    private Timer repaintTimer = new Timer(10, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Field.this.repaint();
        }
    });

    public Field() {
        this.setBackground(Color.white);
        this.repaintTimer.start();
    }
//перерисовка компонента
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D)g;
        Iterator var3 = this.Balls.iterator();

        while(var3.hasNext()) {
            StreamBall ball = (StreamBall)var3.next();
            ball.paint(canvas);
            ball.setStateErase(this.stateErase);
        }

    }

    public void addBall() {
        this.Balls.add(new StreamBall(this));
    }

    public synchronized void pause() {
        this.pause = true;
    }
//пробуждение всех потоков
    public synchronized void resume() {
        this.pause = false;
        this.notifyAll();
    }

    public void stopBall() throws InterruptedException {
        this.wait();
    }

    public void setStateErase(int erase) {
        this.stateErase = erase;
    }
//проверка режима паузы
    public synchronized void canMove(StreamBall Ball) throws InterruptedException {
        if (this.pause) {
            this.wait();
        }

    }
}
