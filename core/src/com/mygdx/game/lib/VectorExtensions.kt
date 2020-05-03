package com.mygdx.game.lib

import com.badlogic.gdx.math.Vector2
import ktx.math.minus

fun Vector2.towards(target: Vector2, maxDistanceDelta: Float): Vector2 {
    val a = target - this
    val magnitude: Float = a.len()

    if (magnitude <= maxDistanceDelta || magnitude == 0f) {
        return target
    }

    return Vector2(
            this.x + a.x / magnitude * maxDistanceDelta,
            this.y + a.y / magnitude * maxDistanceDelta
    )
}