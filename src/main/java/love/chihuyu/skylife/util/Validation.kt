package love.chihuyu.skylife.util

fun <T : Any?> T.require(block: T.(T) -> Boolean, lazyMessage: () -> String): T {
    require(block(this, this), lazyMessage)
    return this
}
fun <T : Any?> T.check(block: T.(T) -> Boolean, lazyMessage: () -> String): T {
    check(block(this, this), lazyMessage)
    return this
}
fun <T : Any?> T.assert(block: T.(T) -> Boolean, lazyMessage: () -> String): T {
    assert(block(this, this), lazyMessage)
    return this
}
