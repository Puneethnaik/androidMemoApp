package com.example.puneeth.memoapp;

import java.util.ArrayList;

/**
 * Created by puneeth on 5/7/18.
 */
public class Buffer {

    private ArrayList<Command> commands;



    private int maxSize;
    public Buffer(int size){
        this.maxSize = size;
        this.commands = new ArrayList<Command>();
    }
    public Command getCommand(int position) throws IndexOutOfBoundsException{
        //if position is greater than 0 and less than the size of the buffer.
//        if((position > 0) && (position < maxSize)){

        return commands.get(position);
    }
    public int getMaxSize() {
        return maxSize;
    }
    public ArrayList<Command> getCommands() {
        return commands;
    }
    public int getBufferSize(){
        return this.commands.size();
    }
    public void writeCommand(Command command){
        if(commands.size() == maxSize){
            //initiate Buffer Manager Thread to write the commands to the database
        }
        else{
            commands.add(command);
        }
    }
}
