package jp.co.chlorocube.verticaltext

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet

open class EdgeDirectionTextView : DirectionTextView {

    companion object {
        private const val EDGE_WIDTH_DEFAULT = 24
        private const val EDGE_COLOR_DEFAULT = Color.WHITE
    }

    private var mText: String? = null
    private var mEdgeWidth: Float = EDGE_WIDTH_DEFAULT.toFloat()
    private var mEdgeColor: Int = EDGE_COLOR_DEFAULT

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    override fun getText(): CharSequence {
        if (mText == null) {
            mText = super.getText().toString()
        }
        return mText!!
    }

    override fun setText(text: CharSequence?, type: BufferType?) {

        mText = text.toString()

        var temp = text.toString().replace("\n", " \n ")
        temp = " $temp "
        super.setText(temp, type)
    }

    fun setEdgeColor(edgeColor: Int) {
        mEdgeColor = edgeColor
    }

    fun setEdgeWidth(edgeWidth: Float) {
        mEdgeWidth = edgeWidth
    }

    override fun drawVertical(canvas: Canvas, textList: List<String>, lineSpacing: Float) {

        val paint = this.paint
        paint.textSize = textSize
        paint.typeface = typeface
        paint.color = textColors.defaultColor

        val edgePaint = Paint(this.paint)
        edgePaint.style = Paint.Style.STROKE
        edgePaint.strokeJoin = Paint.Join.ROUND
        edgePaint.color = mEdgeColor
        edgePaint.strokeWidth = mEdgeWidth

        val lineLength = textList.size
        var charLength = 0
        textList.forEachIndexed { line, textItem ->
            if (textItem.length > charLength)
                charLength = textItem.length
            val lineIndex = lineLength - line - 1
            textItem.forEachIndexed { i, it ->
                val x =
                    paint.fontSpacing * lineIndex + lineSpacing * lineIndex + paddingStart
                val y = (i + 1) * paint.fontSpacing + paddingTop
                val setting: CharSettingVertical? =
                    CharSettingVertical.getSetting(it.toString())
                if (setting == null) {
                    canvas.drawText(it.toString(), x, y, edgePaint)
                } else {
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
                    canvas.drawText(it.toString(), posX, posY, edgePaint)
                    canvas.restore()
                }
            }
        }
        textList.forEachIndexed { tempIndex, textItem ->
            val lineIndex = lineLength - tempIndex - 1
            textItem.forEachIndexed { i, it ->
                val x =
                    paint.fontSpacing * lineIndex + lineSpacing * lineIndex + paddingStart
                val y = (i + 1) * paint.fontSpacing + paddingTop
                val setting: CharSettingVertical? =
                    CharSettingVertical.getSetting(it.toString())
                if (setting == null) {
                    canvas.drawText(it.toString(), x, y, paint)
                } else {
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

    override fun drawHorizontal(canvas: Canvas, textList: List<String>, lineSpacing: Float) {

        val paint = this.paint
        paint.textSize = textSize
        paint.typeface = typeface
        paint.color = textColors.defaultColor

        val edgePaint = Paint(this.paint)
        edgePaint.style = Paint.Style.STROKE
        edgePaint.strokeJoin = Paint.Join.ROUND
        edgePaint.color = mEdgeColor
        edgePaint.strokeWidth = mEdgeWidth

        var tempWidth = 0
        val tempHeight = paint.fontSpacing
        textList.forEachIndexed { lineIndex, textItem ->
            val x = paddingStart.toFloat()
            val y =
                (lineIndex + 1) * tempHeight + lineIndex * lineSpacing + paddingTop
            canvas.drawText(textItem, x, y, edgePaint)

            val width = paint.measureText(textItem)
            if (tempWidth < width)
                tempWidth = width.toInt()
        }
        textList.forEachIndexed { lineIndex, textItem ->
            val x = paddingStart.toFloat()
            val y =
                (lineIndex + 1) * tempHeight + lineIndex * lineSpacing + paddingTop
            canvas.drawText(textItem, x, y, paint)
        }

        mWidth = tempWidth + paddingStart + paddingEnd
        mHeight =
            ((tempHeight + lineSpacing) * textList.size + paddingTop + paddingBottom).toInt()
    }
}