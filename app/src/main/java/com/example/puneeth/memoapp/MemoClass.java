package com.example.puneeth.memoapp;

/**
 * Created by puneeth on 5/7/18.
 */

import java.util.Random;
public class MemoClass {
    private int memoId;
    public MemoClass(){
        this.memoId = 1;
    }
    public int createMemo(){
        return this.memoId++;
    }
    public void deleteMemo(){

    }
    public void editMemo(){

    }
}
