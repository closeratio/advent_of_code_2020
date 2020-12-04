package com.closeratio.aoc2020

data class PassportData(
    val items: Map<String, String>
) {

    fun isValid(): Boolean = REQUIRED_FIELDS.all { it in items }

    companion object {

        private val REQUIRED_FIELDS = setOf(
            "byr",
            "iyr",
            "eyr",
            "hgt",
            "hcl",
            "ecl",
            "pid"
        )

        fun parse(data: String): PassportData = PassportData(
            data.split("\n")
                .flatMap { it.split(" ") }
                .map { it.split(":") }
                .map { (left, right) -> left to right }
                .toMap()
        )

    }

}