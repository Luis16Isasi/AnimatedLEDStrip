package animatedledstrip.test

import animatedledstrip.leds.Animation
import animatedledstrip.leds.AnimationData
import animatedledstrip.leds.EmulatedAnimatedLEDStripNonConcurrent
import org.junit.Test
import org.pmw.tinylog.Configurator
import org.pmw.tinylog.Level

class AnimatedLEDStripNonConcurrentTest {

    init {
        Configurator.defaultConfig().level(Level.OFF).activate()
    }

    @Test
    fun testColor() {
        val testLEDs = EmulatedAnimatedLEDStripNonConcurrent(50)

        testLEDs.run(AnimationData().animation(Animation.COLOR).color(0xFF))
        checkAllPixels(testLEDs, 0xFF)
    }

    @Test
    fun testBounceToColor() {
        val testLEDs = EmulatedAnimatedLEDStripNonConcurrent(50)

        testLEDs.run(AnimationData().animation(Animation.BOUNCETOCOLOR).color(0xFF))
        checkAllPixels(testLEDs, 0xFF)
    }

    @Test
    fun testMultiPixelRunToColor() {
        val testLEDs = EmulatedAnimatedLEDStripNonConcurrent(50)

        testLEDs.run(AnimationData().animation(Animation.MULTIPIXELRUNTOCOLOR).color(0xFF))
        checkAllPixels(testLEDs, 0xFF)
    }

    @Test
    fun testSparkleToColor() {
        val testLEDs = EmulatedAnimatedLEDStripNonConcurrent(50)

        testLEDs.run(AnimationData().animation(Animation.SPARKLETOCOLOR).color(0xFF))
        checkAllPixels(testLEDs, 0xFF)
    }

    @Test
    fun testStack() {
        val testLEDs = EmulatedAnimatedLEDStripNonConcurrent(50)

        testLEDs.run(AnimationData().animation(Animation.STACK).color(0xFF))
        checkAllPixels(testLEDs, 0xFF)
    }

    @Test
    fun testWipe() {
        val testLEDs = EmulatedAnimatedLEDStripNonConcurrent(50)

        testLEDs.run(AnimationData().animation(Animation.WIPE).color(0xFF))
        checkAllPixels(testLEDs, 0xFF)
    }

}