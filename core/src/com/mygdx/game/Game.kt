package com.mygdx.game

import ktx.app.KtxGame
import ktx.app.KtxScreen

class Game : KtxGame<KtxScreen>() {
    @Override
    override fun create() {
        super.create()

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

