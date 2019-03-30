/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author kairon
 */
public class MayBayTa extends VatThe {

    private int left, right, up, down, fireDelay, quanTinh, hp, bienPhai, bienDuoi;
    private boolean trai, phai, len, xuong, fire;
    private double xDouble, yDouble;
    private Image anhDanTa;

    public MayBayTa(int x, int y, Image image, int vanToc, int rong, int cao) {
        super(x, y, image, vanToc, rong, cao);
        xDouble = x;
        yDouble = y;
        quanTinh = 4;
        hp = 100;
        anhDanTa = new ImageIcon(getClass().getResource("/image/MyBullet_1.png")).getImage();
        bienPhai = this.rong - image.getWidth(null);
        bienDuoi = this.cao - image.getHeight(null);
    }

    public Dan ban() {
        if (fireDelay != 0) {
            fireDelay++;
            if (fireDelay == 10) {
                fireDelay = 0;
            }
        } else if (fire) {
            Dan dan = new Dan(x + (image.getWidth(null) - anhDanTa.getWidth(null)) / 2, y, anhDanTa, 8, rong, cao, 10, -1);
            fireDelay++;
            return dan;
        }
        return null;
    }

    public void diChuyen() {
        int a = 0, b = 0;
        if ((left > 0) && (x > 0)) {
            a--;
            if (!trai) {
                left--;
            }
        }
        if ((right > 0) && (x < bienPhai)) {
            a++;
            if (!phai) {
                right--;
            }
        }
        if ((up > 0) && (y > 0)) {
            b--;
            if (!len) {
                up--;
            }
        }
        if ((down > 0) && (y < bienDuoi)) {
            b++;
            if (!xuong) {
                down--;
            }
        }
        if (a * a * b * b != 0) {
            xDouble += vanToc * 0.707 * a;
            yDouble += vanToc * 0.707 * b;
        } else {
            xDouble += vanToc * a;
            yDouble += vanToc * b;
        }

        x = (int) xDouble;
        y = (int) yDouble;
    }

    public void bopCo(boolean a) {
        fire = a;
    }

    public void sangTrai(boolean a) {
        if (trai = a) {
            left = quanTinh;
        }
    }

    public void sangPhai(boolean a) {
        if (phai = a) {
            right = quanTinh;
        }
    }

    public void lenTren(boolean a) {
        if (len = a) {
            up = quanTinh;
        }
    }

    public void xuongDuoi(boolean a) {
        if (xuong = a) {
            down = quanTinh;
        }
    }

    public void giamHp() {
        hp -= 50;
    }

    public int getHp() {
        return hp;
    }
}
