package com.example.sshopappui;

public class item_class {
    int itemImage;
    String itemTitle,price;
    public item_class() {
    }
    public item_class(int itemImage,String itemTitle,String price){
        this.itemImage = itemImage;
        this.itemTitle = itemTitle;
        this.price = price;
    }

    public int getItemImage() {
        return itemImage;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public String getPrice() {
        return price;
    }


    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
