package com.closeratio.aoc2020.math

interface Vec {

    abstract fun adjacent(): Set<Vec>

}