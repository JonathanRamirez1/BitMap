package com.ramiguev.bitmapreal;

import android.os.Build;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    public ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<CheckBox> listBox = new ArrayList<>();

    String bitmap;
    StringBuilder assistant;
    StringBuilder temporary;

    private MutableLiveData<Boolean> isHex;
    public LiveData<Boolean> getHex() {
        if (isHex == null) {
            isHex = new MutableLiveData<>();
        }
        return isHex;
    }

    public String convertBitToHex(String bin) {
        StringBuilder result = new StringBuilder();
        for ( int i=0 ; i<bin.length() ; i+=4 ) {
            int  num = Integer.parseInt(bin.substring( i, i+4) , 2 );
            String replyHex = Integer.toString( num, 16 );
            result.append(replyHex);
        }
        return result.toString().toUpperCase();
    }

    public void getBitmap() {
       temporary = new StringBuilder();
        for (int i=0; i<64; i++){
            temporary.append(list.get(i));
        }
        bitmap = convertBitToHex(temporary.toString());
        isHex.setValue(true);
    }

    public void checkBoxObservers() {
        for (int i = 0; i < 64; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                listBox.get(i).setTooltipText(""+i);
            }
            listBox.get(i).setOnCheckedChangeListener(this::observerCheck);
            list.add(0);
        }
    }

    private void observerCheck(CompoundButton buttonView, boolean isChecked) {
        int index = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            index = Integer.parseInt(String.valueOf(buttonView.getTooltipText()));
        }
        list.set(index, isChecked ? 1 : 0);
        getBitmap();
    }

    public void setOneOrZero() {
        for (int i=0; i<assistant.length(); i++) {
            if (assistant.charAt(i) == '1') {
                list.add(1);
            } else {
                list.add(0);
            }
        }
    }
}