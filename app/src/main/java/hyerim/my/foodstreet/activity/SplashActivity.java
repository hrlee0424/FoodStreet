package hyerim.my.foodstreet.activity;

import androidx.appcompat.app.AppCompatActivity;
import hyerim.my.foodstreet.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    Animation animtext, animimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final ImageView imageView = findViewById(R.id.imageView);
        final TextView textView = findViewById(R.id.textView3);


        animtext = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.textsplash);
        textView.setAnimation(animtext);
        animimage = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.imageplash);
        imageView.setAnimation(animimage);
        animimage.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_up,R.anim.anim_down);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


}
