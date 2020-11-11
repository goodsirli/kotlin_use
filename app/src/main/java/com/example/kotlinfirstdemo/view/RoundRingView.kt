package com.example.kotlinfirstdemo.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

/**
 * 饼状百分比图
 */
class RoundRingView : View {

    val TAG = "RoundRingView"

    //圆形两边的空余空间大小
    var EMPTY_SIZE = 100f

    //扇形的大小集合
    var mDatas = ArrayList<Float>()
    var mColors = ArrayList<Int>()
    var mPaint = Paint()

    var mLinePaint = Paint()
    var mTextPaint = Paint()
    var mCirclePaint = Paint()

    /**
     * 横线的长度x轴的长度
     */
    var mLineae: Int = 30

    /**
     * 斜线的长度x轴和y轴的长度
     */
    var mSlantLine: Int = 30

    constructor(context: Context) : this(context, attrs = null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initPaint()
    }


    fun initPaint() {
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.FILL_AND_STROKE

        mLinePaint.isAntiAlias = true
        mLinePaint.isDither = true
        mLinePaint.style = Paint.Style.STROKE
        mLinePaint.strokeWidth = (1.0f).toFloat()
        mLinePaint.color = Color.BLACK

        mTextPaint.isAntiAlias = true
        mTextPaint.isDither = true
        mTextPaint.style = Paint.Style.STROKE
        mTextPaint.strokeWidth = (1.0f).toFloat()
        mTextPaint.textSize = 20f
        mTextPaint.color = Color.BLACK

        mCirclePaint.isAntiAlias = true
        mCirclePaint.isDither = true
        mCirclePaint.style = Paint.Style.FILL
        mCirclePaint.color = Color.WHITE

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        //计算横线的比例
        mLineae = (width / 30f).toInt()
        //计算斜线的比例
        mSlantLine = (width / 40f).toInt()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val layoutSize = min(widthSize, heightSize)

        setMeasuredDimension(layoutSize, layoutSize)
    }

    fun selfDraw2(canvas: Canvas?) {
        if (mDatas.size == 0) return

        var total = 0f
        mDatas.forEach {
            total += it
        }

        val ref = RectF(0f + EMPTY_SIZE, 0f +EMPTY_SIZE, width * 1.0f -EMPTY_SIZE, height * 1.0f-EMPTY_SIZE)
        var startAngle = -90f
        var i = 0

        mDatas.forEach {

            mPaint.color = mColors[i]
            val scale = it / total
            val sweepAngle = it * 360 / total
            //画弧形
            canvas?.drawArc(ref, startAngle, sweepAngle, true, mPaint)

            //画折线
            val path = Path()
            //将path 定位到弧形的一半位置，sweepAngle 为0表示只定位，不绘制
            path.arcTo(ref, startAngle + sweepAngle / 2f, 0f, false)
            val bounds = RectF()
            path.computeBounds(bounds, true)

            //获取当前的百分比文字
            val textStr = String.format("%.2f%%", scale * 100)
            //获取文字的宽度
            val textWidth = mTextPaint.measureText(textStr)
            val heightRect = Rect()
            mTextPaint.getTextBounds(textStr,0,textStr.length,heightRect)
            val textHeight = heightRect.bottom - heightRect.top
//
            //第一象限
            if (bounds.left >= width / 2.0f && bounds.top <= height / 2.0f) {
                path.lineTo(bounds.left + mLineae, bounds.top)
                path.lineTo(bounds.left + mLineae + mSlantLine, bounds.top - mSlantLine)

                canvas?.drawPath(path, mLinePaint)
                //写文本
                canvas?.drawText(
                    textStr,
                    bounds.left + mLineae + mSlantLine,
                    bounds.top - mSlantLine,
                    mTextPaint
                )
            }
            //第二象限
            else if (bounds.left <= width / 2.0f && bounds.top <= width / 2f) {
                path.lineTo(bounds.left - mLineae, bounds.top)
                path.lineTo(bounds.left - mLineae - mSlantLine, bounds.top - mSlantLine)

                canvas?.drawPath(path, mLinePaint)
                canvas?.drawText(
                    textStr,
                    bounds.left - mLineae - mSlantLine -textWidth,
                    bounds.top - mSlantLine,
                    mTextPaint
                )
            }
            //第三象限
            else if (bounds.left <= width / 2f && bounds.top >= width / 2f) {
                path.lineTo(bounds.left - mLineae, bounds.top)
                path.lineTo(bounds.left - mLineae - mSlantLine, bounds.top + mSlantLine)

                canvas?.drawPath(path, mLinePaint)
                canvas?.drawText(
                    textStr,
                    bounds.left - mLineae - mSlantLine - textWidth,
                    bounds.top + mSlantLine + textHeight,
                    mTextPaint
                )
            }
            //第四象限
            else {
                path.lineTo(bounds.left + mLineae, bounds.top)
                path.lineTo(bounds.left + mLineae + mSlantLine, bounds.top + mSlantLine)

                canvas?.drawPath(path, mLinePaint)
                canvas?.drawText(
                    textStr,
                    bounds.left + mLineae + mSlantLine,
                    bounds.top + mSlantLine ,
                    mTextPaint
                )
            }


            startAngle += sweepAngle
            i++
        }

        canvas?.drawCircle(width/2f,height/2f,width/8f,mCirclePaint)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        selfDraw2(canvas)
    }

    fun setData(data: ArrayList<Float>, colors: ArrayList<Int>) {
        mDatas = data
        mColors = colors
        invalidate()
    }

}