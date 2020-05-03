package com.mygdx.game.lib

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.viewport.Viewport

fun Viewport.getMousePosition(): Vector2 {
    val coords = this.unproject(Vector2(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()))
    return Vector2(coords.x, coords.y)
}