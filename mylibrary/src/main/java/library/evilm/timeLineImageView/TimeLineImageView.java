package library.evilm.timeLineImageView;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * ProjectName:TimeLineImageView
 * Description:
 * Created by evilM on 2016/10/21.15:40
 * Modify by:
 * Modify time:
 * Modify remark:
 */

public class TimeLineImageView extends ImageView {
    /**
     * 线方向
     * VERTICAL:垂直
     * HORIZONTAL:水平
     */
    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;

    /**
     * 线类型
     * STRAIGHT:直线
     * DASH:虚线
     */
    public static final int STRAIGHT = 0;
    public static final int DASH = 1;

    private int mLineOrientation = VERTICAL;
    private int mLineColor = Color.parseColor("#E5E5E5");
    private float mLineSize = Resources.getSystem().getDisplayMetrics().density * .5f + .5f;
    private boolean mLineStart = true;
    private boolean mLineEnd = true;

    private Paint mLinePaint;
    private Path mLinePath;

    public TimeLineImageView(Context context) {
        super(context);
        init(null);
    }

    public TimeLineImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TimeLineImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        DashPathEffect lineEffect = null;//虚线效果

        if (attrs != null) {
            final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TimeLineImageView);
            mLineOrientation = a.getInt(R.styleable.TimeLineImageView_timeLineImageView_lineOrientation, mLineOrientation);
            mLineColor = a.getColor(R.styleable.TimeLineImageView_timeLineImageView_lineColor, mLineColor);
            mLineSize = a.getDimension(R.styleable.TimeLineImageView_timeLineImageView_lineSize, mLineSize);
            mLineStart = a.getBoolean(R.styleable.TimeLineImageView_timeLineImageView_lineStart, mLineStart);
            mLineEnd = a.getBoolean(R.styleable.TimeLineImageView_timeLineImageView_lineEnd, mLineEnd);
            if (a.hasValue(R.styleable.TimeLineImageView_timeLineImageView_lineType)) {
                int lineType = a.getInt(R.styleable.TimeLineImageView_timeLineImageView_lineType, STRAIGHT);
                if (lineType == DASH) {
                    float dashWidth = a.getDimension(R.styleable.TimeLineImageView_timeLineImageView_dashWidth, Resources.getSystem().getDisplayMetrics().density * 2f + .5f);
                    float dashGap = a.getDimension(R.styleable.TimeLineImageView_timeLineImageView_dashGap, Resources.getSystem().getDisplayMetrics().density * 2f + .5f);
                    lineEffect = new DashPathEffect(new float[]{dashWidth, dashGap}, 1);
                }
            }
            a.recycle();
        }

        setScaleType(ScaleType.FIT_START);//使icon至上

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(mLineColor);
        mLinePaint.setStrokeWidth(mLineSize);
        if (lineEffect != null)
            mLinePaint.setPathEffect(lineEffect);

        mLinePath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //先画line
        if (mLineStart || mLineEnd) {
            boolean isVertical = mLineOrientation == VERTICAL;//是否画垂直线
            //计算线起点终点
            int paddingStart = isVertical ? getPaddingTop() : getPaddingLeft();
            int xStart = isVertical ? getWidth() / 2 : mLineStart ? 0 : paddingStart;
            int yStart = isVertical ? mLineStart ? 0 : paddingStart : getHeight() / 2;
            int xEnd = isVertical ? xStart : mLineEnd ? getWidth() : paddingStart;
            int yEnd = isVertical ? mLineEnd ? getHeight() : paddingStart : yStart;

            mLinePath.moveTo(xStart, yStart);
            mLinePath.lineTo(xEnd, yEnd);
            canvas.drawPath(mLinePath, mLinePaint);
        }

        //再画image
        super.onDraw(canvas);
    }

    /**
     * 显示隐藏上下线
     */
    public void setLineVisibility(boolean showStart, boolean showEnd) {
        mLineStart = showStart;
        mLineEnd = showEnd;
        invalidate();
    }

    /**
     * 设置线的方向
     *
     * @param orientation use {@link #VERTICAL or #HORIZONTAL}
     */
    public void setLineOrientation(int orientation) {
        if (orientation != 0 && orientation != 1)
            throw new NullPointerException("orientation must be 0 or 1");
        else {
            mLineOrientation = orientation;
            invalidate();
        }
    }

    /**
     * 设置线为直线
     */
    public void setLineStraight() {
        mLinePaint.setPathEffect(null);
        invalidate();
    }

    /**
     * 设置线为虚线
     *
     * @param width 虚线宽度
     * @param gap   虚线间隔
     */
    public void setLineDash(float width, float gap) {
        DashPathEffect lineEffect = new DashPathEffect(new float[]{width, gap}, 1);
        mLinePaint.setPathEffect(lineEffect);
        invalidate();
    }
}
