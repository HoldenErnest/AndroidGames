package com.holden.newgames


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.log


class HangmanGame : AppCompatActivity() {

    var wrong = 0;
    var word = "newword";
    var guesses = ArrayList<Char>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hangman)
        var guessInput = findViewById<EditText>(R.id.guessInput)
        guessInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (s?.length == 0) return
                Log.d("sometag", "char: " + s!!.get(0))
                guess(s!!.get(0))
            }

            override fun afterTextChanged(p0: Editable?) {
                guessInput.text.clear()
            }
        })
    }

    fun setupGame () {
        wrong = 0
        word = newWord()
        guesses.clear()
    }
    fun newWord():String {
        return "newword"
    }

    fun ansString():String {
        var ansString = ""
        for (letter in word) {
            if (guesses.contains(letter)) {
                ansString += letter
            } else {
                ansString += "_"
            }
            ansString += " "
        }
        return ansString
    }
    fun updateAns() { // display the answer string
        var ansText = findViewById<TextView>(R.id.answerText)
        ansText.text = ansString()
    }
    fun updateWrong() {
        var ansText = findViewById<TextView>(R.id.wrongText)
        ansText.text = ("wrong: " + wrong.toString())
    }

    fun guess(c:Char) {
        guesses.add(c)
        if (correctGuess(c)) {
            updateAns()
        } else {
            wrong++
            updateWrong()
        }
    }

    fun correctGuess(c:Char):Boolean {
        return word.contains(c)
    }

}