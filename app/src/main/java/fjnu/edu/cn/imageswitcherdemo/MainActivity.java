package fjnu.edu.cn.imageswitcherdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final  String TAG = "MainActivity";
    private static final int[] imgResouces = {R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5};
    private int mCurrIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        final MySwitcherView imageSwitcher = (MySwitcherView) findViewById(R.id.switcher_img);
        //imageSwitcher.setImageResource(R.drawable.s1);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                final ImageView imageView = new ImageView(MainActivity.this);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return imageView;
            }
        });

        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick->next view:" +  imageSwitcher.getNextView().getClass().getName());
                new AsyncTask<Void, Void, Bitmap>(){
                    @Override
                    protected Bitmap doInBackground(Void... params) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = false;
                        options.inSampleSize = 4;
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgResouces[new Random().nextInt(5)], options);
                        return bitmap;
                    }

                    @Override
                    protected void onPostExecute(Bitmap bitmap) {
                        imageSwitcher.setImageBitmap(bitmap);
                    }
                }.execute();

            }
        });

        findViewById(R.id.btn_pre).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                imageSwitcher.setImageResource(imgResouces[(--mCurrIndex + imgResouces.length) % imgResouces.length]);
            }
        });
    }
}
