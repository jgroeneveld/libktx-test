package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import ktx.ashley.mapperFor


class SoftCollisionComponent() : Component {
    companion object {
        val mapper = mapperFor<SoftCollisionComponent>()
    }

    //separationStrength defines how strong the force is to separate entities.
    var separationStrength: Float = 0.1f
}

fun Entity.softCollisionComponent(): SoftCollisionComponent? = SoftCollisionComponent.mapper.get(this)
