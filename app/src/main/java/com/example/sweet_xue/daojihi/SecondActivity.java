package com.example.sweet_xue.daojihi;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Timer;


public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1,btn2;
    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);


        if (DaoJiShi.getDaoJiShi().getNumber() > 0){
            btn2.setText(DaoJiShi.getDaoJiShi().getNumber() +"s");
            DaoJiShi.getDaoJiShi().getTimer().cancel();
            Timer timer = null;
            DaoJiShi.getDaoJiShi().setTimer(timer);

            DaoJiShi.getDaoJiShi().daoJiShi(new TimeInterface() {
                @Override
                public void callback(int number) {
                    SecondActivity.this.number = number;
                    handler.sendEmptyMessage(0);
                }
            });
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                finish();
                break;

            case R.id.btn2:

                DaoJiShi.getDaoJiShi().daoJiShi(new TimeInterface() {
                    @Override
                    public void callback(int number) {
                        SecondActivity.this.number = number;
                        handler.sendEmptyMessage(0);
                    }
                });
                break;
        }
    }


    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 0:
                    chage();
                    break;
                default:
                    break;
            }
        }
    };


    public void chage(){
        if (number <= 0){
            btn2.setText("重发");
            btn2.setClickable(true);
        }else {
            btn2.setText(number +"s");
            btn2.setClickable(false);
        }
    }
}
