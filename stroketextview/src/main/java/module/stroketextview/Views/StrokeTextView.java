package module.stroketextview.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;


import java.lang.reflect.Field;

import module.stroketextview.R;

public class StrokeTextView extends TextView {
    //画笔
    TextPaint mTextPaint;
    //描边色
    private int mStrokeColor;
    //内部色
    private int mInnerColor;
    //描边宽度 默认为5
    private int mStrokeWidth = DEFAULT_STOKE_WIDTH;
    //默认描边宽度
    private static final int DEFAULT_STOKE_WIDTH = 5;
    //默认描边色
    private static final int DEFAULT_STROKE_COLOR = Color.RED;
    //默认内部色
    private static final int DEFAULT_INNER_COLOR = Color.WHITE;
    //默认是否是用描边
    private boolean mDrawSideLine = true;
    //记录字的二维数组
    int[][] position;
    //字体高度
    int mTextHeight;

    public StrokeTextView(Context context, int stokeColor, int innerColor, int strokeWidth) {
        super(context);
        mTextPaint = getPaint();
        mStrokeColor = stokeColor;
        mInnerColor = innerColor;
        mStrokeWidth = strokeWidth;
    }

    public StrokeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTextPaint = getPaint();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.StrokeTextView);
        mStrokeColor = array.getColor(R.styleable.StrokeTextView_strokeColor, DEFAULT_STROKE_COLOR);
        mInnerColor = array.getColor(R.styleable.StrokeTextView_innerColor, DEFAULT_INNER_COLOR);
        mStrokeWidth = array.getInt(R.styleable.StrokeTextView_strokeWidth, DEFAULT_STOKE_WIDTH);
        array.recycle();
    }

    public void setDrawSideLine(boolean isDraw) {
        mDrawSideLine = isDraw;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mDrawSideLine) {
            //外层绘制
            setTextColorUseReflection(mStrokeColor);
//            Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
//            mTextHeight = (int) (Math.ceil(fontMetrics.descent - fontMetrics.top) + 2);
            //描边样式
            mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            //描边宽度
            mTextPaint.setStrokeWidth(mStrokeWidth);
            //外层text采用粗体
            mTextPaint.setFakeBoldText(true);
            super.onDraw(canvas);
            //内层 恢复画笔
            setTextColorUseReflection(mInnerColor);
            mTextPaint.setStrokeWidth(0);
            mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mTextPaint.setFakeBoldText(false);
        }
        super.onDraw(canvas);
    }

    /**
     * 通过反射设置字体颜色
     *
     * @param color 颜色
     */
    private void setTextColorUseReflection(int color) {
        try {
            Field mCurTextColor = TextView.class.getDeclaredField("mCurTextColor");
            mCurTextColor.setAccessible(true);
            mCurTextColor.set(this, color);
            mCurTextColor.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
