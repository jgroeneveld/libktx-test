package com.mygdx.game.ecs.systems

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport
import com.mygdx.game.ecs.base.UpdatingSystem
import com.mygdx.game.ecs.components.TargetFinderComponent
import com.mygdx.game.ecs.components.targetFinderComponent
import com.mygdx.game.lib.getMousePosition
import ktx.ashley.allOf
import ktx.graphics.use

class MouseTargetSystem(val viewport: Viewport) : UpdatingSystem(
        allOf(TargetFinderComponent::class).get()
) {
    val shapeRenderer = ShapeRenderer()

    override fun update(deltaTime: Float) {
        // TODO: this has to be done to get the mouse even if it just was tapped
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) || Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            val mousePosition = viewport.getMousePosition()
            renderMousePosition(mousePosition)

            entities.forEach {
                it.targetFinderComponent()!!.target = mousePosition
            }
        }
    }

    private fun renderMousePosition(mousePosition: Vector2) {
        shapeRenderer.projectionMatrix = viewport.camera.combined
        shapeRenderer.use(ShapeRenderer.ShapeType.Filled) {
            it.color = Color.CORAL
            it.circle(mousePosition.x, mousePosition.y, 2f)
        }
    }
}