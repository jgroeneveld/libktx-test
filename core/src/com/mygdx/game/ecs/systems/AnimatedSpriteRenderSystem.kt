package com.mygdx.game.ecs.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.SortedIteratingSystem
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.ecs.components.AnimatedSprite
import com.mygdx.game.ecs.components.TransformComponent
import com.mygdx.game.ecs.components.animatedSprite
import com.mygdx.game.ecs.components.transformComponent
import com.mygdx.game.lib.ashleyext.allOf
import ktx.graphics.use

class AnimatedSpriteRenderSystem(
        val batch: SpriteBatch,
        val camera: OrthographicCamera
) : SortedIteratingSystem(
        allOf(TransformComponent::class, AnimatedSprite::class).get(),
        TransformComponent.zySort
) {
    override fun update(deltaTime: Float) {
        forceSort()

        batch.use(camera) {
            super.update(deltaTime)
        }
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val transform = entity.transformComponent()!!
        val render = entity.animatedSprite()!!

        render.update(deltaTime)
        val frame = render.getCurrentFrame()

        batch.draw(
                frame,
                transform.position.x + render.offset.x,
                transform.position.y + render.offset.y,
                frame.regionWidth * transform.scale.x,
                frame.regionHeight * transform.scale.y
        )
    }
}