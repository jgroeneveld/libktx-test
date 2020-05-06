package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.game.lib.Vec2
import com.mygdx.game.lib.ashleyext.mapperFor

open class SpriteRenderer : Component {
    lateinit var sprite: Sprite
    var offset: Vec2 = Vec2.Zero

    companion object {
        val mapper = mapperFor<SpriteRenderer>()
    }
}

fun Entity.spriteRenderer(): SpriteRenderer? = SpriteRenderer.mapper.get(this)
