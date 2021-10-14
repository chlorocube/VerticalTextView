package jp.co.chlorocube.verticaltext

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

open class DirectionTextView : AppCompatTextView {

    private var mIsVertical: Boolean = true
    protected var mWidth = 0
    protected var mHeight = 0

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (lineSpacingMultiplier <= 1f)
            setLineSpacing(0f, 1.4f)
    }

    fun switchDirection() {
        mIsVertical = !mIsVertical
        invalidate()
    }

    fun isVerticalText(): Boolean {
        return mIsVertical
    }

    override fun onDraw(canvas: Canvas) {
        drawImpl(canvas)
    }

    private fun drawImpl(canvas: Canvas) {

        val lineSpacing = if (lineSpacingMultiplier > 1f)
            (lineSpacingMultiplier - 1f) * paint.fontSpacing
        else
            0f

        val textList = text.split('\n')

        if (mIsVertical)
            drawVertical(canvas, textList, lineSpacing)
        else
            drawHorizontal(canvas, textList, lineSpacing)

        if (layoutParams.width != mWidth || layoutParams.height != mHeight) {
            layoutParams.width = mWidth
            layoutParams.height = mHeight
            requestLayout()
        }
    }

    protected open fun drawVertical(canvas: Canvas, textList: List<String>, lineSpacing: Float) {

        val paint = paint
        paint.textSize = textSize
        paint.typeface = typeface
        paint.color = textColors.defaultColor

        val lineLength = textList.size
        var charLength = 0
        textList.forEachIndexed { tempIndex, textItem ->
            if (textItem.length > charLength)
                charLength = textItem.length
            val lineIndex = lineLength - tempIndex - 1
            textItem.forEachIndexed { i, it ->
                val x =
                    paint.fontSpacing * lineIndex + lineSpacing * lineIndex + paddingStart
                val y = (i + 1) * paint.fontSpacing + paddingTop
                val setting: CharSettingVertical? =
                    CharSettingVertical.getSetting(it.toString())
                if (setting == null)
                    canvas.drawText(it.toString(), x, y, paint)
                else {
                    val posX = x + paint.fontSpacing * setting.x
                    val posY = y + paint.fontSpacing * setting.y

                    canvas.save()
                    canvas.rotate(setting.angle, x, y)
                    if (setting.inversion) {
                        canvas.scale(
                            -1f, 1f, posX + paint.fontSpacing / 2,
                            posY + paint.fontSpacing / 2
                        )
                    }
                    canvas.drawText(it.toString(), posX, posY, paint)
                    canvas.restore()
                }
            }
        }

        mWidth =
            (paint.fontSpacing * lineLength + lineSpacing * (lineLength - 1)).toInt() + paddingStart + paddingEnd
        mHeight = (paint.fontSpacing * charLength).toInt() + paddingTop + paddingBottom
    }

    protected open fun drawHorizontal(canvas: Canvas, textList: List<String>, lineSpacing: Float) {

        val paint = paint
        paint.textSize = textSize
        paint.typeface = typeface
        paint.color = textColors.defaultColor

        var tempWidth = 0
        val tempHeight = paint.fontSpacing
        textList.forEachIndexed { lineIndex, textItem ->
            val x = paddingStart.toFloat()
            val y =
                (lineIndex + 1) * tempHeight + lineIndex * lineSpacing + paddingTop
            canvas.drawText(textItem, x, y, paint)

            val width = paint.measureText(textItem)
            if (tempWidth < width)
                tempWidth = width.toInt()
        }

        mWidth = tempWidth + paddingStart + paddingEnd
        mHeight =
            ((tempHeight + lineSpacing) * textList.size + paddingTop + paddingBottom).toInt()
    }
}