package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.ecs.systems.*
import com.mygdx.game.lib.Scene
import ktx.app.KtxGame
import ktx.app.KtxScreen

open class MyGameScene(game: KtxGame<KtxScreen>) : Scene(game) {
    val img by lazy { Texture("badlogic.jpg") }
    val zombie by lazy { Texture("zombie1-walk.png") }

    override fun show() {
        super.show()

        camera.position.set(120f, 70f, 1f)
        engine.addSystem(SpriteRenderSystem(batch, camera))
        engine.addSystem(DebugRenderSystem(camera))
        engine.addSystem(MoveAndCollideSystem())
        engine.addSystem(CameraMovementSystem(camera))
        engine.addSystem(MoveToTargetSystem())
        engine.addSystem(SpawnerSystem())

//        engine.entity {
//            with<TransformComponent> { position.set(0f, 0f); scale.set(2f, 2f) }
//            with<SpriteComponent> { sprite = com.badlogic.gdx.graphics.g2d.Sprite(img) }
//        }

        repeat(0) {

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

