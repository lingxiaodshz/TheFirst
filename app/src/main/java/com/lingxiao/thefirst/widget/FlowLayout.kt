package com.lingxiao.thefirst.widget

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup


/**
 * @author luckw
 * @date   2020/4/22
 */
class FlowLayout : ViewGroup {
    private val mHorizontalSpacing = dp2px(16) //每个item横向间距
    private val mVerticalSpacing = dp2px(8) //每个item横向间距

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    fun init(context: Context) {

    }

    private lateinit var lineHeights: MutableList<Int>
    private lateinit var allLines: MutableList<MutableList<View>>
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        lineHeights = mutableListOf<Int>()
        allLines = mutableListOf<MutableList<View>>()

        val selfWidth = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val selfHeight = MeasureSpec.getSize(heightMeasureSpec)

        var lineViews= mutableListOf<View>()
        var lineWidthUsed = 0
        var lineHeight = 0

        var parentNeededWidth = 0
        var parentNeededHeight = 0

        for (index in 0 until childCount) {
            var childView = getChildAt(index)
            measureChild(childView, widthMeasureSpec, heightMeasureSpec)

            var childMesauredWidth = childView.measuredWidth
            var childMeasuredHeight = childView.measuredHeight

            if (childMesauredWidth + lineWidthUsed + mHorizontalSpacing > selfWidth) {
                allLines.add(lineViews)
                lineHeights.add(lineHeight)

                parentNeededHeight += lineHeight + mVerticalSpacing
                parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed + mHorizontalSpacing)

                lineViews = mutableListOf<View>()
                lineWidthUsed = 0
                lineHeight = 0
            }

            lineViews.add(childView)

            lineWidthUsed += childMesauredWidth + mHorizontalSpacing
            lineHeight = Math.max(lineHeight, childMeasuredHeight)

            if (index == childCount - 1) {
                lineHeights.add(lineHeight)
                allLines.add(lineViews)
                parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed)
                parentNeededHeight += lineHeight
            }

        }
        var realWidht = if (widthMode == MeasureSpec.EXACTLY) selfWidth else parentNeededWidth
        var realHeight = if (heightMode==MeasureSpec.EXACTLY) selfHeight else parentNeededHeight
        setMeasuredDimension(realWidht, realHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var lineCount = allLines.size
        var curL = paddingLeft
        var curT = paddingTop
        for (i in 0 until lineCount) {
            var lineViews = allLines.get(i)
            for (j in 0 until lineViews.size) {
                var view = lineViews.get(j)
                var left = curL
                var top = curT
                var bottom = top + view.measuredHeight
                var right = left + view.measuredWidth
                view.layout(left, top, right, bottom)
                curL = right + mHorizontalSpacing
            }
            curL = paddingLeft
            curT = curT + lineHeights.get(i) + mVerticalSpacing
        }
    }

    fun dp2px(dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), Resources.getSystem().displayMetrics).toInt()
    }

}