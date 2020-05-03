package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import ktx.ashley.mapperFor

class SpriteComponent : Component {
    companion object {
        val mapper = mapperFor<SpriteComponent>()
    }

    lateinit var sprite: Sprite
    var offset: Vector2 = Vector2(0f, 0f)

    var z: Int = 0
}

fun Entity.spriteComponent(): SpriteComponent? = SpriteComponent.mapper.get(this)