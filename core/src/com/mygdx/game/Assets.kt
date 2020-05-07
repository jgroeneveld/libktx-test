package com.mygdx.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.Logger
import com.mygdx.game.lib.aseprite.Aseprite
import com.mygdx.game.lib.aseprite.AsepriteJson
import com.mygdx.game.lib.aseprite.AsepriteJsonLoader
import com.mygdx.game.lib.aseprite.AsepriteLoader
import com.mygdx.game.lib.load

class Assets() {
    companion object {
        val SHADOW_PNG = "shadow.png"
        val ZOMBIE1_WALK = "zombie1-walk"
        val COMMAND = "command"
    }

    val assetManager = configuredAssetManager()

    fun loadAll(): Assets {
        assetManager.apply {
            load<Texture>(SHADOW_PNG)
            load<Aseprite>(ZOMBIE1_WALK)
            load<Aseprite>(COMMAND)
        }

        return this
    }

    inline operator fun <reified T> get(filename: String): T {
        return assetManager.get(filename, T::class.java)
    }

    fun finishLoading() = assetManager.finishLoading()

    private fun configuredAssetManager(): AssetManager {
        return AssetManager().apply {
            logger.level = Logger.ERROR

            setLoader(AsepriteJson::class.java, AsepriteJsonLoader(InternalFileHandleResolver()))
            setLoader(Aseprite::class.java, AsepriteLoader(InternalFileHandleResolver()))
        }
    }

    fun dispose() {
        assetManager.dispose()
    }
}