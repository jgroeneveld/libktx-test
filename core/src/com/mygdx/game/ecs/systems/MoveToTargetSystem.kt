package com.mygdx.game.ecs.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.mygdx.game.ecs.components.*
import com.mygdx.game.lib.Vec2
import com.mygdx.game.lib.ashleyext.allOf

class MoveToTargetSystem : IteratingSystem(
        allOf(TargetFinderComponent::class, MoveComponent::class, TransformComponent::class).get()
) {
    override fun processEntity(entity: Entity, deltaTime: Float) {
        val transform = entity.transformComponent()!!
        val targetFinder = entity.targetFinderComponent()!!
        val move = entity.moveComponent()!!

        if (targetFinder.target != null && transform.position.distanceSquared(targetFinder.target!!) < 3) {
            targetFinder.target = null
        }

        if (targetFinder.target != null) {
            move.direction = (targetFinder.target!! - transform.position).normalize()
        } else {
            move.direction = Vec2.Zero
        }
    }
}