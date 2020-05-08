package com.mygdx.game.ecs.systems

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Intersector
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.viewport.Viewport
import com.mygdx.game.ecs.base.FamilySystem
import com.mygdx.game.ecs.components.Selectable
import com.mygdx.game.ecs.components.TransformComponent
import com.mygdx.game.ecs.components.selectable
import com.mygdx.game.ecs.components.transformComponent
import com.mygdx.game.lib.Vec2
import com.mygdx.game.lib.ashleyext.allOf
import com.mygdx.game.lib.getMouseWorldPosition
import ktx.graphics.use
import ktx.log.debug
import kotlin.math.max
import kotlin.math.min

// family of all selectables?
// on click find selectable under click
// on area find all selectables under click
// at some point, we have to differentiate between group-selectables (units) and single selectables (buildings, enemy units?)
// should we separate between selecting, command etc? or one system

class SelectionSystem(
        private val viewport: Viewport
) : FamilySystem(
        allOf(TransformComponent::class, Selectable::class).get()
) {
    val rectColor = Color(0.3f, 0.3f, 0.3f, 0.3f)

    private val shapeRenderer = ShapeRenderer()

    private var state: State = State.IDLE
    private var draggingStartedAt: Vec2 = Vec2.Zero
    private var draggingLastAt: Vec2 = Vec2.Zero
    private var rect: Rectangle = Rectangle()

    override fun update(deltaTime: Float) {
        when (state) {
            State.IDLE -> idleState(deltaTime)
            State.DRAGGING -> draggingState(deltaTime)
        }
    }

    private fun idleState(deltaTime: Float) {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            state = State.DRAGGING
            draggingStartedAt = viewport.getMouseWorldPosition()
        }
    }

    private fun draggingState(deltaTime: Float) {
        draggingLastAt = viewport.getMouseWorldPosition()

        updateRectBetween(draggingStartedAt, draggingLastAt)
        drawRect()

        if (!Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            onStoppedDragging(deltaTime)
        }
    }

    private fun onStoppedDragging(deltaTime: Float) {
        state = State.IDLE

        debug { "selected rect $rect" }

        for (entity in entities) {
            val transform = entity.transformComponent()!!
            val selectable = entity.selectable()!!
            val bounds = selectable.globalBounds(transform.position)

            if (Intersector.overlaps(rect, bounds)) {
                debug { "found a unit" }
            }

        }
    }

    private fun drawRect() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.color = rectColor
        shapeRenderer.projectionMatrix = viewport.camera.combined

        shapeRenderer.use(ShapeRenderer.ShapeType.Filled) {
            it.rect(rect.x, rect.y, rect.width, rect.height)
        }

        Gdx.gl.glDisable(GL20.GL_BLEND)
    }

    enum class State {
        IDLE,
        DRAGGING
    }

    private fun updateRectBetween(a: Vec2, b: Vec2) {
        val left = min(a.x, b.x)
        val right = max(a.x, b.x)
        val top = min(a.y, b.y)
        val bottom = max(a.y, b.y)

        rect.set(left, top, right - left, bottom - top)
    }

}
