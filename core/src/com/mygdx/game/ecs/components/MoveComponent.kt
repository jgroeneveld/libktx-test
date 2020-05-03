package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.math.Vector2
import ktx.ashley.mapperFor

class MoveComponent : Component {
    companion object {
        val mapper = mapperFor<MoveComponent>()
    }

    var acceleration = 500f
    var friction = 500f
    var maxSpeed = 40f
    var direction: Vector2 = Vector2()

    val velocity = Vector2()
}

fun Entity.moveComponent(): MoveComponent? = MoveComponent.mapper.get(this)
