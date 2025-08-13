package com.example.bai13_1_customlayout;

public class phone {
    private String namePhone;
    private String imagePhone;

    public phone(String namePhone, String imagePhone) {
        this.namePhone = namePhone;
        this.imagePhone = imagePhone;
    }

    public String getNamePhone() {
        return namePhone;
    }

    public void setNamePhone(String namePhone) {
        this.namePhone = namePhone;
    }

    public String getImagePhone() {
        return imagePhone;
    }

    public void setImagePhone(String imagePhone) {
        this.imagePhone = imagePhone;
    }
}
