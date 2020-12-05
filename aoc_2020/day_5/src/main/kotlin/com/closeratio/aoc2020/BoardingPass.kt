package com.closeratio.aoc2020

data class BoardingPass(
    val data: String
) {

    fun seatId(): Int {
        val rowString = data.take(7)
        val columnString = data.takeLast(3)

        val rows = (1..128).map { Row(it - 1) }
        val columns = (1..8).map { Column(it - 1) }

        val rowId = getElementId(rowString, rows)
        val columnId = getElementId(columnString, columns)

        return rowId * 8 + columnId
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
        val regionChar = dataString[0]

        return when(regionChar) {
            'F', 'L' -> getElementId(dataString.drop(1), leftChunk)
            'B', 'R' -> getElementId(dataString.drop(1), rightChunk)
            else -> throw IllegalArgumentException("Unknown region specifier: $regionChar")
        }
    }

}

