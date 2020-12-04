package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.core.IsIterableContaining.hasItems
import org.junit.jupiter.api.Test

class BatchFileParserTest {

    @Test
    fun parse() {
        val dataList = BatchFileParser.parse(javaClass.getResource("/test_input_1.txt").readText())

        assertThat(dataList, hasSize(4))

        assertThat(
            dataList, hasItems(
                PassportData(
                    mapOf(
                        "ecl" to "gry",
                        "pid" to "860033327",
                        "eyr" to "2020",
                        "hcl" to "#fffffd",
                        "byr" to "1937",
                        "iyr" to "2017",
                        "cid" to "147",
                        "hgt" to "183cm"
                    )
                ),
                PassportData(
                    mapOf(
                        "hcl" to "#ae17e1",
                        "iyr" to "2013",
                        "eyr" to "2024",
                        "ecl" to "brn",
                        "pid" to "760753108",
                        "byr" to "1931",
                        "hgt" to "179cm"
                    )
                )
            )
        )
    }

}