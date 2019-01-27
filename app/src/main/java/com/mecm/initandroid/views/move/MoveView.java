package com.mecm.initandroid.views.move;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.BounceInterpolator;

import com.mecm.initandroid.utils.SwmScreenUtils;
import com.mecm.initandroid.utils.SwmUIUtils;
import com.zhy.autolayout.AutoFrameLayout;

/**
 * 自由滑动的view
 * https://blog.csdn.net/xuan_xiaofeng/article/details/50463595
 */
public class MoveView extends AutoFrameLayout {
    private int x, y;

    public MoveView(Context context) {
        this(context, null);
    }

    public MoveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    private int screenWidth;
    private int screenHeight;

    public MoveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        screenWidth = SwmScreenUtils.INSTANCE.getScreenW();
        screenHeight = SwmScreenUtils.INSTANCE.getScreenH();
    }


    private float movex = 0;
    private float movey = 0;

    private int firstX;
    private int firstY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int) event.getX();
        y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                movex = (int) event.getRawX();
                movey = (int) event.getRawY();
                AnimatorSet setDown = new AnimatorSet();
                setDown.playTogether(
                        ObjectAnimator.ofFloat(this, "scaleX", 1f, 1.5f),
                        ObjectAnimator.ofFloat(this, "scaleY", 1f, 1.5f),
                        ObjectAnimator.ofFloat(this, "alpha", 1f, 0.5f)
                );
                setDown.start();
                break;
            case MotionEvent.ACTION_MOVE:
                //个人感觉跟手时，指尖在控件的中间比较好，所以减去宽高的一半
                setX(x + getLeft() + getTranslationX() - getWidth() / 2);
                setY(y + getTop() + getTranslationY() - getHeight() / 2);
                break;
            case MotionEvent.ACTION_UP:
                AnimatorSet setUp = new AnimatorSet();
                setUp.playTogether(
                        ObjectAnimator.ofFloat(this, "scaleX", 1.5f, 1f),
                        ObjectAnimator.ofFloat(this, "scaleY", 1.5f, 1f),
                        ObjectAnimator.ofFloat(this, "alpha", 0.5f, 1f)
                );
                setUp.start();

                // 超出屏幕右边
                if ((getX() + getWidth()) > (screenWidth - 20)) {
                    setX(firstX);
                    setY(firstY - SwmUIUtils.Companion.dp2px(150));
                    startInAnmin(2);
                }
                // 超出屏幕下边
                int minH = SwmUIUtils.Companion.dp2px(100);
                if (((int) event.getRawY()) > (screenHeight - minH)) {
                    setY(firstY);
                }
                //避免滑出触发点击事件
                if ((int) (event.getRawX() - movex) != 0
                        || (int) (event.getRawY() - movey) != 0) {
                    return true;
                }
                break;
        }
//        return true;
        return super.onTouchEvent(event);
    }

    /**
     * 执行平移动画 隐藏一部分
     * 2 向右边
     *
     * @param i
     */
    public void startInAnmin(int i) {
        int movedx = 0;
        if (i == 1) {
            movedx = -this.getWidth() / 3 * 2;
        } else if (i == 2) {
            movedx = this.getWidth() / 3 * 2;
        }
        ObjectAnimator oAnimatorX = ObjectAnimator.ofFloat(this, "translationX", movedx);
        oAnimatorX.setInterpolator(new BounceInterpolator());
        oAnimatorX.setDuration(500);
        oAnimatorX.start();
    }
}
