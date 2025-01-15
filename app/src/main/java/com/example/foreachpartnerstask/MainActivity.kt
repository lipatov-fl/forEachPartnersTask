package com.example.foreachpartnerstask

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val game = Game()

        val gameStamps = game.generateGame()
        for (i in 0 until 10) {
            Log.d("GameDebug", "Штамп $i: ${gameStamps[i]}")
        }

        val offset = Random.nextInt(0, gameStamps.last().offset + 1)
        val score = game.getScore(gameStamps, offset)

        Log.d("GameScore", "Счет на смещении $offset: Дома ${score.home} - Гости ${score.away}")
    }
}