package cn.qd.peiwen.round;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import cn.qd.peiwen.round.helper.IRoundHelper;
import cn.qd.peiwen.round.helper.RoundHelper;

/**
 * Created by nick network_setting_on 2017/8/8.
 */

public class RoundRelativeLayout extends RelativeLayout implements IRoundHelper {
    private RoundHelper helper;

    public RoundRelativeLayout(Context context) {
        this(context, null);
    }

    public RoundRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
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
        if(helper.isPropertyChanged()) {
            helper.setPropertyChanged(false);
            setBackground(helper.generateBackgroundSelector());
        }
    }
}
