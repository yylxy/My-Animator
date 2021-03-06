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

import me.nereo.multi_image_selector.MultiImageSelector;

public class MainActivityPpp extends AppCompatActivity implements View.OnClickListener {

    public static void startUI(Context context) {
        Intent intent = new Intent(context, MainActivityPpp.class);
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

    public void onClickListener(View view) {

//        MultiImageSelector.create(Context)
//                .showCamera(boolean) // 是否显示相机. 默认为显示
//        .count(int) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
//        .single() // 单选模式
//                .multi() // 多选模式, 默认模式;
//                .origin(ArrayList < String >) // 默认已选择图片. 只有在选择模式为多选时有效
//                .start(Activity / Fragment, REQUEST_IMAGE);

        MultiImageSelector.create().showCamera(true).count(9).multi().start(this, 500);


    }


}



