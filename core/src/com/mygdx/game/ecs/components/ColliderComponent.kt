package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.ecs.systems.DebugRenderable
import com.mygdx.game.lib.ashleyext.mapperFor
import ktx.graphics.circle
import ktx.math.plus

class ColliderComponent(
        var radius: Float,
        var offset: Vector2 = Vector2()
) : Component, DebugRenderable {

    override fun debugRender(entity: Entity, shapeRenderer: ShapeRenderer) {
        val transform = entity.transformComponent()!!

        shapeRenderer.setColor(Color.RED)

        shapeRenderer.circle(transform.position + offset, radius)
    }

    companion object {
        val mapper = mapperFor<ColliderComponent>()
    }

}

fun Entity.colliderComponent(): ColliderComponent? = ColliderComponent.mapper.get(this)
