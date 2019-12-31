package cn.appoa.aframework.widget.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.appoa.aframework.R;


/**
 * @author yanjie
 * @version 1.0
 * @date 2019-07-01 20:08
 * @description 超级按钮
 */
public class SuperButton extends LinearLayout {
    /**
     * 形状
     */
    private static final int CIRCLE = 0x1;
    private static final int RECT = 0x2;

    /**
     * 从上到下
     */
    private static final int TOP_BOTTOM = 0x1;
    /**
     * 从右上到左下
     */
    private static final int TR_BL = 0x2;
    /**
     * 从右到左
     */
    private static final int RIGHT_LEFT = 0x3;
    /**
     * d从右下到左上
     */
    private static final int BR_TL = 0x4;
    /**
     * 从下到上
     */
    private static final int BOTTOM_TOP = 0x5;
    /**
     * 从左下到右上
     */
    private static final int BL_TR = 0x6;
    /**
     * 从左到右
     */
    private static final int LEFT_RIGHT = 0x7;
    /**
     * 从左上到右下
     */
    private static final int TL_BR = 0x8;
    /**
     * 文字内容
     */
    private CharSequence text = null;
    /**
     * 文字颜色
     */
    private int mTextColor;
    /**
     * 文字大小
     */
    private int mTextSize;
    /**
     * 文字是否单行显示，默认单行
     */
    private boolean mSingleLine = true;
    /**
     * 默认背景颜色
     */
    private int mColorNormal = ResourceId.VALUE_NULL;
    /**
     * 默认背景颜色
     */
    private int mColorPressed = ResourceId.VALUE_NULL;
    /**
     * 图片资源
     */
    private Drawable mDrawableLeft = null;
    private Drawable mDrawableRight = null;
    private Drawable mDrawableTop = null;
    private Drawable mDrawableBottom = null;
    private Drawable mDrawableMiddle = null;
    private boolean mDrawableAuto = true;
    /**
     * 图片中间时的宽高
     */
    private int mDrawableMiddleWidth = ResourceId.VALUE_NULL;
    private int mDrawableMiddleHeight = ResourceId.VALUE_NULL;
    /**
     * 图片距离文字距离
     */
    private int mDrawablePadding;
    /**
     * 形状
     */
    private int mShape = RECT;
    /**
     * 当背景是渐进色时，开始颜色
     */
    private int mColorStart = ResourceId.VALUE_NULL;
    /**
     * 当背景是渐进色时，结束颜色
     */
    private int mColorEnd = ResourceId.VALUE_NULL;
    /**
     * 颜色方向
     */
    private int mColorDirection = LEFT_RIGHT;
    /**
     * 所有角圆角半径
     */
    private int mCorner;
    /**
     * 四个角角度半径
     */
    private int mCornerLeftTop = ResourceId.VALUE_NULL;
    private int mCornerLeftBottom = ResourceId.VALUE_NULL;
    private int mCornerRightTop = ResourceId.VALUE_NULL;
    private int mCornerRightBottom = ResourceId.VALUE_NULL;
    /**
     * 边框颜色
     */
    private int mBorderColor;
    /**
     * 边框宽度
     */
    private int mBorderWidth;
    /**
     * 文字和图标容器
     */
    private TextView mTextIconContainer;
    /**
     * 按钮背景
     */
    private GradientDrawable mButtonBackground;
    /**
     * 按钮是否可以点击
     */
    private boolean mButtonClickable = true;
    /**
     * 阴影颜色
     */
    private int mShadowStartColor = ResourceId.VALUE_NULL;
    /**
     * 阴影颜色
     */
    private int mShadowEndColor = ResourceId.VALUE_NULL;
    /**
     * 阴影大小
     */
    private int mShadowSize = ResourceId.VALUE_NULL;


    public SuperButton(Context context) {
        this(context, null);
    }

