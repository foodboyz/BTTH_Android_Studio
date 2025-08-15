package com.example.bai13_1_customlayout;

public class phone {
    private String namePhone;
    private int imagePhone;
    private String pricePhone;


    public phone(String namePhone, int imagePhone, String pricePhone) {
        this.namePhone = namePhone;
        this.imagePhone = imagePhone;
        this.pricePhone = pricePhone;
    }

    // Getters and Setters
    public String getNamePhone() {
        return namePhone;
    }

    public void setNamePhone(String namePhone) {
        this.namePhone = namePhone;
    }

    public int getImagePhone() {
        return imagePhone;
    }

    public void setImagePhone(int imagePhone) {
        this.imagePhone = imagePhone;
    }

    public String getPricePhone() {
        return pricePhone;
    }

    public void setPricePhone(String pricePhone) {
        this.pricePhone = pricePhone;
    }
}
