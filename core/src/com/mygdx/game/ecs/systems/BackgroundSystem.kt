package com.mygdx.game.ecs.systems

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.mygdx.game.COLOR_1
import ktx.graphics.use

class BackgroundSystem(
        private val camera: Camera
) : EntitySystem() {
    private val shapeRenderer = ShapeRenderer()

    override fun update(deltaTime: Float) {
        shapeRenderer.projectionMatrix = camera.combined
        shapeRenderer.use(ShapeRenderer.ShapeType.Filled) {
            it.color = COLOR_1
            it.rect(0f, 0f, 240f, 135f)
        }
    }
}