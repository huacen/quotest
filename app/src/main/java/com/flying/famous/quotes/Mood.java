package com.flying.famous.quotes;

import java.util.ArrayList;

public class Mood {
    public Mood() {
    }

    public Mood(String url, String name, ArrayList<String> list) {
        this.url = url;
        this.name = name;
        this.list = list;
    }

    public String url;
    public String name;
    public ArrayList<String> list = new ArrayList<>();

}
