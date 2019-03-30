/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author kairon
 */
public class MyPanel extends JPanel {

    private MayBayTa mayBayTa;
    private int rong, cao;
    private Image anhNen;
    private boolean running;
    private Random random;
    private ArrayList<MayBayDich> arMayBayDich;
    private ArrayList<Dan> danTa, danDich;

    public MyPanel() {
        anhNen = new ImageIcon(getClass().getResource("/image/BackGround.png")).getImage();
        Image anhMayBayDich = new ImageIcon(getClass().getResource("/image/EnemyPlaneSuper.png")).getImage();
        Image anhMayBayTa = new ImageIcon(getClass().getResource("/image/MyPlane.png")).getImage();
        rong = anhNen.getWidth(null);
        cao = anhNen.getHeight(null);
        random = new Random();
        running = true;
        this.setDoubleBuffered(true);
        this.
        danTa = new ArrayList<Dan>();
        danDich = new ArrayList<Dan>();
        arMayBayDich = new ArrayList<MayBayDich>();
        mayBayTa = new MayBayTa(550, 550, anhMayBayTa, 3, rong, cao);
//        setSize(rong, cao);
        setMinimumSize(new Dimension(rong, cao));
        setPreferredSize(new Dimension(rong, cao));
        setFocusable(true);
        for (int i = 0; i < 15; i++) {
            arMayBayDich.add(new MayBayDich(random.nextInt(rong - anhMayBayDich.getWidth(null)), random.nextInt(cao - 200), anhMayBayDich, 2, rong, cao));
        }
        addKeyListener(keyListener);
        new Thread(run).start();
        setCursor(this.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "null"));
    }
    Runnable run = new Runnable() {
        @Override
        public void run() {
            while (running) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
                mayBayTa.diChuyen();
                taBan();
                diChuyenDan();
                kiemTraTrungDan();
                if (mayBayTa.getHp() <= 0) {
                    running = false;
                    gameOver();
                }
                for (MayBayDich d : arMayBayDich) {
                    d.diChuyen();
                    d.doiHuong();
                    dichBan(d);

                }
                if(arMayBayDich.size()<=0)System.exit(0);
                repaint();
            }
        }

    };

    public void kiemTraTrungDan() {
        boolean flag = false;
        for (int i = danDich.size() - 1; i >= 0; i--) {
            if (mayBayTa.getRect().intersects(danDich.get(i).getRect())) {
                danDich.remove(i);
                flag = true;
                break;
            }
        }
        if (flag) {
            mayBayTa.giamHp();
        }
        for (int j = arMayBayDich.size() - 1; j >= 0; j--) {
            MayBayDich d = arMayBayDich.get(j);
            flag = false;
            for (int i = danTa.size() - 1; i >= 0; i--) {
                if (d.getRect().intersects(danTa.get(i).getRect())) {
                    danTa.remove(i);
                    flag = true;
                    break;
                }
            }
            if (flag) {
                arMayBayDich.remove(d);
            }
        }
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(null, "GAME OVER");
        System.exit(0);
    }

    public void taBan() {
        Dan d = mayBayTa.ban();
        if (d != null) {
            danTa.add(d);
        }
    }

    public void dichBan(MayBayDich d) {
        Dan dan = d.ban();
        if (dan != null) {
            danDich.add(dan);
        }
    }

    public void diChuyenDan() {
        for (int i = danTa.size() - 1; i >= 0; i--) {
            if (danTa.get(i).diChuyen()) {
                danTa.remove(i);
            }
        }
        for (int i = danDich.size() - 1; i >= 0; i--) {
            if (danDich.get(i).diChuyen()) {
                danDich.remove(i);
            }
        }
    }

    KeyListener keyListener = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                mayBayTa.lenTren(true);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                mayBayTa.xuongDuoi(true);
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                mayBayTa.sangTrai(true);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                mayBayTa.sangPhai(true);
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                mayBayTa.bopCo(true);
                taBan();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                mayBayTa.lenTren(false);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                mayBayTa.xuongDuoi(false);
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                mayBayTa.sangTrai(false);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                mayBayTa.sangPhai(false);
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                mayBayTa.bopCo(false);
            }
        }
    };

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paint(g2D);
        g2D.drawImage(anhNen, 0, 0, rong, cao, this);
        mayBayTa.ve(g2D);
        for (MayBayDich d : arMayBayDich) {
            d.ve(g2D);
        }
        for (Dan d : danTa) {
            d.ve(g2D);
        }
        for (Dan d : danDich) {
            d.ve(g2D);
        }
    }
}

class MyFrame extends JFrame {

    public MyFrame() {
        JPanel mypanel = new MyPanel();
        add(mypanel);
        setMinimumSize(mypanel.getMinimumSize());
        setSize(1137+18, 600+38+9);
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame();
        myFrame.setVisible(true);
    }
}
