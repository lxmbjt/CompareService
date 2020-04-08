package com.example.thefifthhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static EditText num1=null;
    private static EditText num2=null;
    private static TextView result=null;
    private static int bigNum;
    public static Handler handler=new Handler();//更新用户界面

    public static void UpdateGUI(int refreshNum){
        bigNum=refreshNum;
        handler.post(RefreshLabel);
    }

    private static Runnable RefreshLabel=new Runnable(){
        @Override
        public void run() {
            result.setText(String.valueOf(bigNum));
        }};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Bundle bundle=new Bundle();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt1=findViewById((R.id.button));
        num1=findViewById((R.id.num1));
        num2=findViewById(R.id.num2);
        result=findViewById(R.id.showBigNum);


        final Intent serviceIntent=new Intent(this,CompareService.class);

        //点击按钮开启服务
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("num1",num1.getText().toString());
                bundle.putString("num2",num2.getText().toString());
                serviceIntent.putExtras(bundle);
                startService(serviceIntent);
            }
        });
    }

}
