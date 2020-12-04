package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PassportDataTest {

    companion object {

        @JvmStatic
        fun generateIsValidCombinations(): List<Arguments> = listOf(
            Arguments.of("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm", true),
            Arguments.of("iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929", false),
            Arguments.of("hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm", true),
            Arguments.of("hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in", false)
        )

    }

    @ParameterizedTest
    @MethodSource("generateIsValidCombinations")
    fun isValid(
        data: String,
        valid: Boolean
    ) {
        val passportData = PassportData.parse(data)

        assertThat(passportData.isValid(), `is`(valid))
    }

}