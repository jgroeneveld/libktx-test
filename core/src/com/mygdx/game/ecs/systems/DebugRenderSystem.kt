package com.mygdx.game.ecs.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.mygdx.game.ecs.components.*
import ktx.ashley.allOf
import ktx.graphics.circle
import ktx.graphics.use

interface DebugRenderable {
    fun debugRender(entity: Entity, shapeRenderer: ShapeRenderer)
}

class DebugRenderSystem(
        val camera: OrthographicCamera
) : IteratingSystem(
        allOf(TransformComponent::class).get()
) {
    val shapeRenderer = ShapeRenderer()

    override fun update(deltaTime: Float) {
        shapeRenderer.projectionMatrix = camera.combined

        shapeRenderer.use(ShapeRenderer.ShapeType.Line) {
            super.update(deltaTime)
        }
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val transform = entity.transformComponent()!!

        for (component in entity.components) {
            if (component is DebugRenderable) {
                component.debugRender(entity, shapeRenderer)
            }
        }
    }
}