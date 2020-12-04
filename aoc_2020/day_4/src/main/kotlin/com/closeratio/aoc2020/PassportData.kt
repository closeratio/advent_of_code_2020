package com.closeratio.aoc2020

data class PassportData(
    val items: Map<String, String>
) {

    fun isLooselyValid(): Boolean = FIELD_RULES.all { it.key in items }

    fun isStronglyValid(): Boolean = FIELD_RULES.all {
        if (it.key in items) {
            it.value(items.getValue(it.key))
        } else {
            false
        }
    }

    companion object {

        private val FIELD_RULES = mapOf<String, (String) -> Boolean>(
            "byr" to this::checkBirthYear,
            "iyr" to this::checkIssueYear,
            "eyr" to this::checkExpirationYear,
            "hgt" to this::checkHeight,
            "hcl" to this::checkHairColour,
            "ecl" to this::checkEyeColour,
            "pid" to this::checkPassportId
        )

        private fun checkBirthYear(birthYear: String): Boolean = birthYear
            .toIntOrNull()
            ?.let {
                it in 1920..2002
            }
            ?: false

        private fun checkIssueYear(issueYear: String): Boolean = issueYear
            .toIntOrNull()
            ?.let {
                it in 2010..2020
            }
            ?: false

        private fun checkExpirationYear(expirationYear: String): Boolean = expirationYear
            .toIntOrNull()
            ?.let {
                it in 2020..2030
            }
            ?: false

        private fun checkHeight(height: String): Boolean = height
            .let {
                val unit = height.takeLast(2)
                val value = height.dropLast(2).toIntOrNull()

                when (unit) {
                    "cm" -> value?.let { it in 150..193 } ?: false
                    "in" -> value?.let { it in 59..76 } ?: false
                    else -> false
                }
            }

        private fun checkHairColour(hairColour: String): Boolean = hairColour
            .let {
                if (hairColour.startsWith("#")) {
                    hairColour
                        .drop(1)
                        .all {
                            it.isDigit() || it in setOf('a', 'b', 'c', 'd', 'e', 'f')
                        }
                } else {
                    false
                }
            }

        private fun checkEyeColour(eyeColour: String): Boolean = eyeColour in setOf(
            "amb",
            "blu",
            "brn",
            "gry",
            "grn",
            "hzl",
            "oth"
        )

        private fun checkPassportId(passportId: String): Boolean = passportId.let {
            it.length == 9 && passportId.all { character -> character.isDigit() }
        }

        fun parse(data: String): PassportData = PassportData(
            data.split("\n")
                .flatMap { it.split(" ") }
                .map { it.split(":") }
                .map { (left, right) -> left to right }
                .toMap()
        )

    }

}