package com.holden.newgames


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.log
import kotlin.random.Random


class HangmanGame : AppCompatActivity() {

    var wrong = 0;
    var word = "newword";
    var guesses = ArrayList<Char>();

    val wordPool = listOf(
        "ocean", "giraffe", "marble", "twilight", "velvet", "cactus", "wander", "breeze", "echo", "lantern",
        "ember", "crystal", "shadow", "nectar", "whistle", "drift", "thunder", "petal", "glimmer", "foggy",
        "meadow", "ripple", "quartz", "solstice", "midnight", "lilac", "hazel", "willow", "feather", "fable",
        "hollow", "frost", "pebble", "dusk", "gleam", "harbor", "pine", "horizon", "blossom", "raven",
        "murmur", "sapphire", "storm", "cobweb", "ivy", "galaxy", "wisp", "boulder", "aurora", "bloom"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hangman)
        var guessInput = findViewById<EditText>(R.id.guessInput)
        setupGame()
        guessInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (s?.length == 0) return
                //Log.d("sometag", "char: " + s!!.get(0))
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
        updateAns()
    }
    fun newWord():String {
        var ran:Int = Random.nextInt(wordPool.size)
        return wordPool[ran];
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
    fun updateWrongList(c:Char) {
        var ansText = findViewById<TextView>(R.id.wrongTextList)
        var oldText = ansText.getText()
        if (wrong > 1)
            ansText.setText(oldText.toString() + ", " + c)
        else {
            ansText.setText(oldText.toString() + c)
        }
    }

    fun guess(c:Char) {
        if (c in guesses) {
            // already made this guess
            //Log.d("sometag", "already made this guess")
            return;
        }
        guesses.add(c)
        if (correctGuess(c)) {
            updateAns()
        } else {
            wrong++
            updateWrong()
            updateWrongList(c)
        }
    }

    fun correctGuess(c:Char):Boolean {
        return word.contains(c)
    }

}