package com.closeratio.aoc2020

import com.closeratio.aoc2020.math.Vec2i

data class State(
    val direction: Direction,
    val shipPosition: Vec2i,
    val waypointPosition: Vec2i
)