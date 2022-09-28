package com.waleedportfolio.kaybees.Admin.Model;

public class DishModel {
    String dname,ddescription,dprice,ddisprice,dquantity;

    public DishModel(String dname, String ddescription, String dprice, String ddisprice, String dquantity) {
        this.dname = dname;
        this.ddescription = ddescription;
        this.dprice = dprice;
        this.ddisprice = ddisprice;
        this.dquantity = dquantity;
    }
    public DishModel(){

    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDdescription() {
        return ddescription;
    }

    public void setDdescription(String ddescription) {
        this.ddescription = ddescription;
    }

    public String getDprice() {
        return dprice;
    }

    public void setDprice(String dprice) {
        this.dprice = dprice;
    }

    public String getDdisprice() {
        return ddisprice;
    }

    public void setDdisprice(String ddisprice) {
        this.ddisprice = ddisprice;
    }

    public String getDquantity() {
        return dquantity;
    }

    public void setDquantity(String dquantity) {
        this.dquantity = dquantity;
    }
}
