package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Rectangle
import com.mygdx.game.ecs.systems.DebugRenderable
import com.mygdx.game.lib.Vec2
import com.mygdx.game.lib.ashleyext.mapperFor

class Selectable(
        val x: Float,
        val y: Float,
        val width: Float,
        val height: Float
) : Component, DebugRenderable {
    fun globalBounds(position: Vec2): Rectangle {
        return Rectangle(position.x + x, position.y + y, width, height)
    }

    override fun debugRender(entity: Entity, shapeRenderer: ShapeRenderer) {
        val transform = entity.transformComponent()!!
        val bounds = globalBounds(transform.position)

        shapeRenderer.color = Color.GRAY
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height)
    }

    companion object {
        val mapper = mapperFor<Selectable>()
    }
}

fun Entity.selectable(): Selectable? = Selectable.mapper.get(this)
