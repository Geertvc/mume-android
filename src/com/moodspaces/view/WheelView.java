package com.moodspaces.view;

import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.moodspaces.model.MoodSelection;

public class WheelView extends ViewGroup {

    protected final static String LOG_TAG = "WheelView";

    private static final float FONT_SIZE_MODIFIER = 0.05f;
    private static final float PIN_RADIUS_MODIFIER = 0.04f;
    protected int[] screenLocation = new int[2];

    /* @formatter:off */
	private String[] moodLabels = {
		"surprise", 
		"fear", 
		"trust",
		"joy", 
		"anticipation", 
		"anger",
		"disgust", 
		"sadness" 
	};
	private int[] moodColors = {
		Color.rgb(0,   123, 51), 
		Color.rgb(0,   129, 171), 
		Color.rgb(31,  108, 173), 
		Color.rgb(123, 78,  163), 
		Color.rgb(220, 0,   71), 
		Color.rgb(232, 114, 0), 
		Color.rgb(237, 197, 0), 
		Color.rgb(123, 189, 13)
	};
	/* @formatter:on */
    protected RectF bounds = new RectF();
    protected Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    protected Paint labelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    protected MoodSelectionView pin;

    public WheelView(Context context) {
        super(context);
        initialize();
    }

    public WheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public WheelView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    protected void initialize() {
        // Nasty little detail
        setWillNotDraw(false);

        circlePaint.setStyle(Paint.Style.FILL);

        labelPaint.setColor(Color.WHITE);
        labelPaint.setTextAlign(Paint.Align.CENTER);

        pin = new MoodSelectionView(this.getContext(), new MoodSelection());
        addView(pin);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        // do nothing, the childs layout themselves
    }

    /**
     * Ensure the view is square MeasureSpecs are some kind of pointers to a
     * spec, you need to get the size using MeasureSpec.getSize(spec)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int diameter = Math.min(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));

        this.getLayoutParams().height = diameter;
        this.getLayoutParams().width = diameter;

        setMeasuredDimension(diameter, diameter);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);

        width -= getPaddingLeft() - getPaddingRight();
        height -= getPaddingTop() - getPaddingBottom();

        float diameter = Math.min(width, height);
        bounds = new RectF(0f, 0f, diameter, diameter);
        bounds.offset((width - bounds.width()) / 2, (height - bounds.height()) / 2);

        labelPaint.setTextSize(getFontSize());

        getLocationOnScreen(screenLocation);

        pin.reLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawCircle(canvas);
        drawLabels(canvas);

        pin.draw(canvas);
    }

    private void drawCircle(Canvas canvas) {
        circlePaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < moodColors.length; i++) {
            circlePaint.setColor(moodColors[i]);
            canvas.drawArc(bounds, 360f / moodColors.length * i, 360f / moodColors.length, true, circlePaint);
        }
    }

    private void drawLabels(Canvas canvas) {
        final float unitRotation = 360 / moodLabels.length;
        final float pivotX = bounds.centerX();
        final float pivotY = bounds.centerY();

        canvas.save();
        canvas.rotate(3f / 2 * unitRotation, pivotX, pivotY);
        for (int i = 0; i < moodLabels.length / 2; i++) {
            canvas.drawText(moodLabels[i], pivotX + 0.3f * bounds.width(), pivotY + getFontSize() / 2, labelPaint);
            canvas.drawText(moodLabels[i + moodLabels.length / 2], pivotX - 0.3f * bounds.width(), pivotY + 0.35f
                    * getFontSize(), labelPaint);
            canvas.rotate(-unitRotation, pivotX, pivotY);
        }
        canvas.restore();
    }

    public Set<MoodSelection> getSelections() {
        HashSet<MoodSelection> result = new HashSet<MoodSelection>();
        result.add(pin.getSelection());
        return result;

    }

    protected float getFontSize() {
        return FONT_SIZE_MODIFIER * bounds.width();
    }

    protected int getPinRadius() {
        return (int) (PIN_RADIUS_MODIFIER * bounds.width());
    }

    protected int getRelativeX(MotionEvent e) {
        return (int) (e.getRawX() - screenLocation[0]);
    }

    protected int getRelativeY(MotionEvent e) {
        return (int) (e.getRawY() - screenLocation[1]);
    }

    public class MoodSelectionView extends View implements OnTouchListener {

        protected final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        protected final MoodSelection selection;

        public MoodSelectionView(Context context, MoodSelection selection) {
            super(context);
            this.selection = selection;
            initialize();
        }

        protected void initialize() {
            setWillNotDraw(false);
            setOnTouchListener(this);
        }

        protected int getLeft(int x) {
            return x - getPinRadius();
        }

        protected int getTop(int y) {
            return y - getPinRadius();
        }

        protected int getBottom(int y) {
            return y + getPinRadius();
        }

        protected int getRight(int x) {
            return x + getPinRadius();
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            setMeasuredDimension(2 * getPinRadius(), 2 * getPinRadius());
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            final float cx = getLeft() + getWidth() / 2;
            final float cy = getTop() + getHeight() / 2;

            // Fill the shape with gray
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.DKGRAY);
            canvas.drawCircle(cx, cy, getPinRadius(), paint);

            // Stroke the shape with black
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(0.4f * getPinRadius());
            paint.setColor(Color.BLACK);
            canvas.drawCircle(cx, cy, getPinRadius(), paint);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            getParent().requestDisallowInterceptTouchEvent(true);

            final int x = getRelativeX(event) - (int) bounds.centerX();
            final int y = getRelativeY(event) - (int) bounds.centerY();

            selection.setR(calculateR(x, y));
            selection.setTheta(calculateTheta(x, y));

            reLayout();

            return true;
        }

        protected double calculateR(int x, int y) {
            return Math.sqrt(y * y + x * x) / (bounds.width() / 2);
        }

        protected double calculateTheta(int x, int y) {
            return Math.atan2(y, x);
        }

        protected int getX(MoodSelection selection) {
            return (int) (bounds.centerX() + (selection.getR() * bounds.width() / 2 * Math.cos(selection.getTheta())));
        }

        protected int getY(MoodSelection selection) {
            return (int) (bounds.centerY() + (selection.getR() * bounds.width() / 2 * Math.sin(selection.getTheta())));
        }

        protected void reLayout() {
            this.layout(getLeft(getX(selection)), getTop(getY(selection)), getRight(getX(selection)),
                    getBottom(getY(selection)));
        }

        public MoodSelection getSelection() {
            return selection;
        }
    }
}