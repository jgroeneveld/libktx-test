package com.mygdx.game.ecs.systems

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Camera
import com.mygdx.game.ecs.base.UpdatingSystem
import com.mygdx.game.ecs.components.TargetFinderComponent
import com.mygdx.game.ecs.components.targetFinderComponent
import com.mygdx.game.lib.getMousePosition
import ktx.ashley.allOf

class MouseTargetSystem(val camera: Camera) : UpdatingSystem(
        allOf(TargetFinderComponent::class).get()
) {
    override fun update(deltaTime: Float) {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            for (entity in entities) {
                entity.targetFinderComponent()!!.target = camera.getMousePosition()
            }
        }
    }
}