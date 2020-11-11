package com.example.onlynews.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

//TODO: Finish constructing general news article preview

public class ArticleView extends View {

    private Paint mPaint;

    public ArticleView(Context context) {
        super(context);
        init(null);
    }

    public ArticleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ArticleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public ArticleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {

    }

    @Override
    protected void onDraw(Canvas canvas) {

    }
}
