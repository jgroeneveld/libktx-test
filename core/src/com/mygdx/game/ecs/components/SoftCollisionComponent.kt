package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.mygdx.game.lib.ashleyext.mapperFor


class SoftCollisionComponent(
        var separationStrength: Float = 0.5f
) : Component {
    companion object {
        val mapper = mapperFor<SoftCollisionComponent>()
    }
}

fun Entity.softCollisionComponent(): SoftCollisionComponent? = SoftCollisionComponent.mapper.get(this)
