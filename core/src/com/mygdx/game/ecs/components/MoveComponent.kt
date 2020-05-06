package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.mygdx.game.lib.Vec2
import com.mygdx.game.lib.ashleyext.mapperFor

class MoveComponent(
        val acceleration: Float = 500f,
        val friction: Float = 500f,
        val maxSpeed: Float = 40f
) : Component {
    var direction = Vec2.Zero
    var velocity = Vec2.Zero

    companion object {
        val mapper = mapperFor<MoveComponent>()
    }
}

fun Entity.moveComponent(): MoveComponent? = MoveComponent.mapper.get(this)
