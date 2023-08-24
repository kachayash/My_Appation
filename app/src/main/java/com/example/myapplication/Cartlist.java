package com.example.myapplication;

public class Cartlist {
    public String getCartlistid() {
        return cartlistid;
    }

    public void setCartlistid(String cartlistid) {
        this.cartlistid = cartlistid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductimg() {
        return productimg;
    }

    public void setProductimg(String productimg) {
        this.productimg = productimg;
    }

    public String getProductdesc() {
        return productdesc;
    }

    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    String cartlistid;
    String productid;
    String productname;
    String productimg;
    String productdesc;
    String productprice;

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    String totalprice , productqyt;

    public String getProductqyt() {
        return productqyt;
    }

    public void setProductqyt(String productqyt) {
        this.productqyt = productqyt;
    }
}
