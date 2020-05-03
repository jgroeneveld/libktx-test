package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.ecs.systems.DebugRenderable
import ktx.ashley.get
import ktx.ashley.mapperFor

class TransformComponent : Component, DebugRenderable {
    companion object {
        val mapper = mapperFor<TransformComponent>()
    }

    val position = Vector2()
    val scale = Vector2(1f, 1f)

    override fun debugRender(entity: Entity, shapeRenderer: ShapeRenderer) {
        shapeRenderer.setColor(Color.WHITE)

        shapeRenderer.point(position.x, position.y, 0f)
    }
}

fun Entity.transformComponent(): TransformComponent? = TransformComponent.mapper.get(this)