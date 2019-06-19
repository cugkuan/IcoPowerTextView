package com.cugkuan.powertextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * 在未来准备支持 Scale Types 属性；
 * 现在暂时不想做
 */
public class IcoPowerTextView extends AppCompatTextView {

    public IcoPowerTextView(Context context) {
        this(context, null);
    }

    public IcoPowerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        Drawable[] drawable = getCompoundDrawables();

        //分别是 ： left;top;right;bottom
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.IcoPowerTextView);
        Drawable drawableL = drawable[0];

        if (drawableL != null) {
            //left
            int leftWidth = array.getDimensionPixelSize(R.styleable.IcoPowerTextView_ico_power_left_width, -1);
            int leftHeight = array.getDimensionPixelSize(R.styleable.IcoPowerTextView_ico_power_left_height, -1);
            IcoDrawable ico = new IcoDrawable(drawableL,leftHeight,leftWidth);
            drawable[0] = ico;
        }
        //top
        Drawable drawableT = drawable[1];
        if (drawableT != null) {
            int topWidth = array.getDimensionPixelSize(R.styleable.IcoPowerTextView_ico_power_top_width, -1);
            int topHeight = array.getDimensionPixelSize(R.styleable.IcoPowerTextView_ico_power_top_height, -1);
            IcoDrawable ico = new IcoDrawable(drawableT,topHeight,topWidth);
            drawable[1] = ico;
        }
        Drawable drawableR = drawable[2];
        //right
        if (drawableR != null) {
            int rightWidth = array.getDimensionPixelSize(R.styleable.IcoPowerTextView_ico_power_right_width, -1);
            int rightHeight = array.getDimensionPixelSize(R.styleable.IcoPowerTextView_ico_power_right_height, -1);
            IcoDrawable ico  = new IcoDrawable(drawableR,rightHeight,rightWidth);
            drawable[2] = ico;
        }
        Drawable drawableB = drawable[3];

        if (drawableB != null) {
            //bottom
            int bottomWidth = array.getDimensionPixelSize(R.styleable.IcoPowerTextView_ico_power_bottom_width, -1);
            int bottomHeight = array.getDimensionPixelSize(R.styleable.IcoPowerTextView_ico_power_bottom_height, -1);
            IcoDrawable ico = new IcoDrawable(drawableB,bottomHeight,bottomWidth);
            drawable[3] = ico;
        }
        array.recycle();
        setCompoundDrawables(drawable[0],drawable[1],drawable[2],drawable[3]);
    }

    static class  IcoDrawable extends Drawable{

        private int mWidth;
        private int mHeight;
        private Drawable mDrawable;

        public IcoDrawable(Drawable drawable,int height,int width){
            if (height < 0){
                this.mHeight = drawable.getIntrinsicHeight();
            }else {
                mHeight = width;
            }
            if (width < 0) {
                this.mWidth = drawable.getIntrinsicWidth();
            }else {
                mWidth = width;
            }
            mDrawable = drawable;
            int w = mDrawable.getIntrinsicWidth() > mWidth ? mWidth : mDrawable.getIntrinsicWidth();
            int h = mDrawable.getIntrinsicHeight() > mHeight ? mHeight : mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(0,0,w,h);
            setBounds(0,0,mWidth,mHeight);
        }

        @Override
        public int getIntrinsicWidth() {
            return mWidth;
        }
        @Override
        public int getIntrinsicHeight() {
            return mHeight;
        }

        @Override
        public void draw(@NonNull Canvas canvas) {
            int dx = (mWidth - mDrawable.getIntrinsicWidth())/2;
            if (dx < 0){
                dx = 0;
            }
            int dy = (mHeight - mDrawable.getIntrinsicHeight())/2;
            if (dy < 0){
                dy = 0;
            }
            canvas.save();
            canvas.translate(dx,dy);
            mDrawable.draw(canvas);
            canvas.restore();
        }

        @Override
        public void setAlpha(int alpha) {
            mDrawable.setAlpha(alpha);
        }

        @Override
        public void setColorFilter(@Nullable ColorFilter colorFilter) {
            mDrawable.setColorFilter(colorFilter);
        }

        @Override
        public int getOpacity() {
            return mDrawable.getOpacity();
        }
    }

}
