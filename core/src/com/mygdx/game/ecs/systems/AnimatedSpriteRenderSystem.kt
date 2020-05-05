package com.mygdx.game.ecs.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.SortedIteratingSystem
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.ecs.components.*
import ktx.ashley.allOf
import ktx.graphics.use

class AnimatedSpriteRenderSystem(
        val batch: SpriteBatch,
        val camera: OrthographicCamera
) : SortedIteratingSystem(
        allOf(TransformComponent::class, AnimatedSpriteRenderer::class).get(),
        compareByDescending { entity -> (entity.animatedSpriteRenderer()?.let { it.z * -10000 } ?: 0) + (entity.transformComponent()?.position?.y ?: 0f) }
) {
    override fun update(deltaTime: Float) {
        forceSort()

        batch.use(camera) {
            super.update(deltaTime)
        }
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val transform = entity.transformComponent()!!
        val render = entity.animatedSpriteRenderer()!!

        render.stateTime += deltaTime
        val frame = render.animation.getKeyFrame(render.stateTime, true)

        batch.draw(
                frame,
                transform.position.x + render.offset.x,
                transform.position.y + render.offset.y,
                frame.regionWidth * transform.scale.x,
                frame.regionHeight * transform.scale.y
        )
    }
}