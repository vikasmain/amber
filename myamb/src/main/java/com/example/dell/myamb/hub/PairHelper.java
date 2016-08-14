package com.example.dell.myamb.hub;

/**
 * Created by vikas bajpayee on 10-08-2016.
 */public class PairHelper {
    private String name = null;
    private String wikiName = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWikiName() {
        return wikiName;
    }

    public void setWikiName(String wikiName) {
        this.wikiName = wikiName;
    }

    public PairHelper(){

    }


    public PairHelper(String name, String wikiName)
    {
        this.name = name;
        this.wikiName = wikiName;
    }


}
