package com.mygdx.game.ecs.systems

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.Viewport
import com.mygdx.game.Assets
import com.mygdx.game.ecs.Units2
import com.mygdx.game.ecs.base.FamilySystem
import com.mygdx.game.ecs.components.TargetFinderComponent
import com.mygdx.game.ecs.components.targetFinderComponent
import com.mygdx.game.lib.Vec2
import com.mygdx.game.lib.ashleyext.allOf
import com.mygdx.game.lib.getMouseWorldPosition
import ktx.graphics.use

class MouseTargetSystem(
        private val assets: Assets,
        private val viewport: Viewport
) : FamilySystem(
        allOf(TargetFinderComponent::class).get()
) {
    private val crosshair = Texture("crosshair-9x9.png") // TODO: put into assets
    private val spriteBatch = SpriteBatch()

    override fun update(deltaTime: Float) {
        val mousePosition = viewport.getMouseWorldPosition()

        renderMouseCursor(mousePosition, 0)

        if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
            Units2.createMoveCommand(engine, assets, mousePosition)

            entities.forEach {
                it.targetFinderComponent()!!.target = mousePosition
            }
        }
    }

    private fun renderMouseCursor(mousePosition: Vec2, tile: Int) {
        // TODO: recalculate because window size changes invalidates this. there is surely a better way
        spriteBatch.use(viewport.camera.combined) {
            val size = 9;
            it.draw(
                    crosshair,
                    mousePosition.x - size / 2,
                    mousePosition.y - size / 2,
                    0f, 0f,
                    size.toFloat(), size.toFloat(),
                    1f, 1f,
                    0f,
                    tile * size, 0, size, size,
                    false, false
            )
        }
    }
}