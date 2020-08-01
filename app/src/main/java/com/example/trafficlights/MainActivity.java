package com.example.trafficlights;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import static android.graphics.Color.BLUE;

public class MainActivity extends AppCompatActivity {
    private LinearLayout b_1, b_2, b_3;
    private Button button;
    private int counter = 0;
    private boolean start_stop = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//Запрет на поворот экрана
        b_1 = findViewById(R.id.bulb_1);
        b_2 = findViewById(R.id.bulb_2);
        b_3 = findViewById(R.id.bulb_3);
        button = findViewById(R.id.button1);
    }

    public void onClickStart(View view) {
        b_1.setBackgroundColor(getResources().getColor(R.color.green));
        if (!start_stop) {
            start_stop = true;
            button.setText("Стоп");
             new Thread(new Runnable() {
            @Override
            public void run() {
                    while (start_stop) {
                        counter++;
                        runOnUiThread(new Runnable() { //Запустить на основном потоке
                            @Override
                            public void run() {
                                switch (counter){
                                    case 1:
                                        b_1.setBackgroundColor(getResources().getColor(R.color.green));
                                        b_2.setBackgroundColor(getResources().getColor(R.color.ColorDefault));
                                        b_3.setBackgroundColor(getResources().getColor(R.color.ColorDefault));
                                        break;
                                    case 30:
                                        b_1.setBackgroundColor(getResources().getColor(R.color.ColorDefault));
                                        b_2.setBackgroundColor(getResources().getColor(R.color.yellow));
                                        b_3.setBackgroundColor(getResources().getColor(R.color.ColorDefault));
                                        break;
                                    case 33:
                                        b_1.setBackgroundColor(getResources().getColor(R.color.ColorDefault));
                                        b_2.setBackgroundColor(getResources().getColor(R.color.ColorDefault));
                                        b_3.setBackgroundColor(getResources().getColor(R.color.red));
                                        counter = -15;
                                        break;

                                }
                            }
                        });

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }}}
        }).start();
    }
        else {
            start_stop = false;
            b_1.setBackgroundColor(getResources().getColor(R.color.ColorDefault));
            b_2.setBackgroundColor(getResources().getColor(R.color.ColorDefault));
            b_3.setBackgroundColor(getResources().getColor(R.color.ColorDefault));
            counter = 0;
            button.setText("Старт");

        }
    }

    private void OnDestroy(){
        super.onDestroy();
        start_stop = false;
}

}