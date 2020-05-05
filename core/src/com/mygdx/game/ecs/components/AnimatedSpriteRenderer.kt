package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import ktx.ashley.mapperFor

open class AnimatedSpriteRenderer : Component {
    companion object {
        val mapper = mapperFor<AnimatedSpriteRenderer>()
    }

    lateinit var animation: Animation<TextureRegion>
    var offset: Vector2 = Vector2(0f, 0f)
    var z: Int = 0

    var stateTime: Float = 0f
}

fun Entity.animatedSpriteRenderer(): AnimatedSpriteRenderer? = AnimatedSpriteRenderer.mapper.get(this)