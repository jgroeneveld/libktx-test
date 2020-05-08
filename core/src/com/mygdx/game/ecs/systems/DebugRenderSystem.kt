package com.mygdx.game.ecs.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Matrix4
import com.mygdx.game.ecs.components.TransformComponent
import com.mygdx.game.lib.ashleyext.allOf
import ktx.graphics.use
import ktx.log.debug

interface DebugRenderable {
    fun debugRender(entity: Entity, shapeRenderer: ShapeRenderer)
}

class DebugRenderSystem(
        val camera: OrthographicCamera
) : IteratingSystem(
        allOf(TransformComponent::class).get()
) {
    // load lazy whats possible because its debug

    val shapeRenderer by lazy { ShapeRenderer() }
    val font: BitmapFont by lazy { generateBitmapFont(hudFontSize) }
    val spriteBatch by lazy { SpriteBatch() }

    val hudBgColor = Color(0f, 0f, 0f, 0.4f)
    val hudFontSize = 24
    val hudHeight = 32f
    val hudWidth by lazy { Gdx.graphics.width.toFloat() }
    val hudMatrix by lazy { Matrix4().setToOrtho2D(0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()) }

    fun generateBitmapFont(size: Int): BitmapFont {
        val generator = FreeTypeFontGenerator(Gdx.files.internal("PixelOperator.ttf"))
        val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
        parameter.size = size
        val bitmapFont = generator.generateFont(parameter)
        generator.dispose()

        return bitmapFont
    }

    var enabled = false

    override fun update(deltaTime: Float) {
        if (togglePressed()) {
            enabled = !enabled
            debug { "DebugRender is now ${if (enabled) "enabled" else "disabled"}" }
        }

        if (!enabled) return

        shapeRenderer.projectionMatrix = camera.combined
        shapeRenderer.use(ShapeRenderer.ShapeType.Line) {
            super.update(deltaTime)
        }

        renderHud()
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        for (component in entity.components) {
            if (component is DebugRenderable) {
                component.debugRender(entity, shapeRenderer)
            }
        }
    }

    private fun renderHud() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.projectionMatrix = hudMatrix
        shapeRenderer.use(ShapeRenderer.ShapeType.Filled) {
            it.color = hudBgColor
            it.rect(0f, 0f, hudWidth, hudHeight)
        }

        Gdx.gl.glDisable(GL20.GL_BLEND)

        spriteBatch.color = Color.WHITE
        spriteBatch.use(hudMatrix) {
            font.draw(it, "Entities total: ${engine.entities.size()}", 8f, hudFontSize.toFloat())
        }
    }

    private fun togglePressed() = Gdx.input.isKeyJustPressed(Input.Keys.GRAVE)
}