package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.ecs.systems.DebugRenderable
import ktx.ashley.mapperFor
import ktx.graphics.circle
import ktx.math.plus

class ColliderComponent() : Component, DebugRenderable {
    companion object {
        val mapper = mapperFor<ColliderComponent>()
    }

    var radius: Float = 2f
    var offset: Vector2 = Vector2()

    override fun debugRender(entity: Entity, shapeRenderer: ShapeRenderer) {
        val transform = entity.transformComponent()!!

        shapeRenderer.setColor(Color.RED)

        shapeRenderer.circle(transform.position + offset, radius)
    }
}

fun Entity.colliderComponent(): ColliderComponent? = ColliderComponent.mapper.get(this)
