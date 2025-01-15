package com.example.foreachpartnerstask

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameTest {

    private lateinit var game: Game

    @BeforeEach
    fun setUp() {
        game = Game()
    }

    @Test
    fun testGetScoreExactMatch() {
        val stamps = arrayOf(
            Stamp(0, Score(0, 0)),
            Stamp(10, Score(1, 0)),
            Stamp(20, Score(1, 1)),
            Stamp(30, Score(2, 1))
        )
        val score = game.getScore(stamps, 20)
        assertEquals(1, score.home)
        assertEquals(1, score.away)
    }

    @Test
    fun testGetScoreBetweenStamps() {
        val stamps = arrayOf(
            Stamp(0, Score(0, 0)),
            Stamp(10, Score(1, 0)),
            Stamp(20, Score(1, 1)),
            Stamp(30, Score(2, 1))
        )
        val score = game.getScore(stamps, 25)
        assertEquals(1, score.home)
        assertEquals(1, score.away)
    }

    @Test
    fun testGetScoreOutOfRange() {
        val stamps = arrayOf(
            Stamp(0, Score(0, 0)),
            Stamp(10, Score(1, 0)),
            Stamp(20, Score(1, 1)),
            Stamp(30, Score(2, 1))
        )
        assertThrows(IllegalArgumentException::class.java) {
            game.getScore(stamps, 35)
        }
    }

    @Test
    fun testGenerateGame() {
        val stamps = game.generateGame()
        assertEquals(Game.TIMESTAMPS_COUNT, stamps.size)
        assertTrue(stamps.isNotEmpty())
        assertTrue(stamps.last().offset >= stamps.first().offset)
    }

    @Test
    fun testGenerateStamp() {
        val previousStamp = Stamp(0, Score(0, 0))
        val newStamp = game.generateStamp(previousStamp)
        assertTrue(newStamp.offset > previousStamp.offset)
        assertTrue(newStamp.score.home >= previousStamp.score.home)
        assertTrue(newStamp.score.away >= previousStamp.score.away)
    }
}