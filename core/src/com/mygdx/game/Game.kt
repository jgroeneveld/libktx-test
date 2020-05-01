package com.mygdx.game

import ktx.app.KtxGame
import ktx.app.KtxScreen

class Game : KtxGame<KtxScreen>() {
    @Override
    override fun create() {
        addScreen(MyGameScene(this))

        setScreen<MyGameScene>()

        super.create()
    }

    @Override
    override fun dispose() {
        super.dispose()
    }
}

