package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.ecs.systems.DebugRenderable
import com.mygdx.game.lib.Vec2
import com.mygdx.game.lib.ashleyext.mapperFor

class TransformComponent(
        var position: Vec2 = Vec2.Zero,
        var scale: Vec2 = Vec2(1f, 1f),
        var z: Int = 0
): Component, DebugRenderable {
    override fun debugRender(entity: Entity, shapeRenderer: ShapeRenderer) {
        shapeRenderer.setColor(Color.WHITE)

        shapeRenderer.point(position.x, position.y, 0f)
    }

    companion object {
        val mapper = mapperFor<TransformComponent>()

        val zySort = compareBy<Entity>(
                { it.transformComponent()!!.z },
                { -it.transformComponent()!!.position.y }
        )
    }
}

fun Entity.transformComponent(): TransformComponent? = TransformComponent.mapper.get(this)