package com.mygdx.game.ecs.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.SortedIteratingSystem
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.mygdx.game.ecs.components.SpriteComponent
import com.mygdx.game.ecs.components.TransformComponent
import com.mygdx.game.ecs.components.spriteComponent
import com.mygdx.game.ecs.components.transformComponent
import ktx.ashley.allOf
import ktx.ashley.get
import ktx.graphics.use

class SpriteRenderSystem(
        val batch: SpriteBatch,
        val camera: OrthographicCamera
) : SortedIteratingSystem(
        allOf(TransformComponent::class, SpriteComponent::class).get(),
        compareBy { entity -> entity[SpriteComponent.mapper]?.z }
) {
    override fun update(deltaTime: Float) {
        forceSort()

        batch.use(camera) {
            super.update(deltaTime)
        }
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val transform = entity.transformComponent()!!
        val render = entity.spriteComponent()!!

        batch.draw(
                render.sprite,
                transform.position.x + render.offset.x,
                transform.position.y + render.offset.y,
                render.sprite.width * transform.scale.x,
                render.sprite.height * transform.scale.y
        )
    }
}