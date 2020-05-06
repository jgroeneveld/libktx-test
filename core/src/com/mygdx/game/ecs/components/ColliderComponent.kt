package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.mygdx.game.ecs.systems.DebugRenderable
import com.mygdx.game.lib.Vec2
import com.mygdx.game.lib.ashleyext.mapperFor

class ColliderComponent(
        var radius: Float,
        var offset: Vec2 = Vec2.Zero
) : Component, DebugRenderable {

    override fun debugRender(entity: Entity, shapeRenderer: ShapeRenderer) {
        val transform = entity.transformComponent()!!

        shapeRenderer.setColor(Color.RED)

        val pos = transform.position + offset
        shapeRenderer.circle(pos.x, pos.y, radius)
    }

    companion object {
        val mapper = mapperFor<ColliderComponent>()
    }

}

fun Entity.colliderComponent(): ColliderComponent? = ColliderComponent.mapper.get(this)
