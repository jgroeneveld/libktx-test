package com.mygdx.game.lib

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.viewport.Viewport
import ktx.collections.GdxArray
import ktx.collections.toGdxArray

fun Viewport.getMouseWorldPosition(): Vector2 {
    // use this function instead of re-using the mouseposition vector. unproject actually changes it
    val coords = this.unproject(Vector2(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()))
    return Vector2(coords.x, coords.y)
}

fun getMousePosition(): Vector2 {
    return Vector2(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
}

inline fun <reified Type : Any> Array<Type?>.toGdxArray(
        ordered: Boolean = true, initialCapacity: Int = this.size
): GdxArray<Type?> {
    val array = GdxArray<Type?>(ordered, initialCapacity, Type::class.java)
    array.addAll(this, 0, this.size)
    return array
}