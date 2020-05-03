package com.mygdx.game.lib

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2
import ktx.math.minus
import ktx.math.plus
import ktx.math.times
import ktx.math.vec2
import kotlin.math.sqrt

data class CollisionResult(
        val normal: Vector2,
        val minimumTranslationVector: Vector2,
        val point: Vector2
)


// nez ShapeCollisionsCircle:53
fun circleToCircle(a: Circle, b: Circle): CollisionResult? {
    val aPos = vec2(a.x, a.y)
    val bPos = vec2(b.x, b.y)

    val distanceSquared = Vector2.dst2(a.x, a.y, b.x, b.y)
    val sumOfRadii = a.radius + b.radius
    val collided = distanceSquared < sumOfRadii * sumOfRadii

    if (collided) {
        val normal = (aPos - bPos).nor()

        val depth = sumOfRadii - sqrt(distanceSquared)
        val minimumTranslationVector = normal * -depth

        val point = bPos + normal * b.radius

        return CollisionResult(
                normal = normal,
                minimumTranslationVector = minimumTranslationVector,
                point = point
        )
    }

    return null
}