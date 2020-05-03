package com.mygdx.game.ecs.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.ecs.components.*
import ktx.ashley.allOf
import ktx.log.debug
import ktx.math.minus
import ktx.math.vec2

class MoveToTargetSystem: IteratingSystem(
        allOf(TargetFinderComponent::class, MoveComponent::class, TransformComponent::class).get()
) {
    override fun processEntity(entity: Entity, deltaTime: Float) {
        val transform = entity.transformComponent()!!
        val targetFinder = entity.targetFinderComponent()!!
        val move = entity.moveComponent()!!

        if (targetFinder.target != null && transform.position.dst2(targetFinder.target!!) < 3) {
            targetFinder.target = null
        }

        if (targetFinder.target != null) {
            move.direction = (targetFinder.target!! - transform.position).nor()
        } else {
            move.direction = Vector2.Zero
        }
    }
}