package com.closeratio.aoc2020

data class BoardingPass(
    val data: String
) {

    fun seatId(): Int {
        val rowString = data.take(7)
        val columnString = data.takeLast(3)

        val rows = (0..127).map { Row(it) }
        val columns = (0..7).map { Column(it) }

        return getElementId(rowString, rows) * 8 + getElementId(columnString, columns)
    }

    private fun getElementId(
        dataString: String,
        elements: List<Element>
    ): Int {
        if (elements.size == 1) {
            return elements[0].id
        } else if (dataString.isEmpty()) {
            throw IllegalStateException("Data string is empty but more than one element remains: $elements")
        }

        val (leftChunk, rightChunk) = elements.chunked(elements.size / 2)

        return when(val regionChar = dataString[0]) {
            'F', 'L' -> getElementId(dataString.drop(1), leftChunk)
            'B', 'R' -> getElementId(dataString.drop(1), rightChunk)
            else -> throw IllegalArgumentException("Unknown region specifier: $regionChar")
        }
    }

}

