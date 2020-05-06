package com.mygdx.game.ecs.systems

import com.badlogic.ashley.systems.IntervalSystem
import com.badlogic.gdx.math.MathUtils
import com.mygdx.game.Assets
import com.mygdx.game.ecs.Units2
import com.mygdx.game.ecs.components.transformComponent
import com.mygdx.game.lib.Vec2

class SpawnerSystem(private val assets: Assets) : IntervalSystem(0.5f) {
    override fun updateInterval() {
        val zombie = Units2.createZombie(engine, assets)

        val randomPos = Vec2(MathUtils.random(235).toFloat(), MathUtils.random(140).toFloat())

        zombie.transformComponent()!!.position = randomPos
    }
}