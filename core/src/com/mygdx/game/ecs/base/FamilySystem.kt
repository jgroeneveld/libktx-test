package com.mygdx.game.ecs.base

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntitySystem
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.utils.ImmutableArray
import com.badlogic.gdx.utils.Array


// A system that has a familiy of entities but does not iterate them.
// They can be accessed in the update method
abstract class FamilySystem(
        private val family: Family,
        priority: Int = 0
) : EntitySystem(priority) {
    var entities: ImmutableArray<Entity> = ImmutableArray(Array())
        private set

    abstract override fun update(deltaTime: Float)

    override fun addedToEngine(engine: Engine) {
        entities = engine.getEntitiesFor(family)
    }

    override fun removedFromEngine(engine: Engine) {
        entities = ImmutableArray(Array())
    }
}