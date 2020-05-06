package com.mygdx.game.lib

import com.badlogic.gdx.math.Vector2
import ktx.math.minus
import kotlin.math.sqrt

// Vec2 is a immutable replacement for libgdx Vector2
// Use Vector2 for performance, otherwise this is easier to use.

data class Vec2(val x: Float, val y: Float) {
    val isZero = x == 0f && y == 0f

    operator fun plus(other: Vec2): Vec2 {
        return Vec2(x + other.x, y + other.y)
    }

    operator fun minus(other: Vec2): Vec2 {
        return Vec2(x - other.x, y - other.y)
    }

    operator fun times(factor: Float): Vec2 {
        return Vec2(x * factor, y * factor)
    }

    fun distanceSquared(target: Vec2): Float {
        return Vector2.dst2(x, y, target.x, target.y)
    }

    fun normalize(): Vec2 {
        val len = length()
        if (len == 0f) {
            return this
        }

        return Vec2(x / len, y / len)
    }

    fun length(): Float {
        return sqrt(x * x + y * y)
    }

    fun towards(target: Vec2, maxDistanceDelta: Float): Vec2 {
        val a = target - this
        val magnitude: Float = a.length()

        if (magnitude <= maxDistanceDelta || magnitude == 0f) {
            return target
        }

        return Vec2(
                this.x + a.x / magnitude * maxDistanceDelta,
                this.y + a.y / magnitude * maxDistanceDelta
        )
    }

    companion object {
        val Zero = Vec2(0f, 0f)
    }
}
