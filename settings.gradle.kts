rootProject.name = "advent_of_code_2020"

include(":aoc_2020:common")

(1..25).forEach {
    include(":aoc_2020:day_$it")
}