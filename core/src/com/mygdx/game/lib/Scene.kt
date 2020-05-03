package com.mygdx.game.lib

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.log.debug

open class Scene(val game: KtxGame<KtxScreen>) : KtxScreen {
    protected val batch by lazy { SpriteBatch() }
    val camera = OrthographicCamera(240f, 135f)
    val viewport: Viewport = FitViewport(240f, 135f, camera)
    val engine = PooledEngine()

    override fun resize(width: Int, height: Int) {
        super.resize(width, height)

        viewport.update(width, height)
    }

    override fun show() {
        super.show()

        debug { "SHOW" }
    }

    override fun render(delta: Float) {
        engine.update(delta)
        camera.update()
    }

    override fun hide() {
        super.hide()

        debug { "HIDE" }

        camera.position.set(0f, 0f, 0f)

        engine.removeAllEntities()
        engine.clearPools()
    }

    override fun dispose() {
        super.dispose()

        batch.dispose()
    }
}
