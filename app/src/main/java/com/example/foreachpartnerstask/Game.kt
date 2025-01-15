package com.example.foreachpartnerstask

import kotlin.math.floor
import kotlin.random.Random

class Game {

    fun getScore(gameStamps: Array<Stamp>, offset: Int): Score {
        if (offset < 0 || offset > gameStamps.last().offset) {
            throw IllegalArgumentException("Смещение выходит за пределы допустимого диапазона")
        }

        var left = 0
        var right = gameStamps.size - 1

        while (left <= right) {
            val mid = left + (right - left) / 2
            val currentStamp = gameStamps[mid]

            when {
                currentStamp.offset == offset -> return currentStamp.score
                currentStamp.offset < offset -> left = mid + 1
                else -> right = mid - 1
            }
        }

        return gameStamps[right].score
    }

    fun generateGame(): Array<Stamp> {
        val stamps = Array(TIMESTAMPS_COUNT) { _ -> Stamp(0, Score(0, 0)) }
        var currentStamp = stamps[0]
        for (i in 1 until TIMESTAMPS_COUNT) {
            currentStamp = generateStamp(currentStamp)
            stamps[i] = currentStamp
        }
        return stamps
    }

    fun generateStamp(previousValue: Stamp): Stamp {
        val scoreChanged = Random.nextFloat() > 1 - PROBABILITY_SCORE_CHANGED
        val homeScoreChange =
            if (scoreChanged && Random.nextFloat() > 1 - PROBABILITY_HOME_SCORE) 1 else 0
        val awayScoreChange = if (scoreChanged && homeScoreChange == 0) 1 else 0
        val offsetChange = (floor(Random.nextFloat() * OFFSET_MAX_STEP) + 1).toInt()

        return Stamp(
            previousValue.offset + offsetChange,
            Score(
                previousValue.score.home + homeScoreChange,
                previousValue.score.away + awayScoreChange
            )
        )
    }

    companion object {
        const val TIMESTAMPS_COUNT = 100000
        const val PROBABILITY_SCORE_CHANGED = 0.1f
        const val PROBABILITY_HOME_SCORE = 0.45f
        const val OFFSET_MAX_STEP = 3
    }
}

data class Score(val home: Int, val away: Int)
data class Stamp(val offset: Int, val score: Score)