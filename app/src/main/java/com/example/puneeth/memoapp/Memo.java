package com.example.puneeth.memoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Console;

public class Memo extends AppCompatActivity {
    private int memoId;
    private Globals globalData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        MemoClass memoHandler = ((Globals)this.getApplication()).getMemoHandler();
        final Buffer buffer = ((Globals)this.getApplication()).getBuffer();
        memoId = memoHandler.createMemo();
        final Button saveButton = (Button)findViewById(R.id.saveButton);
        final Button redoButton = (Button)findViewById(R.id.redoButton);
        final Button undoButton = (Button)findViewById(R.id.undoButton);
        this.globalData = (Globals)getApplication();
        final EditText memoBody = (EditText)findViewById(R.id.memoBody);

        final TextWatcher textEditTrack = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                try {
                    Command command = new Command("edit", s.toString());

                    if(globalData.getBufferPointer() >= globalData.getBuffer().getMaxSize()){
                        globalData.clearBuffer();
                    }
                    if(globalData.getBufferPointer() >= globalData.getBuffer().getBufferSize() - 1) {
                        globalData.setBufferPointer(globalData.getBufferPointer() + 1);
                        buffer.writeCommand(command);
                    }

                    Log.i("buffereCounter", "bufferPointer is " + globalData.getBufferPointer() + " " + globalData.getBuffer().getBufferSize());
                    Log.i("buffereCounter", "bufferPointer is " + globalData.getBufferPointer() + " " + s);


                }catch(Exception ex){
                    //Do nothing
                }
                (globalData).setCanRedo(true);
                undoButton.setEnabled(true);
                redoButton.setEnabled(true);
//                (globalData).setBufferPointer(0);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        memoBody.addTextChangedListener(textEditTrack);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToSave = memoBody.getText().toString();
                (globalData).setCanRedo(false);
                undoButton.setEnabled(false);
                redoButton.setEnabled(false);
//                (globalData).setBufferPointer(0);
                (globalData).getWriter().write(memoId, textToSave);
            }
        });
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToSave = memoBody.getText().toString();

                if((globalData).getBufferPointer() <= 0){
                    //(globalData).setBufferPointer(globalData.getBufferPointer() - 1);
                    undoButton.setEnabled(false);
                }
                else{
                    (globalData).setBufferPointer(globalData.getBufferPointer() - 1);
                    Log.i("changed", "undo changed it");
                    Log.i("buffereCounter", "bufferPointer is " + globalData.getBufferPointer());
                    (globalData).setCanRedo(true);
                    undoButton.setEnabled(true);
                    redoButton.setEnabled(true);

                    memoBody.setText(globalData.renderText());
                }
                Log.i("buffereCounter", "bufferPointer is " + globalData.getBufferPointer());
            }
        });
        redoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToSave = memoBody.getText().toString();
                if((globalData).getBufferPointer() >= (globalData).getBuffer().getBufferSize() - 1){
                    Log.i("reachFull", "now it has reached end of buffer");
                    redoButton.setEnabled(false);
                }
                else{
                    (globalData).setBufferPointer(globalData.getBufferPointer() + 1);
                    memoBody.setText(globalData.renderText());

                    (globalData).setCanRedo(true);
                    undoButton.setEnabled(true);
                    redoButton.setEnabled(true);
                }

            }
        });
    }
}
