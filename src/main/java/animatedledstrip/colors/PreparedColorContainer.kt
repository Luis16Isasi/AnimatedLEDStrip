package animatedledstrip.colors

import animatedledstrip.utils.base

/*
 *  Copyright (c) 2019 AnimatedLEDStrip
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


/**
 * A prepared [ColorContainer] that holds a set of colors that blend from one
 * to the next. This is created by calling the prepare() function on a
 * ColorContainer. Used by methods that set the color of pixel(s) on a strip.
 *
 * @property colors The List of colors that will be used to create this
 * PreparedColorContainer
 */
class PreparedColorContainer(val colors: List<Long>) : ColorContainerInterface {

    /**
     * Get the color in [colors] at the specified index.
     *
     * @param index The index of the color to retrieve
     */
    operator fun get(index: Int) = if (colors.indices.contains(index)) colors[index] else 0

    /**
     * @return 0
     */
    override val color: Long
        get() = 0

    /**
     * Create a string representation of this PreparedColorContainer.
     * The hexadecimal representation of each color in [colors] is
     * listed in comma delimited format, between brackets `[` & `]`
     * If there is only one color in this ColorContainer, the brackets
     * are dropped.
     */
    override fun toString(): String {
        var temp = "["
        for (c in colors) {
            temp += c base 16
            temp += ", "
        }
        temp = temp.removeSuffix(", ")
        temp += "]"
        return temp
    }

    /**
     * Checks if the specified value is in [colors].
     *
     * @param value The value to search for
     */
    operator fun contains(value: Long): Boolean = colors.contains(value)

    /**
     * @return This PreparedColorContainer instance
     */
    override fun prepare(numLEDs: Int, leadingZeros: Int): PreparedColorContainer = this

    /**
     * Returns the size of [colors].
     */
    val size: Int
        get() = colors.size

    /**
     * Creates a new ColorContainer instance with the colors in [colors].
     *
     * @return A new ColorContainer
     */
    override fun toColorContainer() = ColorContainer(colors)

}