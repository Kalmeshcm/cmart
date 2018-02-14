package com.example.abc.cmart;

import android.app.Activity;

import com.google.gson.annotations.SerializedName;

import  java.io.Serializable;

/**
 * Created by ABC on 04-02-2018.
 */

public class Products{
    @SerializedName("type")
    String ptype;

    @SerializedName("name")
    String pname;

    @SerializedName("desc")
    String pdesc;

    @SerializedName("price")
    String pprice;

    @SerializedName("path")
    String pimageurl;

    @SerializedName("seller")
    String pseller;

    public Products(String id, String ptype, String pname, String pdesc, String pprice, String pimageurl, String pseller) {
        this.ptype = ptype;
        this.pname = pname;
        this.pdesc = pdesc;
        this.pprice = pprice;
        this.pimageurl = pimageurl;
        this.pseller = pseller;
    }

    public String getPseller() {
        return pseller;
    }

    public String getPtype() {
        return ptype;
    }

    public String getPname() {
        return pname;
    }

    public String getPdesc() {
        return pdesc;
    }

    public String getPprice() {
        return pprice;
    }

    public String getPimageurl() {
        return pimageurl;
    }

}
