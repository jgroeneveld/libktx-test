package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.ecs.systems.DebugRenderable
import ktx.ashley.mapperFor

class TargetFinderComponent : Component, DebugRenderable {
    companion object {
        val mapper = mapperFor<TargetFinderComponent>()
    }

    var target: Vector2? = null

    override fun debugRender(entity: Entity, shapeRenderer: ShapeRenderer) {
        if (target != null) {
            shapeRenderer.setColor(Color.CYAN)
            shapeRenderer.point(target!!.x, target!!.y, 0f)
        }
    }
}

fun Entity.targetFinderComponent(): TargetFinderComponent? = TargetFinderComponent.mapper.get(this)