package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.mygdx.game.lib.aseprite.Aseprite
import com.mygdx.game.lib.ashleyext.mapperFor

open class DirectionalAnimatedSpriteRenderer : Component {
    lateinit var animations: Aseprite
    var stateTime: Float = 0f

    companion object {
        val mapper = mapperFor<DirectionalAnimatedSpriteRenderer>()
    }
}

fun Entity.directionalAnimatedSpriteRenderer(): DirectionalAnimatedSpriteRenderer? = DirectionalAnimatedSpriteRenderer.mapper.get(this)