    public SuperButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initButton(context, attrs);
    }


    /**
     * 初始化按钮
     */
    private void initButton(Context context, @Nullable AttributeSet attrs) {
        setClickable(true);
        setGravity(Gravity.CENTER);
        mTextIconContainer = new TextView(context);
        //解析属性
        parseAttrs(context, attrs);
        //按钮背景
        mButtonBackground = new GradientDrawable();
        //设置渐变色
        if (mColorStart != ResourceId.VALUE_NULL && mColorEnd != ResourceId.VALUE_NULL) {
            if (mColorDirection == TOP_BOTTOM) {
                mButtonBackground.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
            } else if (mColorDirection == TR_BL) {
                mButtonBackground.setOrientation(GradientDrawable.Orientation.TR_BL);
            } else if (mColorDirection == RIGHT_LEFT) {
                mButtonBackground.setOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
            } else if (mColorDirection == BR_TL) {
                mButtonBackground.setOrientation(GradientDrawable.Orientation.BR_TL);
            } else if (mColorDirection == BOTTOM_TOP) {
                mButtonBackground.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
            } else if (mColorDirection == BL_TR) {
                mButtonBackground.setOrientation(GradientDrawable.Orientation.BL_TR);
            } else if (mColorDirection == LEFT_RIGHT) {
                mButtonBackground.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
            } else if (mColorDirection == TL_BR) {
                mButtonBackground.setOrientation(GradientDrawable.Orientation.TL_BR);
            } else {
                mButtonBackground.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
            }
            mButtonBackground.setColors(new int[]{mColorStart, mColorEnd});
        } else {
            //设置填充颜色
            setButtonBackgroundColor(mColorNormal);
        }

        if (mShape == CIRCLE) {
            mButtonBackground.setShape(GradientDrawable.OVAL);
        } else {
            mButtonBackground.setShape(GradientDrawable.RECTANGLE);
        }
        //ordered top-left, top-right, bottom-right, bottom-left
        mButtonBackground.setCornerRadii(new float[]{
                mCornerLeftTop != ResourceId.VALUE_NULL ? mCornerLeftTop : mCorner, mCornerLeftTop != ResourceId.VALUE_NULL ? mCornerLeftTop : mCorner,
                mCornerRightTop != ResourceId.VALUE_NULL ? mCornerRightTop : mCorner, mCornerRightTop != ResourceId.VALUE_NULL ? mCornerRightTop : mCorner,
                mCornerRightBottom != ResourceId.VALUE_NULL ? mCornerRightBottom : mCorner, mCornerRightBottom != ResourceId.VALUE_NULL ? mCornerRightBottom : mCorner,
                mCornerLeftBottom != ResourceId.VALUE_NULL ? mCornerLeftBottom : mCorner, mCornerLeftBottom != ResourceId.VALUE_NULL ? mCornerLeftBottom : mCorner});

        //设置边框颜色和边框宽度
        mButtonBackground.setStroke(mBorderWidth, mBorderColor);
        //设置背景
        setBackground(mButtonBackground);

        //设置文字
        mTextIconContainer.setText(text);
        //设置文字大小
        mTextIconContainer.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        //设置文字颜色
        mTextIconContainer.setTextColor(mTextColor);
        mTextIconContainer.setCompoundDrawablePadding(mDrawablePadding);
        //是否单行
        if (mSingleLine) {
            mTextIconContainer.setSingleLine();
        }
        mTextIconContainer.setGravity(Gravity.CENTER);

        if (mDrawableAuto) {
            //设置图标
            int iconSize = (int) (mTextSize * 1.2f);
            setBounds(mDrawableLeft, iconSize);
            setBounds(mDrawableTop, iconSize);
            setBounds(mDrawableRight, iconSize);
            setBounds(mDrawableBottom, iconSize);
            //设置文字drawable
            mTextIconContainer.setCompoundDrawables(mDrawableLeft, mDrawableTop, mDrawableRight, mDrawableBottom);
        } else {
            mTextIconContainer.setCompoundDrawablesWithIntrinsicBounds(mDrawableLeft, mDrawableTop, mDrawableRight, mDrawableBottom);
        }

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (mDrawableMiddle != null) {
            ImageView imageView = new ImageView(getContext());
            if (mDrawableMiddleWidth == ResourceId.VALUE_NULL || mDrawableMiddleHeight == ResourceId.VALUE_NULL) {
                layoutParams.width = 40;
                layoutParams.height = 40;
            } else {
                layoutParams.width = mDrawableMiddleWidth;
                layoutParams.height = mDrawableMiddleHeight;
            }
            imageView.setImageDrawable(mDrawableMiddle);
            addView(imageView, layoutParams);
        } else {
            addView(mTextIconContainer, layoutParams);
        }

        //当设置的有阴影效果时
        if (mShadowSize != ResourceId.VALUE_NULL &&
                mShadowStartColor != ResourceId.VALUE_NULL && mShadowEndColor != ResourceId.VALUE_NULL) {
            RoundRectHelperUtil.help();
            RoundRectDrawableWithShadow shadowBackground = new RoundRectDrawableWithShadow(
                    ColorStateList.valueOf(mColorNormal), mCorner,
                    mShadowStartColor, mShadowEndColor,
                    mShadowSize, mShadowSize);
            setBackground(shadowBackground);
        }
    }


    /**
     * 在自动缩放模式下设置drawable边框
     */
    private void setBounds(Drawable drawable, int size) {
        if (drawable == null) {
            return;
        }
        drawable.setBounds(0, 0, size, size);
    }


    /**
     * 设置不可点击颜色，此时按钮点击无反应
     */
    @Deprecated
    public void setUnableColor(@ColorInt int color) {
        this.mColorNormal = color;
        setButtonBackgroundColor(color);
        setButtonClickable(false);
    }

    /**
     * 设置按钮是否可以点击
     */
    public void setButtonClickable(boolean buttonClickable) {
        this.mButtonClickable = buttonClickable;
    }

    /**
     * 设置按钮颜色以及是否可以点击
     * 不影响color_pressed的值
     *
     * @param color           设置按钮的color_normal值
     * @param buttonClickable 设置按钮是否可点击
     */
    public void setButtonClickable(@ColorInt int color, boolean buttonClickable) {
        this.mColorNormal = color;
        setButtonBackgroundColor(color);
        setButtonClickable(buttonClickable);
    }

    /**
     * 修改文字
     */
    public void setText(CharSequence text) {
        if (text == null) {
            return;
        }
        mTextIconContainer.setText(text);
    }

    /**
     * 修改文字颜色
     */
    public void setTextColor(@ColorInt int textColor) {
        mTextIconContainer.setTextColor(textColor);
    }

    /**
     * 修改按钮默认背景颜色
     */
    public void setColorNormal(@ColorInt int colorNormal) {
        this.mColorNormal = colorNormal;
        setButtonBackgroundColor(colorNormal);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            performClick();
        }
        if (!mButtonClickable) {
            return true;
        }
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                setButtonBackgroundColor(mColorPressed == ResourceId.VALUE_NULL ? mColorNormal : mColorPressed);
                break;
            case MotionEvent.ACTION_UP:
                setButtonBackgroundColor(mColorNormal);
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 设置正常状态下颜色
     */
    private void setButtonBackgroundColor(@ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mButtonBackground.setColor(ColorStateList.valueOf(color));
        } else {
            mButtonBackground.setColor(color);
        }
        setBackground(mButtonBackground);
    }

    /**
     * dp转换px
     */
    public int dp2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 属性解析
     */
    private void parseAttrs(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperButton);
        int length = typedArray.getIndexCount();
        for (int i = 0; i < length; i++) {
            int attr = typedArray.getIndex(i);
            //文字内容
            if (attr == R.styleable.SuperButton_text) {
                text = typedArray.getText(attr);
            }
            //文字颜色
            if (attr == R.styleable.SuperButton_textColor) {
                mTextColor = typedArray.getColor(attr, Color.TRANSPARENT);
            }
            //文字大小
            if (attr == R.styleable.SuperButton_textSize) {
                mTextSize = typedArray.getDimensionPixelSize(attr, ResourceId.VALUE_DEFAULT);
            }
            //默认背景颜色
            if (attr == R.styleable.SuperButton_color_normal) {
                mColorNormal = typedArray.getColor(attr, ResourceId.VALUE_NULL);
            }
            //按压状态颜色
            if (attr == R.styleable.SuperButton_color_pressed) {
                mColorPressed = typedArray.getColor(attr, ResourceId.VALUE_NULL);
            }
            //图片在文字左边
            if (attr == R.styleable.SuperButton_drawable_left) {
                mDrawableLeft = typedArray.getDrawable(attr);
            }
            //图片在文字右边
            if (attr == R.styleable.SuperButton_drawable_right) {
                mDrawableRight = typedArray.getDrawable(attr);
            }
            //图片在文字上边
            if (attr == R.styleable.SuperButton_drawable_top) {
                mDrawableTop = typedArray.getDrawable(attr);
            }
            //图片在文字下边
            if (attr == R.styleable.SuperButton_drawable_bottom) {
                mDrawableBottom = typedArray.getDrawable(attr);
            }
            //图片在中间
            if (attr == R.styleable.SuperButton_drawable_middle) {
                mDrawableMiddle = typedArray.getDrawable(attr);
            }
            //图片的宽度
            if (attr == R.styleable.SuperButton_drawable_middle_width) {
                mDrawableMiddleWidth = typedArray.getDimensionPixelSize(attr, ResourceId.VALUE_DEFAULT);
            }
            //图片的高度
            if (attr == R.styleable.SuperButton_drawable_middle_height) {
                mDrawableMiddleHeight = typedArray.getDimensionPixelSize(attr, ResourceId.VALUE_DEFAULT);
            }
            //自动适应文字的大小
            if (attr == R.styleable.SuperButton_drawable_auto) {
                mDrawableAuto = typedArray.getBoolean(attr, true);
            }
            //文字是否单行
            if (attr == R.styleable.SuperButton_singleLine) {
                mSingleLine = typedArray.getBoolean(attr, true);
            }
            //图片距离文字距离
            if (attr == R.styleable.SuperButton_drawable_padding) {
                mDrawablePadding = typedArray.getDimensionPixelSize(attr, ResourceId.VALUE_DEFAULT);
            }
            //形状
            if (attr == R.styleable.SuperButton_shape) {
                mShape = typedArray.getInt(attr, RECT);
            }
            //开始颜色
            if (attr == R.styleable.SuperButton_color_start) {
                mColorStart = typedArray.getColor(attr, Color.TRANSPARENT);
            }
            //结束颜色
            if (attr == R.styleable.SuperButton_color_end) {
                mColorEnd = typedArray.getColor(attr, Color.TRANSPARENT);
            }
            //颜色方向
            if (attr == R.styleable.SuperButton_color_direction) {
                mColorDirection = typedArray.getInt(attr, LEFT_RIGHT);
            }
            //所有角圆角半径
            if (attr == R.styleable.SuperButton_corner) {
                mCorner = typedArray.getDimensionPixelSize(attr, ResourceId.VALUE_DEFAULT);
            }
            //左上角圆角半径
            if (attr == R.styleable.SuperButton_corner_left_top) {
                mCornerLeftTop = typedArray.getDimensionPixelSize(attr, ResourceId.VALUE_NULL);
            }
            //右上角圆角半径
            if (attr == R.styleable.SuperButton_corner_right_top) {
                mCornerRightTop = typedArray.getDimensionPixelSize(attr, ResourceId.VALUE_NULL);
            }
            //左下角圆角半径
            if (attr == R.styleable.SuperButton_corner_left_bottom) {
                mCornerLeftBottom = typedArray.getDimensionPixelSize(attr, ResourceId.VALUE_NULL);
            }
            //右下角圆角半径
            if (attr == R.styleable.SuperButton_corner_right_bottom) {
                mCornerRightBottom = typedArray.getDimensionPixelSize(attr, ResourceId.VALUE_NULL);
            }
            //边框宽度
            if (attr == R.styleable.SuperButton_border_width) {
                mBorderWidth = typedArray.getDimensionPixelSize(attr, 0);
            }
            //边框颜色
            if (attr == R.styleable.SuperButton_border_color) {
                mBorderColor = typedArray.getColor(attr, Color.TRANSPARENT);
            }
            //按钮是否可以点击
            if (attr == R.styleable.SuperButton_button_click_able) {
                mButtonClickable = typedArray.getBoolean(attr, true);
            }
            //阴影开始颜色
            if (attr == R.styleable.SuperButton_color_shadow_start) {
                mShadowStartColor = typedArray.getColor(attr, ResourceId.VALUE_NULL);
            }
            //阴影结束颜色
            if (attr == R.styleable.SuperButton_color_shadow_end) {
                mShadowEndColor = typedArray.getColor(attr, ResourceId.VALUE_NULL);
            }
            //阴影大小
            if (attr == R.styleable.SuperButton_shadow_size) {
                mShadowSize = typedArray.getDimensionPixelSize(attr, ResourceId.VALUE_NULL);
            }

        }
        typedArray.recycle();
    }

}
