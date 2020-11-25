package com.example.onlynews.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.onlynews.util.NewsArticle;

//TODO: Finish constructing general news article preview
@RequiresApi(api = Build.VERSION_CODES.M)
public class ArticleView extends View {

    private Paint mPaint;
    private NewsArticle mArticle;
    private Rect imageBounds;
    private TextPaint textPaint;
    private StaticLayout staticLayout;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public ArticleView(Context context, NewsArticle article) {
        super(context);
        this.mArticle = article;
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
        this.mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(50);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);

        this.textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setAntiAlias(true);

        StaticLayout.Builder builder = StaticLayout.Builder.obtain(mArticle.getTitle(), 0, mArticle.getTitle().length(), textPaint, 600)
                .setAlignment(Layout.Alignment.ALIGN_CENTER)
                .setLineSpacing(0, 1)
                .setIncludePad(false);
        staticLayout = builder.build();


        imageBounds = new Rect(10, 10, 1080, this.getMeasuredHeight() / 3);
    }

    public void setArticle(NewsArticle article) {
        this.mArticle = article;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.WHITE);
        canvas.drawRoundRect(0, 0, getWidth(), getHeight(), 20, 20, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawRoundRect(5, 5, getMeasuredWidth() - 5, ((3 * getMeasuredHeight()) / 4) - 5, 20, 20, this.mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawText(mArticle.getTitle(), 10, 600, textPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Fragment html = new htmlViewFragment();
        FragmentTransaction ft = ((Activity) getContext()).getSupportedFragmentManager().beginTransaction();
        ft.replace(((Activity) getContext()).getTaskId(), html);
        return super.onTouchEvent(event);
    }
}
