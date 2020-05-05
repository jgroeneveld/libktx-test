package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Stage
import com.mygdx.game.ecs.systems.*
import com.mygdx.game.lib.Scene
import ktx.app.KtxGame
import ktx.app.KtxScreen

open class MyGameScene(game: KtxGame<KtxScreen>) : Scene(game) {
    val img by lazy { Texture("badlogic.jpg") }
    val zombie by lazy { Texture("zombie1-walk.png") }
    val stage = Stage()

    override fun show() {
        super.show()

        camera.position.set(240f / 2, 135f / 2, 0f)

        engine.addSystem(BackgroundSystem(camera))

        engine.addSystem(ShadowRenderSystem(batch, camera))
        engine.addSystem(SpriteRenderSystem(batch, camera))
        engine.addSystem(AnimatedSpriteRenderSystem(batch, camera))

        engine.addSystem(MoveAndCollideSystem())
        engine.addSystem(CameraMovementSystem(camera))
        engine.addSystem(MoveToTargetSystem())
        engine.addSystem(SpawnerSystem())

        engine.addSystem(DebugRenderSystem(camera))

        engine.addSystem(MouseTargetSystem(viewport))
    }

    override fun render(delta: Float) {
        super.render(delta)

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
        }

        stage.act()
        stage.draw()
    }

    override fun dispose() {
        super.dispose()

        img.dispose()
    }
}

