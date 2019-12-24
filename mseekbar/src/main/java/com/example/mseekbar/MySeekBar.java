package com.example.mseekbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MySeekBar extends View {

    private static int LINE = 0;
    private static int CIRCLE = 1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    Paint paint;    //画笔
    Shader gradient;  //渐变设置
    int progress;   //进度
    int max = 100;   //最大值
    int mode = LINE;   //进度条模式(圆形或直线)
    int circleStart = 0;   //进度条起始位置：从圆环最右侧的点开始，顺时针旋转的角度
    int circleEnd = 0;   //进度条结束位置：从圆环最右侧的点开始，顺时针旋转的角度
    float circleWidth = 20f;   //圆形进度条宽度
    int gradientOrientation = 0;   //进度条渐变方向
    int gradientsStartColor = Color.GRAY;   //进度条渐变开始颜色
    int gradientEndColor = Color.GRAY;   //进度条渐变结束
    int background = Color.GRAY;   //进度条渐变结束
    int progressColor = Color.RED;   //进度颜色
    boolean isGradient = false;   //底色渐变开关

    public MySeekBar(Context context) {
        super(context);
        init();
    }

    public MySeekBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MySeekBar);
        setMax(ta.getInt(R.styleable.MySeekBar_msb_max, 100));
        setProgress(ta.getInt(R.styleable.MySeekBar_msb_progress, 0));
        setMode(ta.getInt(R.styleable.MySeekBar_msb_mode, LINE));
        setCircleStart(ta.getInt(R.styleable.MySeekBar_msb_circle_start, 0));
        setCircleEnd(ta.getInt(R.styleable.MySeekBar_msb_circle_end, 360));
        setCircleWidth(ta.getDimension(R.styleable.MySeekBar_msb_circle_width, 20));
        setGradientOrientation(ta.getInt(R.styleable.MySeekBar_msb_gradient_orientation, HORIZONTAL));
        setGradientsStartColor(ta.getColor(R.styleable.MySeekBar_msb_gradient_start_color, Color.GRAY));
        setGradientEndColor(ta.getInt(R.styleable.MySeekBar_msb_gradient_end_color, Color.GRAY));
        setProgressColor(ta.getInt(R.styleable.MySeekBar_msb_progress_color, Color.RED));
        setBackground(ta.getInt(R.styleable.MySeekBar_msb_background, Color.GRAY));
        setIsGradient(ta.getBoolean(R.styleable.MySeekBar_msb_gradient, false));
        ta.recycle();
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStrokeCap(Paint.Cap.ROUND);

        paint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStrokeWidth(getHeight() - getPaddingTop() - getPaddingBottom());
        if (mode == CIRCLE) {
            drawCircleSeekBar(canvas);
        } else {
            drawLineSeekBar(canvas);
        }
    }

    private void drawCircleSeekBar(Canvas canvas) {
        int diameter = Math.min(getWidth() - getPaddingRight() - getPaddingLeft(), getHeight() - getPaddingTop() - getPaddingBottom());
        if (isGradient) {
            if (gradientOrientation == HORIZONTAL) {
                gradient = new SweepGradient((getWidth() - getPaddingLeft() - getPaddingRight()) / 2,
                        (getHeight() - getPaddingTop() - getPaddingBottom()) / 2,
                        new int[]{gradientsStartColor, gradientEndColor, gradientsStartColor},
                        new float[]{0, (circleEnd - circleStart) / 360f, 1});
                Matrix matrix = new Matrix();
                matrix.setRotate(circleStart);
                gradient.setLocalMatrix(matrix);
            } else {
                gradient = new RadialGradient((getWidth() - getPaddingLeft() - getPaddingRight()) / 2,
                        (getHeight() - getPaddingTop() - getPaddingBottom()) / 2,
                        diameter / 2,
                        new int[]{Color.WHITE, Color.WHITE, gradientsStartColor, gradientEndColor},
                        new float[]{0, 1 - circleWidth * 2 / diameter, 1 - circleWidth * 2 / diameter, 1},
                        Shader.TileMode.CLAMP);
            }
            paint.setShader(gradient);
        } else {
            paint.setColor(background);
        }
        paint.setStyle(Paint.Style.STROKE);//设置画圆弧的画笔的属性为描边
        paint.setStrokeWidth(circleWidth);

        RectF oval = new RectF(getWidth() / 2 - diameter / 2 + circleWidth / 2, getHeight() / 2 - diameter / 2 + circleWidth / 2,
                getWidth() / 2 + diameter / 2 - circleWidth / 2, getHeight() / 2 + diameter / 2 - circleWidth / 2);
        canvas.drawArc(oval, circleStart, circleEnd - circleStart, false, paint);
        if (progress > 0) {
            paint.setColor(progressColor);
            paint.setShader(null);
            int sweepAngle = (circleEnd - circleStart) * progress / max;
            canvas.drawArc(oval, circleStart, sweepAngle, false, paint);
        }
    }

    private void drawLineSeekBar(Canvas canvas) {
        if (isGradient) {
            if (gradientOrientation == HORIZONTAL) {
                gradient = new LinearGradient(getPaddingLeft(),
                        0,
                        getWidth() - getPaddingRight(),
                        0,
                        new int[]{gradientsStartColor, gradientEndColor},
                        null,
                        Shader.TileMode.CLAMP);
            } else {
                gradient = new LinearGradient(0,
                        getPaddingTop(),
                        0,
                        getHeight() - getPaddingBottom(),
                        new int[]{gradientsStartColor, gradientEndColor},
                        null,
                        Shader.TileMode.CLAMP);
            }
            paint.setShader(gradient);
        } else {
            paint.setColor(background);
        }
        canvas.drawLine(getHeight() / 2 + 1 + getPaddingLeft(),
                (getHeight() - getPaddingTop() - getPaddingBottom()) / 2 + getPaddingTop(),
                getWidth() - getHeight() / 2 - getPaddingRight(),
                (getHeight() - getPaddingTop() - getPaddingBottom()) / 2 + getPaddingTop(),
                paint);
        if (progress > 0) {
            paint.setColor(progressColor);
            paint.setShader(null);
            int progressWidth = (getWidth() - getHeight() - getPaddingLeft() - getPaddingRight()) * progress / max;
            canvas.drawLine(getHeight() / 2 + 1 + getPaddingLeft(),
                    (getHeight() - getPaddingTop() - getPaddingBottom()) / 2 + getPaddingTop(),
                    getHeight() / 2 + 1 + getPaddingLeft() + progressWidth,
                    (getHeight() - getPaddingTop() - getPaddingBottom()) / 2 + getPaddingTop(),
                    paint);
        }
    }

    /**
     * 设置进度
     *
     * @param progress 进度百分比
     */
    public void setProgress(int progress) {
        if (progress < 0) {
            this.progress = 0;
        } else if (progress > max) {
            this.progress = max;
        } else {
            this.progress = progress;
        }
        invalidate();
    }

    /**
     * 总进度
     *
     * @param max 总进度
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * 设置进度条模式
     *
     * @param mode 0：线型  1：圆形
     */
    private void setMode(int mode) {
        this.mode = mode;
    }

    /**
     * 设置圆形进度条开始角度
     *
     * @param circleStart
     */
    private void setCircleStart(int circleStart) {
        this.circleStart = circleStart;
    }

    /**
     * 设置圆形进度条结束角度
     *
     * @param circleEnd
     */
    private void setCircleEnd(int circleEnd) {
        this.circleEnd = circleEnd;
    }

    /**
     *
     * @param circleWidth
     */
    private void setCircleWidth(float circleWidth) {
        this.circleWidth = circleWidth;
    }

    private void setGradientOrientation(int gradientOrientation) {
        this.gradientOrientation = gradientOrientation;
    }

    private void setGradientEndColor(int gradientEndColor) {
        this.gradientEndColor = gradientEndColor;
    }

    private void setGradientsStartColor(int gradientsStartColor) {
        this.gradientsStartColor = gradientsStartColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public void setIsGradient(boolean isGradient) {
        this.isGradient = isGradient;
    }

    public void setBackground(int background) {
        this.background = background;
    }
}
