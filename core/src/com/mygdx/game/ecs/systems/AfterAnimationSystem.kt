package com.mygdx.game.ecs.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.mygdx.game.ecs.components.*
import com.mygdx.game.lib.ashleyext.allOf

class AfterAnimationSystem : IteratingSystem(
        allOf(AnimatedSprite::class, AfterAnimation::class).get()
) {
    override fun processEntity(entity: Entity, deltaTime: Float) {
        val animatedSprite = entity.animatedSprite()!!
        val afterAnimation = entity.afterAnimation()!!

        if (animatedSprite.animationIsFinished) {
            afterAnimation.callback(entity)
        }
    }
}