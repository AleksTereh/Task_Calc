package ru.alekstereh.task_calc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import ru.alekstereh.task_calc.storage.Theme;
import ru.alekstereh.task_calc.storage.ThemeStorage;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_MEMORY = "key_memory";
    private static final String KEY_OPERATOR = "key_operator";
    private static final String KEY_DISPLAY = "key_display";

    private TextView display;
    private double memory;
    private char operator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ThemeStorage storage = ThemeStorage.getInstance(getApplicationContext());

        Theme savedTheme = storage.getTheme();

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();

                    Theme chosenTheme = (Theme) data.getSerializableExtra(ThemeSelectionActivity.CHOSEN_THEME);

                    storage.saveTheme(chosenTheme);

                    recreate();
                }
            }
        });


        setTheme(savedTheme.getTheme());

        setContentView(R.layout.activity_main);
        initButton();
        operator = '1';
        memory = 0;
        display = findViewById(R.id.txtView);

    }


    private void initButton() {
        Button b1 = findViewById(R.id.btn1);
        b1.setOnClickListener(v -> {
            String value = display.getText().toString() + 1;
            display.setText(value);
        });

        Button b2 = findViewById(R.id.btn2);
        b2.setOnClickListener(v -> {
            String value = display.getText().toString() + 2;
            display.setText(value);
        });

        Button b3 = findViewById(R.id.btn3);
        b3.setOnClickListener(v -> {
            String value = display.getText().toString() + 3;
            display.setText(value);
        });

        Button b4 = findViewById(R.id.btn4);
        b4.setOnClickListener(v -> {
            String value = display.getText().toString() + 4;
            display.setText(value);
        });

        Button b5 = findViewById(R.id.btn5);
        b5.setOnClickListener(v -> {
            String value = display.getText().toString() + 5;
            display.setText(value);
        });

        Button b6 = findViewById(R.id.btn6);
        b6.setOnClickListener(v -> {
            String value = display.getText().toString() + 6;
            display.setText(value);
        });

        Button b7 = findViewById(R.id.btn7);
        b7.setOnClickListener(v -> {
            String value = display.getText().toString() + 7;
            display.setText(value);
        });

        Button b8 = findViewById(R.id.btn8);
        b8.setOnClickListener(v -> {
            String value = display.getText().toString() + 8;
            display.setText(value);
        });

        Button b9 = findViewById(R.id.btn9);
        b9.setOnClickListener(v -> {
            String value = display.getText().toString() + 9;
            display.setText(value);
        });

        Button b0 = findViewById(R.id.btn0);
        b0.setOnClickListener(v -> {
            String value = display.getText().toString() + 0;
            display.setText(value);

        });

        Button bPoint = findViewById(R.id.btnPoint);
        bPoint.setOnClickListener(v -> {
            String value = display.getText().toString() + ".";
            display.setText(value);
        });

        Button bC = findViewById(R.id.btnСlear);
        bC.setOnClickListener(view -> {
            memory = 0;
            display.setText("");
        });

        Button bB = findViewById(R.id.btnBack);
        bB.setOnClickListener(view -> {
            String value = display.getText().toString();
            value = value.substring(0, value.length() - 1);
            display.setText(value);
        });


        Button ans = findViewById(R.id.btnEqually);
        ans.setOnClickListener(view -> {
            switch (operator) {
                case '+':
                    memory += Double.parseDouble(display.getText().toString());
                    display.setText(String.valueOf(memory));
                    break;
                case '-':
                    memory -= Double.parseDouble(display.getText().toString());
                    display.setText(String.valueOf(memory));
                    break;
                case '/':
                    memory /= Double.parseDouble(display.getText().toString());
                    display.setText(String.valueOf(memory));
                    break;
                case '*':
                    memory *= Double.parseDouble(display.getText().toString());
                    display.setText(String.valueOf(memory));
                    break;
            }
            operator = 0;
        });

        Button btnDiv = findViewById(R.id.btnDivision);
        btnDiv.setOnClickListener(v -> {
            memory = Double.parseDouble(display.getText().toString());
            operator = btnDiv.getText().charAt(0);
            display.setText("");
        });

        Button bMul = findViewById(R.id.btnMultiplication);
        bMul.setOnClickListener(v -> {
            memory = Double.parseDouble(display.getText().toString());
            operator = bMul.getText().charAt(0);
            display.setText("");
        });

        Button bSub = findViewById(R.id.btnSubtraction);
        bSub.setOnClickListener(v -> {
            memory = Double.parseDouble(display.getText().toString());
            operator = bSub.getText().charAt(0);
            display.setText("");
        });

        Button bAdd = findViewById(R.id.btnAddition);
        bAdd.setOnClickListener(v -> {
            memory = Double.parseDouble(display.getText().toString());
            operator = bAdd.getText().charAt(0);
            display.setText("");
        });


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putDouble(KEY_MEMORY, memory);
        instanceState.putString(KEY_DISPLAY, display.getText().toString());
        instanceState.putChar(KEY_OPERATOR, operator);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        double value = instanceState.getDouble(KEY_MEMORY);
        String result = instanceState.getString(KEY_DISPLAY);
        char operator = instanceState.getChar(KEY_OPERATOR);
        setConfig(value, result, operator);
    }

    private void setConfig(double value, String result, char operator) {
        memory = value;
        if (operator != 0) {
            this.operator = operator;
        }

        display.setText(result);
    }

}