package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import ktx.app.KtxGame
import ktx.app.KtxScreen

val COLOR_1 = Color(224 / 255f, 200 / 255f, 171 / 255f, 1f) // E0C8AB
val COLOR_2 = Color(211 / 255f, 190 / 255f, 150 / 255f, 1f) // D3BE96
val COLOR_3 = Color(164 / 255f, 82 / 255f, 39 / 255f, 1f) // A45227
val COLOR_4 = Color(39 / 255f, 32 / 255f, 19 / 255f, 1f) // 272013
val COLOR_5 = Color(161 / 255f, 135 / 255f, 80 / 255f, 1f) // A18750

class Game : KtxGame<KtxScreen>() {


    @Override
    override fun create() {
        super.create()

        Gdx.input.isCursorCatched = true

        addScreen(MyGameScene(this))
        setScreen<MyGameScene>()

//        addScreen(MyGameSceneBoxd(this))
//        setScreen<MyGameSceneBoxd>()
    }

    @Override
    override fun dispose() {
        super.dispose()
    }
}

