package me.discgold.strangerthings;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class About_author extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast;
    OneTable oneTable = new OneTable();
    Delay delay = new Delay();
    public Animation a;
    public TextView textView_about1;
    public TextView textView_about2;
    public TextView textView_about3;


    public int line = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        final TextView textView_about1 = (TextView)findViewById(R.id.textView_about1);
        final TextView textView_about2 = (TextView)findViewById(R.id.textView_about2);
        final TextView textView_about3 = (TextView)findViewById(R.id.textView_about3);

        textView_about1.setText(oneTable.onescenario_ru[19]);
        textView_about2.setText(oneTable.onescenario_ru[18]);
        textView_about3.setText(oneTable.onescenario_ru[17]);

        //внизу будет код, который прячет текст
        textView_about1.setVisibility(View.INVISIBLE);
        textView_about2.setVisibility(View.INVISIBLE);
        textView_about3.setVisibility(View.INVISIBLE);
        //конец скрытия текста

        //команда, которая запускает AsyncTask
        delay.execute();
        //конец команды, которая запускает AsyncTask
        Button button_back_to_start = (Button)findViewById(R.id.button_back_to_start);
        button_back_to_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent (About_author.this, MainActivity.class);
                    startActivity(intent);finish();
                } catch (Exception e) {

                }
            }
        });

        Window w =getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    //с этого места начинается код AsyncTask
    class Delay extends AsyncTask<Void, Integer, Void>{

        @Override
            protected Void doInBackground(Void... params){
            while (line<=2){
                publishProgress(line++);
                try{
                    Thread.sleep(5000);
                    if (isCancelled()) return null;
                }catch(Exception e){}
            }
            return null;
        }

        @Override
        protected void onProgressUpdate (Integer... values){
        final TextView textView_about1 = (TextView)findViewById(R.id.textView_about1);
        final TextView textView_about2 = (TextView)findViewById(R.id.textView_about2);
        final TextView textView_about3 = (TextView)findViewById(R.id.textView_about3);

        final Animation a = AnimationUtils.loadAnimation(About_author.this, R.anim.alpha);

            switch(line){
                case 1: textView_about1.setVisibility(View.VISIBLE); textView_about1.startAnimation(a); break;
                case 2: textView_about2.setVisibility(View.VISIBLE); textView_about2.startAnimation(a); break;
                case 3: textView_about3.setVisibility(View.VISIBLE); textView_about3.startAnimation(a); break;
                default: break;
            }
        }

    }
    //с этого места заканчивается код AsyncTask

    //системная кнопка "Назад" - начало
    @Override
    public void onBackPressed(){
        try{
            Intent intent = new Intent (About_author.this, MainActivity.class);
            startActivity(intent);finish();
        } catch (Exception e) {

        }
    }
    //системная кнопка "Назад" - конец
}
