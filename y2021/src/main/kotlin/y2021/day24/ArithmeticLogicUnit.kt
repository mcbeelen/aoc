package y2021.day24

import y2021.day13.Instruction

class ArithmeticLogicUnit(val instructions : List<String>) {

    private var w = 0
    private var x = 0
    private var y = 0
    private var z = 0

    fun isValidModelNumber(candidate: String) : Boolean {

        return isValid()
    }

    private fun isValid() = z == 0

}