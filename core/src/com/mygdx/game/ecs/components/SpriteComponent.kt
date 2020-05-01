package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.Sprite
import ktx.ashley.mapperFor

class SpriteComponent : Component {
    companion object {
        val mapper = mapperFor<SpriteComponent>()
    }

    lateinit var sprite: Sprite
    var z: Int = 0
}