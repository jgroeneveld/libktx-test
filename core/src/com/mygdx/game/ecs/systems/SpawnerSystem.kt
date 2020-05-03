package com.mygdx.game.ecs.systems

import com.badlogic.ashley.systems.IntervalSystem
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.ecs.components.*
import ktx.ashley.entity
import ktx.ashley.with
import ktx.log.debug
import ktx.math.vec2

class SpawnerSystem: IntervalSystem(1f) {
    val zombie by lazy { Texture("zombie1-walk.png") }

    override fun updateInterval() {
        engine.entity {
            with<TransformComponent> {
                position.set(MathUtils.random(235).toFloat(), MathUtils.random(140).toFloat())
            }

            with<SpriteComponent> {
                sprite = com.badlogic.gdx.graphics.g2d.Sprite(zombie, 0, 0, 10, 10)
                offset = Vector2(-sprite.width/2, -sprite.height/2 + 2)
            }

            with<MoveComponent>()

            with<ColliderComponent> {
                radius = 2f
            }

            with<TargetFinderComponent> {
                target = vec2(MathUtils.random(235).toFloat(), MathUtils.random(140).toFloat())
            }
        }
    }

}