package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.lib.ashleyext.mapperFor

class MoveComponent : Component {
    var acceleration = 500f
    var friction = 500f
    var maxSpeed = 40f
    var direction: Vector2 = Vector2()

    val velocity = Vector2()

    companion object {
        val mapper = mapperFor<MoveComponent>()
    }
}

fun Entity.moveComponent(): MoveComponent? = MoveComponent.mapper.get(this)
