package com.holden.newgames


import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import java.util.LinkedList
import java.util.Queue
import androidx.activity.compose.setContent

import androidx.compose.material3.Text



class SnakeGame : AppCompatActivity() {

    var moveControls : Vector2 = Vector2(0f,0f)

    var head : Vector2 = Vector2(0f,0f) // where is the player right now

    var segments: Queue<Vector2> = LinkedList() // the segments going from the tail to the head (pop tail every tick)


    private var tmp: String = "SOMEHTING"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snake)
        setContent {
            GridItem("hi")
        }


        //setupControls()
    }

    private fun setupControls() {
        val button = findViewById<Button>(R.id.grid_item)
        button.setOnClickListener {
            Log.d("error","thing")
            tmp += "A "
            updateView()
        }

    }
    fun updateView() {
        setContent {
            GridItem(tmp)
        }
    }

    @Composable
    fun GridItem(name: String) {
        Text(text = "Hello $name!")
    }

    @Preview
    @Composable
    fun PrevAll() {
        GridItem("jim")
    }


}
