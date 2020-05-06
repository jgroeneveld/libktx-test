package com.mygdx.game.ecs

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.MathUtils
import com.mygdx.game.Assets
import com.mygdx.game.ecs.components.*
import com.mygdx.game.lib.Vec2
import com.mygdx.game.lib.aseprite.Aseprite
import com.mygdx.game.lib.ashleyext.entity
import com.mygdx.game.lib.ashleyext.with

object Units2 {
    fun createZombie(engine: Engine, assets: Assets): Entity {
        val shadowImage: Texture = assets.get(Assets.SHADOW_PNG)
        val zombieAnimations: Aseprite = assets.get(Assets.ZOMBIE1_WALK)

        return engine.entity {
            with(TransformComponent())
            with(MoveComponent())
            with(ColliderComponent(radius = 3f))
            with(SoftCollisionComponent(separationStrength = 0.35f))
            with(TargetFinderComponent()) {
                target = Vec2(MathUtils.random(235).toFloat(), MathUtils.random(140).toFloat())
            }

            with(ShadowRenderer(Sprite(shadowImage, 0, 0, 10, 10))) {
                centerOffset(0, 2)
            }

            with(AnimatedSprite(zombieAnimations)) {
                centerOffset(0, 2)
                play("WalkDown")
            }
        }
    }
}