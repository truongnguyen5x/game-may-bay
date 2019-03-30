/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author kairon
 */
public class MayBayDich extends VatThe {

    private Image anhDanDich;
    private Random random;
    private int ngang, doc, tocDoDanh, timeMove, timeRotate;
    private static int bienPhai, bienTren;

    public MayBayDich(int x, int y, Image image, int tocDo, int rong, int cao) {
        super(x, y, image, tocDo, rong, cao);
        random = new Random();
        tocDoDanh = random.nextInt(500);
        anhDanDich = new ImageIcon(getClass().getResource("/image/EnemyBulletSuper.png")).getImage();
        bienPhai = rong - image.getWidth(null) - vanToc;
        bienTren = cao - image.getHeight(null) - vanToc;
    }

    public void diChuyen() {

        if (timeMove-- == 0) {
            if (x < vanToc) {
                ngang = 1;
            }
            if (y < vanToc) {
                doc = 1;
            }
            if (x > bienPhai) {
                ngang = -1;
            }
            if (y > bienTren) {
                doc = -1;
            }
            x += (int) (vanToc * ngang / Math.sqrt(doc * doc + ngang * ngang));
            y += (int) (vanToc * doc / Math.sqrt(doc * doc + ngang * ngang));
            timeMove = random.nextInt(3);
        }
    }

    public void doiHuong() {
        if (timeRotate-- == 0) {
            doc = random.nextInt(3) - 1;
            ngang = random.nextInt(3) - 1;
            timeRotate = random.nextInt(300);
        }
    }

    public Dan ban() {
        if (tocDoDanh-- == 0) {
            Dan dan = new Dan(x + (image.getWidth(null) - anhDanDich.getWidth(null)) / 2, y + image.getHeight(null), anhDanDich, 2, rong, cao, 20, 1);
            tocDoDanh = random.nextInt(1000);
            return dan;
        }
        return null;
    }
}
