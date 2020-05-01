package com.mygdx.game

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import ktx.graphics.lerpTo
import ktx.math.minus
import ktx.math.times

class CameraMovementSystem(val camera: Camera) : EntitySystem() {
    var cameraTarget: Vector2? = null

    override fun update(deltaTime: Float) {
        super.update(deltaTime)

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            cameraTarget = Vector2(MathUtils.random(0f, 200f), MathUtils.random(0f, 200f))
        }

        val step = Vector2(240f, 135f)
        val offset = Vector2(-150f, -80f)

        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            cameraTarget = step * Vector2(0f, 2f) - offset
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            cameraTarget = step * Vector2(1f, 2f) - offset
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            cameraTarget = step * Vector2(2f, 2f) - offset
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            cameraTarget = step * Vector2(0f, 1f) - offset
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            cameraTarget = step * Vector2(1f, 1f) - offset
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            cameraTarget = step * Vector2(2f, 1f) - offset
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            cameraTarget = step * Vector2(0f, 0f) - offset
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            cameraTarget = step * Vector2(1f, 0f) - offset
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            cameraTarget = step * Vector2(2f, 0f) - offset
        }

        if (cameraTarget != null) {
            camera.lerpTo(cameraTarget!!, 0.2f)
        }
    }
}