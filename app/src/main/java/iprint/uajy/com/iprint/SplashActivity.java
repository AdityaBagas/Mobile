package iprint.uajy.com.iprint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ivLogo = findViewById(R.id.ivLogo);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        ivLogo.startAnimation(myanim);
        final Intent i = new Intent(SplashActivity.this, MainActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}