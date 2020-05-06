package com.mygdx.game.lib

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.viewport.Viewport
import ktx.collections.GdxArray
import ktx.collections.toGdxArray
import ktx.math.minus

fun Vector2.towards(target: Vector2, maxDistanceDelta: Float): Vector2 {
    val a = target - this
    val magnitude: Float = a.len()

    if (magnitude <= maxDistanceDelta || magnitude == 0f) {
        return target
    }

    return Vector2(
            this.x + a.x / magnitude * maxDistanceDelta,
            this.y + a.y / magnitude * maxDistanceDelta
    )
}

fun Viewport.getMouseWorldPosition(): Vec2 {
    // use this function instead of re-using the mouseposition vector. unproject actually changes it
    val coords = this.unproject(Vector2(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()))
    return Vec2(coords.x, coords.y)
}

fun getMousePosition(): Vec2 {
    return Vec2(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
}

inline fun <reified Type : Any> Array<Type?>.toGdxArray(
        ordered: Boolean = true, initialCapacity: Int = this.size
): GdxArray<Type?> {
    val array = GdxArray<Type?>(ordered, initialCapacity, Type::class.java)
    array.addAll(this, 0, this.size)
    return array
}

inline operator fun <reified T> AssetManager.get(filename: String): T {
    return this.get(filename, T::class.java)
}

inline fun <reified T> AssetManager.load(filename: String) {
    this.load(filename, T::class.java)
}