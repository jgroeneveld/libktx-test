package com.mygdx.game.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.mygdx.game.lib.ashleyext.mapperFor

enum class Direction {
    NONE, WEST, EAST, NORTH, SOUTH
}

open class DirectionalAnimationPlayer(
        private val none: String,
        private val west: String,
        private val east: String,
        private val north: String,
        private val south: String
) : Component {
    operator fun get(direction: Direction): String {
        return when(direction) {
            Direction.NONE -> none
            Direction.WEST -> west
            Direction.EAST -> east
            Direction.NORTH -> north
            Direction.SOUTH -> south
        }
    }

    companion object {
        val mapper = mapperFor<DirectionalAnimationPlayer>()
    }
}

fun Entity.directionalAnimationPlayer(): DirectionalAnimationPlayer? = DirectionalAnimationPlayer.mapper.get(this)