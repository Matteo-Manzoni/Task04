import android.view.View
import android.content.Context
import android.util.AttributeSet
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Style
import android.graphics.Typeface

class CustView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    //circle and text colors
    private val circleCol: Int = Color.RED
    private val labelCol: Int = Color.YELLOW
    private val backCol: Int = Color.rgb(250,250,200)
    //label text
    private val wordsText: String = "Hello World"
    //paint variables
    private var circlePaint: Paint
    private var backPaint: Paint
    private var wordsPaint: Paint

    init {
        //paint object for drawing circles in onDraw -- also configure it
        circlePaint = Paint().apply {
            setStyle(Paint.Style.FILL)
            isAntiAlias = true

            //set the paint color using the circle color specified
            color = circleCol
        }

        backPaint = Paint().apply {
            // Set up the paint style
            setStyle(Paint.Style.FILL)
            color = backCol
        }

        wordsPaint = Paint().apply {
            color = labelCol

            //set text properties
            textAlign = Paint.Align.CENTER
            textSize = 100f
            typeface = Typeface.SANS_SERIF
        }
    }

    override fun onDraw(canvas: Canvas) {
        // Background
        // Measure the size of the canvas, we could take into account padding here
        val canvasWidth = width.toFloat()
        val canvasHeight = height.toFloat()

        // Draw rectangle with drawRect(topleftX, topLeftY, bottomRightX, bottomRightY, Paint)
        // Use Ctrl-P to see the parameters for a function
        canvas.drawRect(0f, 0f, canvasWidth, canvasHeight, backPaint)

        // Circle
        //get half of the width and height to locate the centre of the screen
        val viewWidthHalf = canvasWidth / 2f
        val viewHeightHalf = canvasHeight / 2f

        //get the radius as half of the width or height, whichever is smaller
        //subtract twenty so that it has some space around it
        val radius: Float = minOf(viewWidthHalf,viewHeightHalf) - 20

        // Draw circle with drawCircle(centerX, centerY, radius, Paint)
        canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius, circlePaint)

        // Text
        //draw the text using the string attribute and chosen properties
        canvas.drawText(wordsText, viewWidthHalf, viewHeightHalf, wordsPaint)
    }
}