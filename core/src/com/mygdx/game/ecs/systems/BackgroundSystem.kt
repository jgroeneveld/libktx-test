package com.mygdx.game.ecs.systems

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import ktx.graphics.use

class BackgroundSystem(val camera: Camera): EntitySystem() {
    val shapeRenderer = ShapeRenderer()

    override fun update(deltaTime: Float) {
        super.update(deltaTime)

        shapeRenderer.projectionMatrix = camera.combined
        shapeRenderer.use(ShapeRenderer.ShapeType.Filled) {
            it.setColor(Color.GREEN)
            it.rect(0f, 0f, 240f, 135f)
        }
    }
}