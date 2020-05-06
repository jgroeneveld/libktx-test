package com.mygdx.game.ecs.systems

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport
import com.mygdx.game.ecs.base.UpdatingSystem
import com.mygdx.game.ecs.components.TargetFinderComponent
import com.mygdx.game.ecs.components.targetFinderComponent
import com.mygdx.game.lib.Vec2
import com.mygdx.game.lib.getMousePosition
import com.mygdx.game.lib.getMouseWorldPosition
import com.mygdx.game.lib.ashleyext.allOf
import ktx.graphics.use

class MouseTargetSystem(val viewport: Viewport) : UpdatingSystem(
        allOf(TargetFinderComponent::class).get()
) {
    val crosshair = Texture("crosshair-9x9.png")
    val spriteBatch = SpriteBatch()

    override fun update(deltaTime: Float) {
        val mousePosition = viewport.getMouseWorldPosition()

        // TODO: this has to be done to get the mouse even if it just was tapped
        if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT) || Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            renderMouseCursor(mousePosition, 1)

            entities.forEach {
                it.targetFinderComponent()!!.target = mousePosition
            }
        } else {
            renderMouseCursor(mousePosition, 0)
        }
    }

    private fun renderMouseCursor(mousePosition: Vec2, tile: Int) {
        // TODO: recalculate because window size changes invalidates this. there is surely a better way
        spriteBatch.use(viewport.camera.combined) {
            val size = 9;
            it.draw(crosshair, mousePosition.x - size/2, mousePosition.y - size/2, 0f, 0f, size.toFloat(), size.toFloat(), 1f, 1f, 0f, tile*size, 0, size, size, false, false)
        }
    }
}