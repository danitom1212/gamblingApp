package com.example.gamblingapp.imageViewScrolling;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gamblingapp.R;

public class imageViewScrolling extends FrameLayout {

    private static  int ANIMATION_DUR=550;
    ImageView current_image,next_image;
    int last_result=0,old_value=0;
    IEventEnd eventEnd;

    public void setEventEnd(IEventEnd eventEnd) {
        this.eventEnd = eventEnd;
    }

    public imageViewScrolling(Context context) {
        super(context);
        init(context);
    }

    public imageViewScrolling( Context context,AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.image_view_scrolling,this);
        current_image=(ImageView) getRootView().findViewById(R.id.current_image);
        next_image=(ImageView) getRootView().findViewById(R.id.next_image);
    next_image.setTranslationY(getHeight());
    }
    public void setValueRendom(int image,int rotate_count)
    {
        current_image.animate().translationY(-getHeight()).setDuration(ANIMATION_DUR).start();
        next_image.setTranslationY(next_image.getHeight());
next_image.animate().translationY(0).setDuration(ANIMATION_DUR).setListener(new Animator.AnimatorListener() {
    @Override
    public void onAnimationStart(Animator animator) {

    }

    @Override
    public void onAnimationEnd(Animator animator) {
setImage(current_image,old_value%6);// because we have 6 images , we will mod for 6
current_image.setTranslationY(0);
if (old_value!=rotate_count){
    //if old_value still not equal rotate count ,we will still roll
    setValueRendom(image,rotate_count);
    old_value++;

}
else {//if rotate is reaced to the
    last_result=0;
    old_value=0;
    setImage(next_image,image);
    eventEnd.eventEnd(image%6,rotate_count);
}
    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }
});
    }

    private void setImage(ImageView image_view, int value) {
        if (value==util.BAR)
            image_view.setImageResource(R.drawable.bar_done);
        else if (value==util.ORANGE)
            image_view.setImageResource(R.drawable.orange_done);
        else if (value==util.TRIPLE)
            image_view.setImageResource(R.drawable.triple_done);
        else if (value==util.WATERMALON)
            image_view.setImageResource(R.drawable.waternelon_done);
        else if (value==util.LEMON)
            image_view.setImageResource(R.drawable.lemon_done);
        else
            image_view.setImageResource(R.drawable.sevent_done);
//set tag for image for use to compare res
        image_view.setTag(value);
        last_result=value;
    }
    public int getValue(){
        return Integer.parseInt(next_image.getTag().toString());
    }
}
