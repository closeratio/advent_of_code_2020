package com.closeratio.aoc2020

object Runner : AbstractRunner<Long>() {

    private val tiles: Set<Tile> = TileParser.parseTiles(javaClass.getResource("/input.txt").readText())
    private val configuration = TileConfiguration.computeFrom(tiles)

    override fun part1(): Long = configuration.checksum()

    override fun part2(): Long = configuration
        .calculateWaterRoughness(
            SearchImage.from(javaClass.getResource("/search_image.txt").readText())
        )
        .toLong()

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}