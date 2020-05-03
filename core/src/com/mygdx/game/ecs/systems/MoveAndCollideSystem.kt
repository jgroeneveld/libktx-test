package com.mygdx.game.ecs.systems

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.ecs.components.*
import com.mygdx.game.lib.circleToCircle
import com.mygdx.game.towards
import ktx.ashley.allOf
import ktx.log.debug
import ktx.math.plus
import ktx.math.times


class MoveAndCollideSystem() : IteratingSystem(
        // optionally SoftCollisionComponent
        allOf(TransformComponent::class, MoveComponent::class, ColliderComponent::class).get()
) {
    override fun addedToEngine(engine: Engine?) {
        super.addedToEngine(engine)

        debug { "added to engine" }
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val transform = entity.transformComponent()!!
        val move = entity.moveComponent()!!
        val collider = entity.colliderComponent()!!

        updateVelocity(move, deltaTime)

        val step = move.velocity * deltaTime

        for (other in this.entities) {
            if (other == entity) {
                continue
            }
            val otherTransform = other.transformComponent()!!
            val otherCollider = other.colliderComponent()!!

            val result = circleToCircle(
                    Circle(transform.position + step + collider.offset, collider.radius),
                    Circle(otherTransform.position + otherCollider.offset, otherCollider.radius)
            )

            if (result != null) {
                var separate = result.minimumTranslationVector
                entity.softCollisionComponent()?.let {
                    separate *= it.separationStrength
                }
                step.sub(separate)
            }
        }

        transform.position.add(step.x, step.y)
    }

    private fun updateVelocity(move: MoveComponent, deltaTime: Float) {
        val direction = move.direction

        val newVel = if (!direction.isZero) {
            move.velocity.towards(direction * move.maxSpeed, move.acceleration * deltaTime)
        } else {
            move.velocity.towards(Vector2.Zero, move.friction * deltaTime)
        }
        move.velocity.set(newVel)
    }
}
