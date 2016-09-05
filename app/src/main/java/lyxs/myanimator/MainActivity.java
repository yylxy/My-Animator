package lyxs.myanimator;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static void startUI(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    boolean flag = true;
    int[] ids = new int[]{R.id.im_image_6, R.id.im_image_1, R.id.im_image_2, R.id.im_image_3, R.id.im_image_4, R.id.im_image_5};
    ArrayList<ImageView> views = new ArrayList<>();

    private void initView() {


        for (int i = 0; i < ids.length; i++) {
            ImageView im = (ImageView) findViewById(ids[i]);
            im.setOnClickListener(this);
            views.add(im);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_image_6:
                if (flag) {
                    startAnimator();
                } else {
                    cloneAnimator();
                }
                break;
            default:

                Toast.makeText(this, "" + v.getId(), Toast.LENGTH_SHORT).show();
        }
    }


    private void startAnimator() {
        for (int i = 1; i < ids.length; i++) {
            ObjectAnimator animaitor = ObjectAnimator.ofFloat(views.get(i), "translationX", 0f, i * 160);
            animaitor.setDuration(400);
            animaitor.setStartDelay(i * 100);//延时
            animaitor.setInterpolator(new OvershootInterpolator());
            animaitor.start();
        }
        flag = false;
    }

    private void cloneAnimator() {
        for (int i = 1; i < ids.length; i++) {
            ObjectAnimator animaitor = ObjectAnimator.ofFloat(views.get(i), "translationX", i * 160, 0f);
            animaitor.setDuration(400);
            animaitor.setStartDelay(i * 100);//延时
            animaitor.setInterpolator(new BounceInterpolator());
            animaitor.start();
        }
        flag = true;
    }


}



