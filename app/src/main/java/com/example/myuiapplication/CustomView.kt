package com.example.myuiapplication

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.WindowManager

private const val TAG = "CustomView"
class CustomView : View {

    lateinit var path: Path
    lateinit var paint: Paint
    val rectF = RectF()
    var rect: RectF? = null
    val w = Resources.getSystem().displayMetrics.widthPixels
    val h = Resources.getSystem().displayMetrics.heightPixels
    var angle = 0f
    val startAngle = 180f
    var endAngle = 0


//    constructor(context: Context) : super(context) {
//
//    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context)
        Log.d(TAG, "w: $w | h: $h")
        Log.d(TAG, "w2: $width | h2: $height")
    }

    private fun init(c: Context) {
        path = Path()
        paint = Paint()

        val screenSize = getScreenSize(c)
        Log.d(TAG, "screenSize: $screenSize")

        with(paint) {
            color = Color.BLUE
            style = Paint.Style.STROKE
            strokeWidth = 5f
        }

        rect = RectF(0f, 0f, 1080f, 800f)

        val dimensionPixel = c.resources.getDimensionPixelSize(R.dimen.curve_width).toFloat() //360
        val dimensionPixel2 = c.resources.getDimensionPixelSize(R.dimen.curve_width).toFloat() - (4.0f * dimensionPixel) //
        val top  = dimensionPixel / 2.0f
        val left  = (screenSize.x / 2 - dimensionPixel) - dimensionPixel2
        val right = (dimensionPixel2 * 2.0f) + left
        val bottom = (dimensionPixel2 * 2.0f) + top
        endAngle = ((screenSize.x / 2) - dimensionPixel).toInt()
        angle = 54.5f
        rectF.set(left, top, right, bottom)

    }


    fun getScreenSize(ctx: Context): Point {
        val windowManager = (ctx.getSystemService(Context.WINDOW_SERVICE)) as WindowManager
        val display = windowManager.defaultDisplay
        val point = Point()
        display.getSize(point)
        return point
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val top = 1800f

        Log.d(TAG, "onDraw: $width | $height")
//        path.moveTo(0f, top)
//        path.cubicTo(w * 0.40f,0f, w * 0.60f, 0f, w * 1f, top)
//        path.cubicTo(80f,1250f, 800f, 1250f, w - 20f, top)

//        canvas?.drawPath(path, paint)



//        canvas?.drawArc(rectF, startAngle, angle, false, paint)
        path.addArc(rect!!, 30f, -150f)
        canvas!!.drawPath(path, paint)


    }

}