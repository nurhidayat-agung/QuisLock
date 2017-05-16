package com.amicta.kazt.quislock.model;

/**
 * Created by kazt on 4/14/17.
 */

public class ModelBPMainMenuItem {
    private int idItem;
    private String uangTunai;
    private int point;
    private int imgItem;

    public ModelBPMainMenuItem(int idItem, String uangTunai, int point) {
        this.idItem = idItem;
        this.uangTunai = uangTunai;
        this.point = point;
    }

    public ModelBPMainMenuItem(String uangTunai, int point, int imgItem) {
        this.uangTunai = uangTunai;
        this.point = point;
        this.imgItem = imgItem;
    }

    public ModelBPMainMenuItem(String uangTunai, int point) {
        this.uangTunai = uangTunai;
        this.point = point;
    }

    public ModelBPMainMenuItem(int idItem, String uangTunai, int point, int imgItem) {
        this.idItem = idItem;
        this.uangTunai = uangTunai;
        this.point = point;
        this.imgItem = imgItem;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getUangTunai() {
        return uangTunai;
    }

    public void setUangTunai(String uangTunai) {
        this.uangTunai = uangTunai;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getImgItem() {
        return imgItem;
    }

    public void setImgItem(int imgItem) {
        this.imgItem = imgItem;
    }
}
