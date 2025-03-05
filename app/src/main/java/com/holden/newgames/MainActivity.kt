package com.holden.newgames

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private lateinit var mainLayout: ViewGroup
    private lateinit var image: ImageView

    // default position of image
    private var xDelta = 0
    private var yDelta = 0

    private var centerPress = Vector2(0f,0f)
    private val centerOffset = 50 // offset your current touch position must be from the original press

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainLayout = findViewById(R.id.main)
        image = findViewById(R.id.image)

        // returns True if the listener has
        // consumed the event, otherwise False.
        image.setOnTouchListener(onTouchListener())
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onTouchListener(): OnTouchListener {
        return OnTouchListener { view, event ->
            // position information
            // about the event by the user
            val x = event.rawX.toInt()
            val y = event.rawY.toInt()
            // detecting user actions on moving
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    val lParams = view.layoutParams as RelativeLayout.LayoutParams
                    centerPress = Vector2(x * 1f,y * 1f)
                    lParams.leftMargin = 0
                    lParams.topMargin = 0
                    mainLayout.invalidate()
                }
                MotionEvent.ACTION_UP -> {
                    val layoutParams = view.layoutParams as RelativeLayout.LayoutParams
                    layoutParams.leftMargin = 0
                    layoutParams.topMargin = 0
                    mainLayout.invalidate()
                    Toast.makeText(
                        this,
                        "new location!", Toast.LENGTH_SHORT
                    ).show()
                }
                MotionEvent.ACTION_MOVE -> {

                    val layoutParams = view.layoutParams as RelativeLayout.LayoutParams
                    //layoutParams.leftMargin = x - xDelta
                    //layoutParams.topMargin = y - yDelta
                    //layoutParams.rightMargin = 0
                    //layoutParams.bottomMargin = 0
                    //view.layoutParams = layoutParams

                    // update value based on distance from centerPress
                    val offSetV: Vector2 = centerPress.vectorTowardsPoint(Vector2(x * 1f,y * 1f))
                    if (offSetV.magnitude() >= centerOffset) {
                        if (abs(offSetV.x) > abs(offSetV.y)) { //move horizontally
                            if (offSetV.x > 0) { // move right

                            } else { // move left

                            }
                        } else { // move vertically
                            if (offSetV.y > 0) { // move up

                            } else { // move down

                            }
                        }
                        layoutParams.leftMargin = (centerPress.x - (offSetV.normalized()*50f).x).toInt()
                        layoutParams.topMargin = (centerPress.y - (offSetV.normalized()*50f).y).toInt()
                    } else {

                    }

                    layoutParams.rightMargin = 0
                    layoutParams.bottomMargin = 0
                    view.layoutParams = layoutParams
                }
            }
            // reflect the changes on screen
            mainLayout.invalidate()
            true
        }
    }
}