package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.mygdx.game.lib.Vec2
import com.mygdx.game.lib.aseprite.Aseprite
import com.mygdx.game.lib.ashleyext.mapperFor
import ktx.log.debug

open class AnimatedSprite(
        private val animations: Aseprite,
        var offset: Vec2 = Vec2.Zero
) : Component {

    val animationIsFinished: Boolean
        get() = currentAnimation.isAnimationFinished(stateTime)

    private var currentAnimationName = animations.animationNames().first()
    private var stateTime: Float = 0f

    private val currentAnimation
        get() = animations[currentAnimationName]

    fun play(animationName: String, restart: Boolean = true) {
        currentAnimationName = animationName
        if (restart) stateTime = 0f
    }

    fun isPlaying(animationName: String) = currentAnimationName == animationName

    fun getCurrentFrame(): TextureRegion {
        return currentAnimation.getKeyFrame(stateTime)
    }

    fun center(deltaX: Int = 0, deltaY: Int = 0) {
        val frame = animations.frame(0)
        offset = Vec2(-frame.regionWidth / 2f + deltaX, -frame.regionHeight / 2f + deltaY)
    }

    fun update(deltaTime: Float) {
        stateTime += deltaTime
    }

    companion object {
        val mapper = mapperFor<AnimatedSprite>()
    }
}

fun Entity.animatedSprite(): AnimatedSprite? = AnimatedSprite.mapper.get(this)
