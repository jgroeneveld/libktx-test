package com.mygdx.game.lib.ashleyext

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity

// My own modified version of Ktx.Ashley with an improved syntax allowing to use constructors and private variables..

inline fun Engine.entity(configure: EngineEntity.() -> Unit = {}): Entity {
    val entity = createEntity()
    EngineEntity(this, entity).configure()
    addEntity(entity)
    return entity
}

class EngineEntity(
        val engine: Engine,
        val entity: Entity)

inline fun <reified T : Component> EngineEntity.with(component: T, configure: (T).() -> Unit = {}): T {
    component.configure()
    entity.add(component)
    return component
}

// with pooled uses the engine.createComponent to allow for pooling. This removes the possibility for constructors.
// The component must implement Poolable.
inline fun <reified T : Component> EngineEntity.withPooled(configure: (T).() -> Unit = {}): T {
    val cmp = engine.createComponent(T::class.java)
            ?: throw NullPointerException("The component of ${T::class.java} type is null.")
    cmp.configure()
    entity.add(cmp)
    return cmp
}