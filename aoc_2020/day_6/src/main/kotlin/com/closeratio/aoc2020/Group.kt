package com.closeratio.aoc2020

data class Group(
    val people: List<Person>
) {

    fun answerCount(): Int = people
        .fold(setOf<Char>()) { acc, curr -> acc + curr.answers }
        .size

}