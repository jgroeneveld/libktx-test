package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.game.lib.Vec2
import com.mygdx.game.lib.ashleyext.mapperFor

class ShadowRenderer(
        var sprite: Sprite,
        var offset: Vec2 = Vec2.Zero
) : Component {

    fun center(deltaX: Int, deltaY: Int) {
        offset = Vec2(-sprite.width / 2f + deltaX, -sprite.height / 2f + deltaY)
    }

    companion object {
        val mapper = mapperFor<ShadowRenderer>()
    }
}

fun Entity.shadowRenderer(): ShadowRenderer? = ShadowRenderer.mapper.get(this)