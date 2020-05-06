package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.lib.aseprite.Aseprite
import com.mygdx.game.lib.ashleyext.mapperFor

open class AnimatedSprite(
        val animations: Aseprite,
        var offset: Vector2 = Vector2()
) : Component {

    fun play(animationName: String, restart: Boolean = true) {
        currentAnimationName = animationName
        if (restart) stateTime = 0f
    }

    fun getCurrentFrame(): TextureRegion {
        return getCurrentAnimation().getKeyFrame(stateTime, true)
    }

    fun getCurrentAnimation(): Animation<TextureRegion> {
        return animations.get(currentAnimationName)
    }

    fun centerOffset(deltaX: Int, deltaY: Int) {
        val frame = animations.frame(0)
        offset = Vector2(frame.regionWidth / 2f + deltaX, -frame.regionHeight / 2f + deltaY)
    }

    fun update(deltaTime: Float) {
        stateTime += deltaTime
    }

    private var currentAnimationName = ""
    private var stateTime: Float = 0f

    companion object {
        val mapper = mapperFor<AnimatedSprite>()
    }
}

fun Entity.animatedSprite(): AnimatedSprite? = AnimatedSprite.mapper.get(this)
