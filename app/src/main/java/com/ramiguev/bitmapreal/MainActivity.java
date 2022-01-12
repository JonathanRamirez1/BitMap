package com.ramiguev.bitmapreal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import com.ramiguev.bitmapreal.databinding.ActivityMainBinding;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        setObservers();
        addCheckBox();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mainViewModel.checkBoxObservers();
        }
        setChangeText();
    }

    private void setObservers() {
        mainViewModel.getHex().observe(this, hex -> {
            if (hex.equals(true)) {
                binding.editTextHex.setText(mainViewModel.bitmap);
            }
        });
    }

    private String bit(String bit) {
        mainViewModel.assistant = new StringBuilder();
        if (bit.length() == 16) {
            return bit;
        } else {
            for (int j = 0; j < 16 - bit.length(); j++) {
                mainViewModel.assistant.append("0");
            }
            bit = bit + mainViewModel.assistant;
        }
        return bit;
    }

    /**
     * Activa los bits
     **/
    private void bitsActive(String bitmap) {
        mainViewModel.list = new ArrayList<>();
        mainViewModel.assistant = new StringBuilder();

        try {
            if (bitmap.length() != 0) {
                if (bitmap != null) {
                    for (int i = 0; i < bitmap.length(); i++) {
                        int numHex = Integer.parseInt(String.valueOf(bitmap.charAt(i)), 16);
                        String bin = Integer.toBinaryString(numHex);
                        mainViewModel.temporary = new StringBuilder();
                        if (bin.length() != 4) {
                            for (int j = 0; j < 4 - bin.length(); j++) {
                                mainViewModel.temporary.append("0");
                            }
                            bin = mainViewModel.temporary + bin;
                        }
                        mainViewModel.assistant.append(bin);
                    }
                    mainViewModel.setOneOrZero();
                }
            } else {
                for (int k = 0; k < 64; k++) {
                    mainViewModel.list.add(0);
                }
            }
        } catch (Exception e) {
            Log.e("ErrorNumber", "Este fue el error: " + e);
        }
    }

    private void addCheckBox() {
        mainViewModel.listBox.add(binding.checkboxBit1);
        mainViewModel.listBox.add(binding.checkboxBit2);
        mainViewModel.listBox.add(binding.checkboxBit3);
        mainViewModel.listBox.add(binding.checkboxBit4);
        mainViewModel.listBox.add(binding.checkboxBit5);
        mainViewModel.listBox.add(binding.checkboxBit6);
        mainViewModel.listBox.add(binding.checkboxBit7);
        mainViewModel.listBox.add(binding.checkboxBit8);
        mainViewModel.listBox.add(binding.checkboxBit9);
        mainViewModel.listBox.add(binding.checkboxBit10);
        mainViewModel.listBox.add(binding.checkboxBit11);
        mainViewModel.listBox.add(binding.checkboxBit12);
        mainViewModel.listBox.add(binding.checkboxBit13);
        mainViewModel.listBox.add(binding.checkboxBit14);
        mainViewModel.listBox.add(binding.checkboxBit15);
        mainViewModel.listBox.add(binding.checkboxBit16);
        mainViewModel.listBox.add(binding.checkboxBit17);
        mainViewModel.listBox.add(binding.checkboxBit18);
        mainViewModel.listBox.add(binding.checkboxBit19);
        mainViewModel.listBox.add(binding.checkboxBit20);
        mainViewModel.listBox.add(binding.checkboxBit21);
        mainViewModel.listBox.add(binding.checkboxBit22);
        mainViewModel.listBox.add(binding.checkboxBit23);
        mainViewModel.listBox.add(binding.checkboxBit24);
        mainViewModel.listBox.add(binding.checkboxBit25);
        mainViewModel.listBox.add(binding.checkboxBit26);
        mainViewModel.listBox.add(binding.checkboxBit27);
        mainViewModel.listBox.add(binding.checkboxBit28);
        mainViewModel.listBox.add(binding.checkboxBit29);
        mainViewModel.listBox.add(binding.checkboxBit30);
        mainViewModel.listBox.add(binding.checkboxBit31);
        mainViewModel.listBox.add(binding.checkboxBit32);
        mainViewModel.listBox.add(binding.checkboxBit33);
        mainViewModel.listBox.add(binding.checkboxBit34);
        mainViewModel.listBox.add(binding.checkboxBit35);
        mainViewModel.listBox.add(binding.checkboxBit36);
        mainViewModel.listBox.add(binding.checkboxBit37);
        mainViewModel.listBox.add(binding.checkboxBit38);
        mainViewModel.listBox.add(binding.checkboxBit39);
        mainViewModel.listBox.add(binding.checkboxBit40);
        mainViewModel.listBox.add(binding.checkboxBit41);
        mainViewModel.listBox.add(binding.checkboxBit42);
        mainViewModel.listBox.add(binding.checkboxBit43);
        mainViewModel.listBox.add(binding.checkboxBit44);
        mainViewModel.listBox.add(binding.checkboxBit45);
        mainViewModel.listBox.add(binding.checkboxBit46);
        mainViewModel.listBox.add(binding.checkboxBit47);
        mainViewModel.listBox.add(binding.checkboxBit48);
        mainViewModel.listBox.add(binding.checkboxBit49);
        mainViewModel.listBox.add(binding.checkboxBit50);
        mainViewModel.listBox.add(binding.checkboxBit51);
        mainViewModel.listBox.add(binding.checkboxBit52);
        mainViewModel.listBox.add(binding.checkboxBit53);
        mainViewModel.listBox.add(binding.checkboxBit54);
        mainViewModel.listBox.add(binding.checkboxBit55);
        mainViewModel.listBox.add(binding.checkboxBit56);
        mainViewModel.listBox.add(binding.checkboxBit57);
        mainViewModel.listBox.add(binding.checkboxBit58);
        mainViewModel.listBox.add(binding.checkboxBit59);
        mainViewModel.listBox.add(binding.checkboxBit60);
        mainViewModel.listBox.add(binding.checkboxBit61);
        mainViewModel.listBox.add(binding.checkboxBit62);
        mainViewModel.listBox.add(binding.checkboxBit63);
        mainViewModel.listBox.add(binding.checkboxBit64);
    }

    private void setChangeText() {
        binding.editTextHex.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable) {
                String hex = binding.editTextHex.getText().toString();
                bitsActive(bit(hex));
                for (int i = 0; i < 64; i++) {
                    mainViewModel.listBox.get(i).setChecked(mainViewModel.list.get(i) == 1);
                }
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.editTextHex.setSelection(start);
                binding.editTextHex.requestFocus();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
    }
}