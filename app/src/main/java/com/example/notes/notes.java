package com.example.notes;

public class notes {

    String title;
    String description;

    public notes(){}

    public notes(String title,String description){
        this.title=title;
        this.description=description;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

}
