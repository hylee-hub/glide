package com.bumptech.glide.integration.concurrent;

import java.util.concurrent.*; 

public class Multiton {

    private HashMap<String, Interface> interfaceMap;

    private static class Holder {
        public static final Multiton INSTANCE = new Multiton();
    }

    public static Multiton getInstance() {
        return Holder.INSTANCE;
    }

    private Multiton(){
        interfaceMap = new HashMap<String, Interface>();        
    }

    public Interface getInterface(String key){
        Interface value = interfaceMap.get(key);
        if(null == value){
            synchronized(interfaceMap){
                // double check locking
                if(interfaceMap.get(key) == null){
                    switch(key){
                    case TypeA : // initialize Type A interface here and put it to map
                        value = new TypeA();
                        interfaceMap.put(key, value);
                        break;
                    case TypeB : // initialize Type B interface here and put it to map
                        value = new TypeB();
                        interfaceMap.put(key, value);
                        break;
                    }
                }
            }
        }
        return interfaceMap.get(key);
    }
