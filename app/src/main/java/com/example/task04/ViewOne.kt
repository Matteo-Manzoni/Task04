package com.example.task04

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import com.google.android.material.snackbar.Snackbar

class ViewOne: View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val myGestureDetector = GestureDetectorCompat(context, MyGestureListener())

    init {
        // 'this' is a reference to the current class.
        // Views have many properties - one is backgroundColor.
        this.setBackgroundColor(Color.argb(128,32,64,255))
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return myGestureDetector.onTouchEvent(event) || super.onTouchEvent(event)
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

            Log.d(LOGTAG, "SingleTapUp x= $xCoord y= $yCoord")

            Snackbar
                .make(this@ViewOne, "SingleTapUp x= $xCoord y= $yCoord", Snackbar.LENGTH_SHORT)
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
