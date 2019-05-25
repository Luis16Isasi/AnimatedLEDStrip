package animatedledstrip.utils

/*
 *  Copyright (c) 2019 AnimatedLEDStrip
 *  Copyright (c) 2013 FastLED
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */


import org.pmw.tinylog.Logger

/**
 * Blend two [animatedledstrip.colors.ColorContainer]s together and return a new `ColorContainer`.
 *
 * Adapted from the FastLED library.
 *
 * @param existing The starting `ColorContainer`
 * @param overlay The `ColorContainer` to blend toward
 * @param amountOfOverlay The proportion (0-255) of `b` to blend
 */
fun blend(existing: Long, overlay: Long, amountOfOverlay: Int): Long {
    if (amountOfOverlay == 0) return existing

    if (amountOfOverlay == 255) return overlay

    if (existing == overlay) return existing

    val r = blend8(existing.r, overlay.r, amountOfOverlay)
    val g = blend8(existing.g, overlay.g, amountOfOverlay)
    val b = blend8(existing.b, overlay.b, amountOfOverlay)

    return ((r shl 16) or (g shl 8) or b).toLong()

}


/**
 * Try to pause the thread for an amount of time in milliseconds.
 *
 * @param wait The time (in milliseconds) to wait for
 */
fun delayBlocking(wait: Long) {
    try {
        Thread.sleep(wait)
    } catch (e: InterruptedException) {
    }
}

/**
 * Overload for delayBlocking for when an Int is sent.
 *
 * @param wait The time (in milliseconds) to wait for
 */
fun delayBlocking(wait: Int) {
    delayBlocking(wait.toLong())
}

/**
 * Returns a `Long` from a hexadecimal String.
 *
 * @param string The hex `String` to decode
 */
fun parseHex(string: String): Long {
    val s = string.removePrefix("0x")     // remove leading 0x if present
    return try {
        java.lang.Long.parseLong(s, 16)
    } catch (e: NumberFormatException) {
        Logger.warn("Format of string \"$string\" is malformed: $e")
        0x0
    }
}

/**
 * Returns the 'grayscale' version of the color.
 */
fun Long.grayscale(): Long {
    val avg = (((this shr 16 and 0xFF) + (this shr 8 and 0xFF) + (this and 0xFF)) / 3) base 16
    return parseHex("$avg$avg$avg")
}

/**
 * Returns the 'red' part of a color.
 */
val Long.r
    get() = (this shr 16 and 0xFF).toInt()

/**
 * Returns the 'green' part of a color.
 */
val Long.g
    get() = (this shr 8 and 0xFF).toInt()

/**
 * Returns the 'blue' part of a color.
 */
val Long.b
    get() = (this and 0xFF).toInt()

/**
 * Infix function for easily creating string representations of a Long in
 * different bases.
 *
 * @param b The base to use
 */
infix fun Long.base(b: Int) = this.toString(b)