package com.mygdx.game.ecs.systems

import com.badlogic.ashley.systems.IntervalSystem
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.ecs.components.*
import ktx.ashley.entity
import ktx.ashley.with
import ktx.collections.toGdxArray
import ktx.math.vec2

fun loadFrames(sheet: Texture, cols: Int, rows: Int): List<TextureRegion> {
    val frames2d = TextureRegion.split(sheet, sheet.width / cols, sheet.height / rows)
    val frames1d = mutableListOf<TextureRegion>()

    var index = 0
    for (i in 0 until rows) {
        for (j in 0 until cols) {
            frames1d.add(frames2d[i][j])
        }
    }

    return frames1d
}

fun loadAnimation(sheetFrames: List<TextureRegion>, firstFrame: Int, numFrames: Int, fps: Int): Animation<TextureRegion> {
    require(firstFrame + numFrames < sheetFrames.size)

    val sl = sheetFrames.subList(firstFrame, firstFrame + numFrames)

    return Animation<TextureRegion>(1f / fps, sl.toGdxArray())
}

class SpawnerSystem : IntervalSystem(1f) {
    val zombieSpriteSheet by lazy { Texture("zombie1-walk.png") }
    val shadow by lazy { Texture("shadow.png") }
    val zombieFrames by lazy { loadFrames(zombieSpriteSheet, cols = 6, rows = 5) }

    val fps = 5

    override fun updateInterval() {
        engine.entity {
            with<TransformComponent> {
                position.set(MathUtils.random(235).toFloat(), MathUtils.random(140).toFloat())
            }

            with<ShadowRenderer> {
                sprite = com.badlogic.gdx.graphics.g2d.Sprite(shadow, 0, 0, 10, 10)
                offset = Vector2(-sprite.width / 2, -sprite.height / 2 + 2)
                z = 0
            }

//            with<SpriteComponent> {
//                sprite = com.badlogic.gdx.graphics.g2d.Sprite(zombieSpriteSheet, 0, 0, 10, 10)
//                offset = Vector2(-sprite.width/2, -sprite.height/2 + 2)
//                z = -1
//            }

            with<AnimatedSpriteRenderer> {
                val r = Math.random()
                val anim = if (r > 0.75) {
                    loadAnimation(zombieFrames, 0, 6, fps)// down
                } else if (r > 0.5) {
                    loadAnimation(zombieFrames, 6, 6, fps) // up
                } else if (r > 0.25) {
                    loadAnimation(zombieFrames, 12, 6, fps) // ??
                } else {
                    loadAnimation(zombieFrames, 18, 6, fps) // ??
                }
                animation = anim
                offset = Vector2(-anim.keyFrames.first().regionWidth / 2f, -anim.keyFrames.first().regionHeight / 2f + 2)
                z = -1
            }

            with<MoveComponent>()

            with<ColliderComponent> {
                radius = 2f
            }

            with<SoftCollisionComponent>()

            with<TargetFinderComponent> {
                target = vec2(MathUtils.random(235).toFloat(), MathUtils.random(140).toFloat())
            }
        }
    }

}