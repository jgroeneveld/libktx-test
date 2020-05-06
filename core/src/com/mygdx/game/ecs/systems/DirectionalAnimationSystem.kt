package com.mygdx.game.ecs.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.mygdx.game.ecs.components.*
import com.mygdx.game.lib.ashleyext.allOf

class DirectionalAnimationSystem : IteratingSystem(
        allOf(
                MoveComponent::class,
                AnimatedSprite::class,
                DirectionalAnimationPlayer::class
        ).get()
) {
    override fun processEntity(entity: Entity, deltaTime: Float) {
        val move = entity.moveComponent()!!
        val animatedSprite = entity.animatedSprite()!!
        val directionalAnimation = entity.directionalAnimationPlayer()!!

        val dir = if (move.direction.isZero) Direction.NONE else angleToDirection(move.direction.angle())
        val nextAnimation = directionalAnimation[dir]

        if (!animatedSprite.isPlaying(nextAnimation)) {
            animatedSprite.play(nextAnimation)
        }
    }

    // calculates the quadrant shifted by 45 degree to have compass
    private fun angleToDirection(angle: Float): Direction {
        var shiftedAngle = angle - 45

        if (shiftedAngle < 0) {
            shiftedAngle += 360
        }

        val index = shiftedAngle.toInt() / 90

        return angleToDirectionMapping.getValue(index)
    }

    private val angleToDirectionMapping = mapOf(
            0 to Direction.NORTH,
            1 to Direction.WEST,
            2 to Direction.SOUTH,
            3 to Direction.EAST
    )
}