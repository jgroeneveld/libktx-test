package com.mygdx.game.ecs.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.mygdx.game.ecs.components.MoveComponent
import com.mygdx.game.ecs.components.TransformComponent
import ktx.ashley.allOf
import ktx.ashley.get
import kotlin.math.abs

class MoveSystem() : IteratingSystem(
        allOf(TransformComponent::class, MoveComponent::class).get()
) {
    override fun processEntity(entity: Entity, deltaTime: Float) {
        entity[TransformComponent.mapper]?.let { transform ->
            entity[MoveComponent.mapper]?.let { move ->
                if (transform.position.x <= -50) {
                    move.velocity.x = abs(move.velocity.x)
                }
                if (transform.position.x >= 240 * 3) {
                    move.velocity.x = -abs(move.velocity.x)
                }
                if (transform.position.y <= -50) {
                    move.velocity.y = abs(move.velocity.y)
                }
                if (transform.position.y >= 135 * 3) {
                    move.velocity.y = -abs(move.velocity.y)
                }
                transform.position.x += move.velocity.x * deltaTime
                transform.position.y += move.velocity.y * deltaTime

            }
        }
    }
}