package gesin.ceg4110_hw1;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class DynamicallySwipeablePager extends ViewPager {
    private boolean swipeable;
    public DynamicallySwipeablePager(Context context, AttributeSet attrs) {
        super(context, attrs);
        swipeable = true;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return swipeable?super.onTouchEvent(event):false;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event)
    {
        return swipeable?super.onInterceptTouchEvent(event):false;
    }
    public void setSwipeable(boolean swipeable)
    {
        this.swipeable = swipeable;
    }
}
