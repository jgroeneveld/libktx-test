package com.mygdx.game.ecs.systems

import com.badlogic.ashley.systems.IntervalSystem
import com.badlogic.gdx.math.MathUtils
import com.mygdx.game.Assets
import com.mygdx.game.ecs.Units2
import com.mygdx.game.ecs.components.transformComponent

class SpawnerSystem(val assets: Assets) : IntervalSystem(1f) {
    override fun updateInterval() {
        val zombie = Units2.createZombie(engine, assets)
        zombie.transformComponent()!!.position.set(MathUtils.random(235).toFloat(), MathUtils.random(140).toFloat())
    }

}