package com.closeratio.aoc2020.math

import com.closeratio.aoc2020.math.Vec2i.Companion.ZERO
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import kotlin.math.PI

class Vec2iTest {

    @Test
    fun rotate() {

        assertThat(ZERO.rotate(PI), `is`(ZERO))

        assertThat(Vec2i(0, -1).rotate(PI / 2.0), `is`(Vec2i(1, 0)))
        assertThat(Vec2i(0, -1).rotate(PI), `is`(Vec2i(0, 1)))
        assertThat(Vec2i(0, -1).rotate(PI * 1.5), `is`(Vec2i(-1, 0)))
        assertThat(Vec2i(0, -1).rotate(PI * 2), `is`(Vec2i(0, -1)))

        assertThat(Vec2i(0, -12).rotate(PI / 2.0), `is`(Vec2i(12, 0)))
        assertThat(Vec2i(0, -12).rotate(PI), `is`(Vec2i(0, 12)))
        assertThat(Vec2i(0, -12).rotate(PI * 1.5), `is`(Vec2i(-12, 0)))
        assertThat(Vec2i(0, -12).rotate(PI * 2), `is`(Vec2i(0, -12)))

    }

}