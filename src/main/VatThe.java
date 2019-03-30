/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author kairon
 */
public class VatThe {

    int x, y, rong, cao;
    Image image;
    int vanToc;

    public VatThe(int x, int y, Image image, int vanToc, int rong, int cao) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.vanToc = vanToc;
        this.cao = cao;
        this.rong = rong;
    }

    public void ve(Graphics2D g) {
        g.drawImage(image, x, y, image.getWidth(null), image.getHeight(null), null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCao() {
        return cao;
    }

    public int getRong() {
        return rong;
    }

    public Rectangle getRect() {
        int i = image.getWidth(null);
        int j = image.getHeight(null);
        int a = this.x;
        int b = this.y;
        a += (int) (i * 0.1);
        b += (int) (j * 0.1);
        int c = (int) (i * 0.8);
        int d = (int) (j * 0.8);
        return new Rectangle(a, b, c, d);
    }

    public boolean kiemTraVaCham(VatThe a) {
        if (this.getRect().intersects(a.getRect())) {
            return true;
        }
        return false;
    }
}
