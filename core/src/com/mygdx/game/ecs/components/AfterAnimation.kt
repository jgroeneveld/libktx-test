package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.mygdx.game.lib.ashleyext.mapperFor

typealias EntityCallback = (Entity) -> Unit

class AfterAnimation(val callback: EntityCallback) : Component {
    companion object {
        val mapper = mapperFor<AfterAnimation>()
    }
}

fun Entity.afterAnimation(): AfterAnimation? = AfterAnimation.mapper.get(this)
