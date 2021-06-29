package com.mindoktor.mindoktorassignment.common;

import java.util.UUID;

public class UUIDGenerator {

    static private String getUUID(){
        return  UUID.randomUUID().toString();
    }

    static public String getProductUUID(){
        return  "P-" + getUUID();
    }

    static public String getArticleUUID(){
        return  "A-" + getUUID();
    }

}
