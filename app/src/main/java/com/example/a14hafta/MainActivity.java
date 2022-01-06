package com.example.a14hafta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    String[] yon={"ileri","Geri"};
    int[] gorseller={R.drawable.bir,R.drawable.iki,R.drawable.uc,R.drawable.dort,R.drawable.bes};
    String[] aciklama={"BİR","İKİ","ÜÇ","DÖRT","BEŞ"};
    Timer zamanlayici=new Timer();
    int sira=0;
    ImageView imageView1;
    ProgressBar progressBar1;
    TextView textView1;
    Spinner spinner1;
    Button button5,button6;
    EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        resim();
        ArrayAdapter<String> adaptor=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,yon);
        spinner1.setAdapter(adaptor);
        progressBar1.setMax(gorseller.length);
        button6.setEnabled(false);
    }

    public void init()
    {
     imageView1=findViewById(R.id.imageView1);
     progressBar1=findViewById(R.id.progressBar1);
     textView1=findViewById(R.id.textView1);
     spinner1=findViewById(R.id.spinner1);
     button5=findViewById(R.id.button5);
     button6=findViewById(R.id.button6);
     editText1=findViewById(R.id.editText1);
    }
    public void resim()
    {
        imageView1.setImageResource(gorseller[sira]);
        textView1.setText(aciklama[sira]);
        progressBar1.setProgress(sira+1);
    }

    public void ilk_gorsel(View view) {
        sira=0;
        resim();
    }

    public void sonraki_resim(View view) {
        sira++;
        resim();
        if(sira==gorseller.length-1)
        {
            sira=-1;
        }

    }

    public void onceki_resim(View view) {
        sira--;
        resim();
        if(sira==0)
        {
            sira=gorseller.length;
        }
    }

    public void son_resim(View view) {
        sira=gorseller.length-1;
        resim();
    }

    public void timer_baslat(View view) {
        int saniye=Integer.parseInt(editText1.getText().toString());
        zamanlayici=new Timer();
        TimerTask gorev=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(spinner1.getSelectedItem().toString()=="ileri")
                            sonraki_resim(view);
                        else
                            onceki_resim(view);

                    }
                });
            }
        };
        zamanlayici.schedule(gorev,0,saniye*1000);
        button5.setEnabled(false);
        button6.setEnabled(true);

    }

    public void timer_durdur(View view) {
        zamanlayici.cancel();
        button5.setEnabled(true);
        button6.setEnabled(false);
    }
}