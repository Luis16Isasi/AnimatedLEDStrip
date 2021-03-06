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

package animatedledstrip.animations

//@Serializable
//@SerialName("RandomizedAnimationGroup")
//class RandomizedAnimationGroup(
//    override val info: AnimationInfo,
//    val animationList: List<String>,
//) : AnimationGroup(), SendableData {
//
//    constructor(groupInfo: NewAnimationGroupInfo, animationManager: LEDStripAnimationManager)
//            : this(prepareGroupParameters(animationManager,
//                                          groupInfo.groupInfo,
//                                          groupInfo.animationList),
//                   groupInfo.animationList)
//
//    override suspend fun runAnimation(
//        leds: AnimationManager,
//        params: RunningAnimationParams,
//        scope: CoroutineScope,
//    ) {
//        val nextAnimIndex = animationList.indices.random()
//        val nextAnim = (leds.sectionManager as Section).findAnimation(animationList[nextAnimIndex])
//        val animDuration =
//            params.intParams.getValue("animationDuration-${nextAnim.info.name} ($nextAnimIndex)").toLong()
//        val postAnimationDelay =
//            params.intParams.getValue("postAnimationDelay-${nextAnim.info.name} ($nextAnimIndex)").toLong()
//
//        leds.apply {
//            if (animDuration < 0)
//                runSequential(SubAnimationToRunParams(prepareAnimationToRunParams(params,
//                                                                                  nextAnim.info,
//                                                                                  nextAnimIndex),
//                                                      scope,
//                                                      sectionManager))
//            else
//                scope.launch {
//                    withTimeoutOrNull(animDuration) {
//                        runSequential(SubAnimationToRunParams(prepareAnimationToRunParams(params,
//                                                                                          nextAnim.info,
//                                                                                          nextAnimIndex),
//                                                              this,
//                                                              sectionManager))
//                    }
//                }.join()
//        }
//
//        delay(postAnimationDelay)
//    }
//}
