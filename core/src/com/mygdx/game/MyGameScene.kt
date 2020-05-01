package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.ecs.components.MoveComponent
import com.mygdx.game.ecs.components.SpriteComponent
import com.mygdx.game.ecs.components.TransformComponent
import com.mygdx.game.ecs.systems.MoveSystem
import com.mygdx.game.ecs.systems.RenderSystem
import com.mygdx.game.lib.Scene
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.ashley.entity
import ktx.ashley.with
import ktx.math.times

open class MyGameScene(game: KtxGame<KtxScreen>) : Scene(game) {
    val img by lazy { Texture("badlogic.jpg") }
    val zombie by lazy { Texture("zombie1-walk.png") }

    override fun show() {
        super.show()
        camera.position.set(120f, 70f, 1f)

        engine.addSystem(RenderSystem(batch, camera))
        engine.addSystem(MoveSystem())
        engine.addSystem(CameraMovementSystem(camera))

        engine.entity {
            with<TransformComponent> { position.set(0f, 0f); scale.set(2f, 2f) }
            with<SpriteComponent> { sprite = com.badlogic.gdx.graphics.g2d.Sprite(img) }
        }

        repeat(10) {
            val dir = Vector2(MathUtils.random(-10, 10).toFloat(), MathUtils.random(-10, 10).toFloat()).nor()
            val speed = MathUtils.random(20f, 80f)

            engine.entity {
                with<TransformComponent> { position.set(MathUtils.random(800f), MathUtils.random(500f)) }
                with<SpriteComponent> { sprite = com.badlogic.gdx.graphics.g2d.Sprite(zombie, 0, 0, 10, 10) }
                with<MoveComponent> { velocity.set(dir * speed) }
            }
        }
    }

    override fun update(delta: Float) {
        super.update(delta)

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
        }

    }

    override fun dispose() {
        super.dispose()

        img.dispose()
    }
}

