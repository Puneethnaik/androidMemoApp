package com.example.puneeth.memoapp;

/**
 * Created by puneeth on 5/7/18.
 */
public class Command {
    private String typeOfCommand;
    private String command;
    public Command(String type, String command){
        this.typeOfCommand = type;
        this.command = command;
    }
    public void setCommandType(String type){
        this.typeOfCommand = type;
    }
    public void setCommand(String command){
        this.command = command;
    }
    public String getTypeOfCommand(){
        return this.typeOfCommand;
    }
    public String getCommand(){
        return this.command;
    }
}
