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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Matrix4
import com.mygdx.game.ecs.components.TransformComponent
import ktx.ashley.allOf
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
    val shapeRenderer = ShapeRenderer()
    val font = BitmapFont()
    val spriteBatch = SpriteBatch()
    val hudFontSize = 15f
    val hudBgColor = Color(0f, 0f, 0f, 0.4f)
    val hudFontPadding = 2f
    val hudMatrix = Matrix4().setToOrtho2D(0f, 0f, camera.viewportWidth, camera.viewportHeight)

    var enabled = true

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
        // we have to be cautious with this to not get into the way of any spritebatch.
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.projectionMatrix = hudMatrix
        shapeRenderer.use(ShapeRenderer.ShapeType.Filled) {
            it.color = hudBgColor
            it.rect(0f, 0f, camera.viewportWidth, hudFontSize + hudFontPadding)
        }

        Gdx.gl.glDisable(GL20.GL_BLEND)

        spriteBatch.color = Color.WHITE
        spriteBatch.use(hudMatrix) {
            font.draw(it, "Entities total: ${engine.entities.size()}", hudFontPadding * 2, hudFontSize)
        }
    }

    private fun togglePressed() =
            Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)
}