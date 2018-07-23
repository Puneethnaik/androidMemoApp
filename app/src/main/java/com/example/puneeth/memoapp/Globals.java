package com.example.puneeth.memoapp;

import android.app.Application;
import android.util.Log;

/**
 * Created by puneeth on 5/7/18.
 */
public class Globals extends Application{
    private MemoClass memoHandler;
    private Buffer buffer;
    private boolean canRedo;
    private int bufferPointer;


    public String getRunningString() {
        return runningString;
    }

    private String runningString;
    private WriteManager writer;
    public Globals(){
        memoHandler = new MemoClass();
        buffer = new Buffer(20);
        canRedo = false;
        bufferPointer = 0;
        runningString = "";// this stores the current text as is on the memo.
        writer = new WriteManager();
    }
    public MemoClass getMemoHandler(){
        return memoHandler;
    }
    public Buffer getBuffer() {
        return buffer;
    }

    public String renderText(){
        String command;
        try {
            command = buffer.getCommand(bufferPointer).getCommand();
        }catch(IndexOutOfBoundsException ex){
            command = buffer.getCommand(bufferPointer - 1).getCommand();
        }
        return command;
    }
    public WriteManager getWriter() {
        return writer;
    }

    public void setWriter(WriteManager writer) {
        this.writer = writer;
    }

    public int getBufferPointer() {
        return bufferPointer;
    }

    public void setBufferPointer(int bufferPointer) {
        this.bufferPointer = bufferPointer;
    }

    public boolean isCanRedo() {
        return canRedo;
    }

    public void setCanRedo(boolean canRedo) {
        this.canRedo = canRedo;
    }

    public void setMemoHandler(MemoClass memoHandler) {
        this.memoHandler = memoHandler;
    }

    public void setBuffer(Buffer buffer) {
        this.buffer = buffer;
    }


    public void clearBuffer(){
        //runningString += renderText();
        buffer.getCommands().clear();
        bufferPointer = 0;
    }
}
