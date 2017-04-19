package fjnu.edu.cn.imageswitcherdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

/**
 * Created by gaofei on 2017/4/19.
 */

public class MySwitcherView extends ImageSwitcher {

    public MySwitcherView(Context context) {
        super(context);
    }

    public MySwitcherView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setImageResource(@DrawableRes int resid) {
        super.setImageResource(resid);
    }

    public void setImageBitmap(Bitmap bitmap){
        ImageView image = (ImageView)this.getNextView();
        image.setImageBitmap(bitmap);
        showNext();
    }
}
