package com.example.fourcolourapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import com.google.android.material.snackbar.Snackbar

class FourColours: View {


    private val myGestureDetector = GestureDetectorCompat(context, MyGestureListener())
    private val paint = Paint()

    private var selectedColor: String = ""


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        // 'this' is a reference to the current class.
        // Views have many properties - one is backgroundColor.
        this.setBackgroundColor(Color.argb(128,32,64,255))
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return myGestureDetector.onTouchEvent(event) || super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // Draw the four rectangles with different colors
        paint.color = Color.RED
        canvas?.drawRect(0f, 0f, width / 2f, height / 2f, paint)

        paint.color = Color.GREEN
        canvas?.drawRect(width / 2f, 0f, width.toFloat(), height / 2f, paint)

        paint.color = Color.BLUE
        canvas?.drawRect(0f, height / 2f, width / 2f, height.toFloat(), paint)

        paint.color = Color.YELLOW
        canvas?.drawRect(width / 2f, height / 2f, width.toFloat(), height.toFloat(), paint)
    }

    private inner class MyGestureListener: GestureDetector.SimpleOnGestureListener() {

        override fun onDown(ev: MotionEvent): Boolean {
            /*
             * You should always include onDown().
             *
             * Normally onDown should return true, unless you want to ignore all touch gestures
             * starting in a particular region of your view (you will not see events from outside
             * the view anyway).
             */
            return true
        }

        override fun onSingleTapUp(ev: MotionEvent): Boolean {
            // You can access the properties of the event
            val xCoord = ev.x
            val yCoord = ev.y
            val quadrant = when {
                xCoord < width / 2f && yCoord < height / 2f -> "RED"
                xCoord >= width / 2f && yCoord < height / 2f -> "GREEN"
                xCoord < width / 2f && yCoord >= height / 2f -> "BLUE"
                else -> "YELLOW"
            }

            selectedColor = quadrant
            Snackbar
                .make(this@FourColours, "SingleTapUp x= $selectedColor", Snackbar.LENGTH_SHORT)
                .show()

            return true
        }

        override fun onDoubleTap(ev: MotionEvent): Boolean {
            val xCoord = ev.x
            val yCoord = ev.y

            Log.d(LOGTAG, "onDoubleTap x= $xCoord y= $yCoord")
            return true
        }

        override fun onFling(
            ev: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            Log.d(LOGTAG, "Fling")
            return true
        }

    }      // End of myGestureListener class


    companion object {         // declare a constant (must be in the companion of the outer class)
        const val LOGTAG = "MyTask"
    }
}