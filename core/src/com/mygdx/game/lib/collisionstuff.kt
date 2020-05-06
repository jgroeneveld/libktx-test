package com.mygdx.game.lib

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2
import ktx.math.minus
import ktx.math.plus
import ktx.math.times
import ktx.math.vec2
import kotlin.math.sqrt

data class CollisionResult(
        val normal: Vec2,
        val minimumTranslationVector: Vec2,
        val point: Vec2
)


// nez ShapeCollisionsCircle:53
fun circleToCircle(aPos: Vec2, aRadius: Float, bPos: Vec2, bRadius: Float): CollisionResult? {
    val distanceSquared = aPos.distanceSquared(bPos)
    val sumOfRadii = aRadius + bRadius
    val collided = distanceSquared < sumOfRadii * sumOfRadii

    if (collided) {
        val normal = (aPos - bPos).normalize()

        val depth = sumOfRadii - sqrt(distanceSquared)
        val minimumTranslationVector = normal * -depth

        val point = bPos + normal * bRadius

        return CollisionResult(
                normal = normal,
                minimumTranslationVector = minimumTranslationVector,
                point = point
        )
    }

    return null
}