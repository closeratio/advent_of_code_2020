package com.closeratio.aoc2020

object Runner : AbstractRunner<Long>() {

    private val tiles: Set<Tile> = TileParser.parseTiles(javaClass.getResource("/input.txt").readText())

    override fun part1(): Long = TileConfiguration.computeFrom(tiles).checksum()

    override fun part2(): Long = 0L

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}