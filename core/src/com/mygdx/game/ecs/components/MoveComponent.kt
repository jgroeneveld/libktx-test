package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Vector2
import ktx.ashley.mapperFor

class MoveComponent : Component {
    companion object {
        val mapper = mapperFor<MoveComponent>()
    }

    val velocity = Vector2()
}