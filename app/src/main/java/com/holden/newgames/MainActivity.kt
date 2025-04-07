package com.holden.newgames

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var mainLayout: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainLayout = findViewById(R.id.main)
    }

    fun sendMessage(view: View?) {
        if (R.id.snakeButton == view?.id) {
            // open snake
            startActivity(Intent(this@MainActivity, SnakeGame::class.java))

        } else if (R.id.hangmanButton == view?.id){
            // do other game or something2
            startActivity(Intent(this@MainActivity, HangmanGame::class.java))
        }
    }

}