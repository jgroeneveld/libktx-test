package com.mygdx.game.desktop

import com.badlogic.gdx.Application
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.mygdx.game.Game

//object DesktopLauncher {
//    fun main(arg: Array<String?>?) {
//        val config = LwjglApplicationConfiguration()
//        LwjglApplication(Game(), config)
//    }
//}

fun main() {
    val config = LwjglApplicationConfiguration()
    config.width = 240*5
    config.height = 135*5
    LwjglApplication(Game(), config).logLevel = Application.LOG_DEBUG
//    LwjglApplication(Game(), config).logLevel = Application.LOG_DEBUG
}