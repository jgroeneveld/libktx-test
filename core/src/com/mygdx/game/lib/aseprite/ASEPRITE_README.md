# Aseprite Integration

based on https://github.com/dwursteisen/libgdx-addons/tree/master/aseprite-addons

Can load animations based on tags including speed and direction (forward, backward, pingpong).
It supports non-repeating (Normal) animations by adding _nr as the name suffix. Otherwise they will loop.

```kotlin
val assets = AssetManager()

assets.setLoader(AsepriteJson::class.java, AsepriteJsonLoader(InternalFileHandleResolver()))
assets.setLoader(Aseprite::class.java, AsepriteLoader(InternalFileHandleResolver()))

// will load player.png and player.json
assets.load("player", Aseprite::class.java)
  
// ...

val player: Aseprite = assets.get("player", Aseprite::class.java)
// will get the jump animation
val jump = player["jump"]         
``` 
