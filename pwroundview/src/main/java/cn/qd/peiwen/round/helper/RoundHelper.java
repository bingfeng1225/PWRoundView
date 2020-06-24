package cn.qd.peiwen.round.helper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;

import java.lang.ref.WeakReference;

import cn.qd.peiwen.round.R;

/**
 * Created by nick network_setting_on 2017/8/5.
 */

public class RoundHelper {
    private Context context;
    private int corner_TL;
    private int corner_TR;
    private int corner_BL;
    private int corner_BR;

    private int strokeWidth;
    private int strokeColor;
    private int strokePressColor;
    private int backgroundColor;
    private int backgroundPressColor;
    private boolean isRadiusHalfHeight;

    private boolean propertyChanged = true;

    private WeakReference<IRoundHelper> helper;

    public RoundHelper(Context context,IRoundHelper helper) {
        this.context = context;
        this.helper = new WeakReference<>(helper);
    }

    public void initRoundView(AttributeSet attrs, int defStyle) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundHelper, defStyle, 0);
        try {
            this.corner_BL = ta.getDimensionPixelSize(R.styleable.RoundButton_rv_corner_bl, 0);
            this.corner_BR = ta.getDimensionPixelSize(R.styleable.RoundHelper_rv_corner_br, 0);
            this.corner_TL = ta.getDimensionPixelSize(R.styleable.RoundHelper_rv_corner_tl, 0);
            this.corner_TR = ta.getDimensionPixelSize(R.styleable.RoundHelper_rv_corner_tr, 0);
            this.strokeWidth = ta.getDimensionPixelSize(R.styleable.RoundHelper_rv_stroke_width, 0);
            backgroundColor = ta.getColor(R.styleable.RoundHelper_rv_bg_color, Color.TRANSPARENT);
            backgroundPressColor = ta.getColor(R.styleable.RoundImageView_rv_bg_press_color, Integer.MAX_VALUE);
            strokeColor = ta.getColor(R.styleable.RoundHelper_rv_stroke_color, Color.TRANSPARENT);
            strokePressColor = ta.getColor(R.styleable.RoundHelper_rv_stroke_press_color, Integer.MAX_VALUE);
            isRadiusHalfHeight = ta.getBoolean(R.styleable.RoundHelper_rv_half_corner_height, false);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ta.recycle();
        }
    }

    protected void generateDrawable(GradientDrawable gd, int color, int strokeColor) {
        gd.setColor(color);
        float[] radiusArr = new float[8];
        if (corner_TL > 0 || corner_TR > 0 || corner_BR > 0 || corner_BL > 0) {
            /**The corners are ordered top-left, top-right, bottom-right, bottom-left*/
            radiusArr[0] = corner_TL;
            radiusArr[1] = corner_TL;
            radiusArr[2] = corner_TR;
            radiusArr[3] = corner_TR;
            radiusArr[4] = corner_BR;
            radiusArr[5] = corner_BR;
            radiusArr[6] = corner_BL;
            radiusArr[7] = corner_BL;
            gd.setCornerRadii(radiusArr);
        }
        gd.setStroke(strokeWidth, strokeColor);
    }

    public StateListDrawable generateBackgroundSelector() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        GradientDrawable gd_background = new GradientDrawable();
        GradientDrawable gd_background_press = new GradientDrawable();
        generateDrawable(gd_background, backgroundColor, strokeColor);
        stateListDrawable.addState(new int[]{-android.R.attr.state_pressed}, gd_background);
        if (backgroundPressColor != Integer.MAX_VALUE || strokePressColor != Integer.MAX_VALUE) {
            generateDrawable(gd_background_press, backgroundPressColor == Integer.MAX_VALUE ? backgroundColor : backgroundPressColor,
                    strokePressColor == Integer.MAX_VALUE ? strokeColor : strokePressColor);
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, gd_background_press);
        }
        return stateListDrawable;
    }

    public int getCorner_TL() {
        return corner_TL;
    }

    public void setCorner_TL(int corner_TL) {
        this.propertyChanged = true;
        this.corner_TL = corner_TL;
    }

    public int getCorner_TR() {
        return corner_TR;
    }

    public void setCorner_TR(int corner_TR) {
        this.propertyChanged = true;
        this.corner_TR = corner_TR;
    }

    public int getCorner_BL() {
        return corner_BL;
    }

    public void setCorner_BL(int corner_BL) {
        this.propertyChanged = true;
        this.corner_BL = corner_BL;
    }

    public int getCorner_BR() {
        return corner_BR;
    }

    public void setCorner_BR(int corner_BR) {
        this.propertyChanged = true;
        this.corner_BR = corner_BR;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.propertyChanged = true;
        this.strokeWidth = strokeWidth;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(int strokeColor) {
        this.propertyChanged = true;
        this.strokeColor = strokeColor;
    }

    public int getStrokePressColor() {
        return strokePressColor;
    }

    public void setStrokePressColor(int strokePressColor) {
        this.propertyChanged = true;
        this.strokePressColor = strokePressColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.propertyChanged = true;
        this.backgroundColor = backgroundColor;
    }

    public int getBackgroundPressColor() {
        return backgroundPressColor;
    }

    public void setBackgroundPressColor(int backgroundPressColor) {
        this.propertyChanged = true;
        this.backgroundPressColor = backgroundPressColor;
    }

    public boolean isPropertyChanged() {
        return propertyChanged;
    }

    public void setPropertyChanged(boolean propertyChanged) {
        this.propertyChanged = propertyChanged;
    }

    public void layoutView(int height) {
        if (isRadiusHalfHeight) {
            int caonerRadius = height / 2;
            corner_BL = caonerRadius;
            corner_BR = caonerRadius;
            corner_TL = caonerRadius;
            corner_TR = caonerRadius;
        }
    }
}
