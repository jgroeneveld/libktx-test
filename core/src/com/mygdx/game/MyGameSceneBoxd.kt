package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.mygdx.game.ecs.systems.SpriteRenderSystem
import com.mygdx.game.lib.Scene
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.box2d.body
import ktx.box2d.box
import ktx.box2d.circle
import ktx.box2d.createWorld
import ktx.math.vec2


open class MyGameSceneBoxd(game: KtxGame<KtxScreen>) : Scene(game) {
    val img by lazy { Texture("badlogic.jpg") }
    val zombie by lazy { Texture("zombie1-walk.png") }

    var world = createWorld()
    var debugRenderer = Box2DDebugRenderer(true, false, true, true, true, true)

    // TODO: dispose?
    // TODO: grouping to not have thinks checked against where we dont care?
    var ground = world.body(BodyDef.BodyType.StaticBody) {
        box(width = 200.pixelsInMeters, height = 10.pixelsInMeters)
    }

    var obstacle = world.body() {
        position.set(50.pixelsInMeters, 40.pixelsInMeters)
        circle(radius = 10.pixelsInMeters)
    }

    var ball = world.body(BodyDef.BodyType.DynamicBody) {
        position.set(0.pixelsInMeters, 40.pixelsInMeters)
        linearDamping = 15f
        circle(radius = 5f.pixelsInMeters) {
//            density = 1f
//            friction = 2f
//            restitution = 0.6f
        }

    }

    override fun show() {
        super.show()
//        camera.position.set(120f, 70f, 1f)
        camera.position.set(0f, 0f, 0f)
        engine.addSystem(SpriteRenderSystem(batch, camera))
        engine.addSystem(CameraMovementSystem(camera))

//        engine.entity {
//            with<TransformComponent> { position.set(0f, 0f); scale.set(2f, 2f) }
//            with<SpriteComponent> { sprite = com.badlogic.gdx.graphics.g2d.Sprite(img) }
//        }
//
//        repeat(10) {
//            val dir = Vector2(MathUtils.random(-10, 10).toFloat(), MathUtils.random(-10, 10).toFloat()).nor()
//            val speed = MathUtils.random(20f, 80f)
//
//            engine.entity {
//                with<TransformComponent> { position.set(MathUtils.random(800f), MathUtils.random(500f)) }
//                with<SpriteComponent> { sprite = com.badlogic.gdx.graphics.g2d.Sprite(zombie, 0, 0, 10, 10) }
//                with<MoveComponent> { velocity.set(dir * speed) }
//            }
//        }


    }

    override fun render(delta: Float) {
        super.render(delta)

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
        }

        val pos = ball.position

        val dir = vec2()
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            dir.y -=1
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            dir.y +=1
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            dir.x +=1
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            dir.x -=1
        }
        if (!dir.isZero) {
            dir.nor()
            ball.applyLinearImpulse(dir.x * 20f.pixelsInMeters, dir.y * 20f.pixelsInMeters, pos.x, pos.y, true)
        }

        updatePhysics(delta)
    }

    var physicsAccumulator: Float = 0f;
    val timestep = 1f / 60f // recommended to be between 1/45 and 1/300
    val velocityIterations = 6
    val positionIterations = 2

    open fun updatePhysics(delta: Float) {
        debugRenderer.render(world, camera.combined.cpy().scale(1f * ppm, 1f * ppm, 1f))

        val frameTime = Math.min(delta, 0.25f)
        physicsAccumulator += frameTime


        while (physicsAccumulator >= timestep) {
            world.step(timestep, velocityIterations, positionIterations)
            physicsAccumulator -= timestep
        }
    }

    override fun dispose() {
        super.dispose()

        img.dispose()
    }
}

val Int.pixelsInMeters: Float
    inline get() {
        return this / ppm
    }
val Float.pixelsInMeters: Float
    inline get() {
        return this / ppm
    }

val ppm = 100f