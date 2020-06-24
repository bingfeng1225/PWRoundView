package cn.qd.peiwen.round;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;

import cn.qd.peiwen.round.helper.IRoundHelper;
import cn.qd.peiwen.round.helper.RoundHelper;


/**
 * Created by nick network_setting_on 2017/8/29.
 */

public class RoundButton extends AppCompatButton implements IRoundHelper {
    private RoundHelper helper;

    public RoundButton(Context context) {
        this(context, null);
    }

    public RoundButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.initView(context, attrs, defStyle);
    }

    public RoundHelper getHelper() {
        return helper;
    }

    private void initView(Context context, AttributeSet attrs, int defStyle) {
        this.helper = new RoundHelper(context,this);
        this.helper.initRoundView(attrs, defStyle);
    }

    @Override
    public View findRoundView() {
        return this;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        helper.layoutView(getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(helper.isPropertyChanged()) {
            setBackground(helper.generateBackgroundSelector());
        }
    }
}
