/*
 * Copyright (c) 2018-2021 AnimatedLEDStrip
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package animatedledstrip.animations.predefined

import animatedledstrip.animations.Animation
import animatedledstrip.animations.AnimationParameter
import animatedledstrip.animations.DefinedAnimation
import animatedledstrip.animations.Dimensionality
import animatedledstrip.colors.PreparedColorContainer
import animatedledstrip.colors.shuffledWithIndices
import animatedledstrip.leds.animationmanagement.numLEDs
import animatedledstrip.leds.colormanagement.setPixelProlongedColor
import animatedledstrip.leds.colormanagement.setStripProlongedColor
import kotlinx.coroutines.delay

val bubbleSort = DefinedAnimation(
    Animation.AnimationInfo(
        name = "Bubble Sort",
        abbr = "BST",
        description = "Visualization of bubble sort.\n" +
                      "`pCols[0]` is randomized, then bubble sort is used to resort it.",
        runCountDefault = 1,
        minimumColors = 1,
        unlimitedColors = false,
        dimensionality = Dimensionality.oneDimensional,
        intParams = listOf(AnimationParameter("interMovementDelay", "Delay between sorting movements", 5)),
    )
) { leds, params, _ ->
    val colorMap = params.colors[0].shuffledWithIndices().toMutableList()
    val color = PreparedColorContainer(colorMap.map { it.second })
    val interMovementDelay = params.intParams.getValue("interMovementDelay").toLong()

    leds.apply {
        setStripProlongedColor(color)

        var keepSearching = true

        while (keepSearching) {
            keepSearching = false
            for (pixel in 0 until numLEDs - 1) {
                if (colorMap[pixel].first > colorMap[pixel + 1].first) {
                    keepSearching = true
                    val temp = colorMap[pixel]
                    colorMap[pixel] = colorMap[pixel + 1]
                    setPixelProlongedColor(pixel, colorMap[pixel].second)
                    colorMap[pixel + 1] = temp
                    setPixelProlongedColor(pixel + 1, colorMap[pixel + 1].second)
                }
                delay(interMovementDelay)
            }
        }
    }
}
