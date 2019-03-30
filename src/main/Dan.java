/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author kairon
 */
import java.awt.Image;

public class Dan extends VatThe {

    int satThuong;
    int huong;

    public Dan(int x, int y, Image image, int tocDo, int rong, int cao, int satThuong, int huong) {
        super(x, y, image, tocDo, rong, cao);
        this.satThuong = satThuong;
        this.huong = huong;
    }

    public boolean diChuyen() {
        y += vanToc * huong;
        if ((y <= 0) || (y >= (cao - image.getHeight(null)))) {
            return true;
        } else {
            return false;
        }
    }
}
