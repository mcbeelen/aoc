package day03_overlapping_fabric

import util.grid.ScreenCoordinate

class Fabric(val claims: List<Claim>) {


    val maxX: Int by lazy {
        claims.map { it.area.right }.max() ?: 0
    }
    val maxY: Int by lazy {
        claims.map { it.area.bottom }.max() ?: 0
    }


    fun hasMultipleClaimsFor(inchOfFabric: ScreenCoordinate) =
        claims.map { it.area.contains(inchOfFabric) }
                .filter { it }
                .count() > 1


    fun countInchesOfOverlappingClaims(): Int {
        var numberOfInchesWithOverlappingClaims = 0

        for (x in 0 .. maxX) {
            for (y in 0 .. maxY) {
                val inchOfFabric = ScreenCoordinate(x, y)
                if (hasMultipleClaimsFor(inchOfFabric)) {
                    numberOfInchesWithOverlappingClaims++
                }
            }
        }

        return numberOfInchesWithOverlappingClaims
    }

    fun findIdOfNonOverlappingClaim(): String {
        return claims.first {
            hasNoOverlaps(it)
        }.id

    }

    private fun hasNoOverlaps(candidate: Claim) = claims.none { it.overlapsWith(candidate) }


}