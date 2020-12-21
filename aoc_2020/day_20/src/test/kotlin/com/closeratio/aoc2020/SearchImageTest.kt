package com.closeratio.aoc2020

import com.closeratio.aoc2020.math.Vec2i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.core.IsIterableContaining
import org.junit.jupiter.api.Test

class SearchImageTest {

    private val image = SearchImage.from(javaClass.getResource("/search_image.txt").readText())

    @Test
    fun from() {
        assertThat(image.searchPixels, hasSize(15))
        assertThat(image.searchPixels, IsIterableContaining.hasItems(
            Vec2i(0, 1),
            Vec2i(1, 2),

            Vec2i(4, 2),
            Vec2i(5, 1)
        ))
    }

    @Test
    fun findTarget() {
        val pixels: List<List<Char>> = javaClass.getResource("/search_image_test_input.txt")
            .readText()
            .trim()
            .split("\n")
            .map { line ->
                line.map { it }
            }

        val result = image.findTarget(pixels)

        assertThat(result, `is`(30))
    }

}