package de.westnordost.streetcomplete.quests.tactile_paving

import de.westnordost.osmapi.map.data.OsmNode
import de.westnordost.osmapi.map.data.OsmWay
import de.westnordost.streetcomplete.quests.TestMapDataWithGeometry
import org.junit.Assert.*
import org.junit.Test

class AddTactilePavingCrosswalkTest {
    private val questType = AddTactilePavingCrosswalk()

    @Test fun `not applicable to non-crossing`() {
        val node = OsmNode(1L, 1, 0.0,0.0, mapOf(
            "nub" to "dub"
        ))
        val mapData = TestMapDataWithGeometry(listOf(node))
        assertEquals(0, questType.getApplicableElements(mapData).toList().size)
        assertEquals(false, questType.isApplicableTo(node))
    }

    @Test fun `applicable to crossing`() {
        val crossing = OsmNode(1L, 1, 0.0,0.0, mapOf(
            "highway" to "crossing"
        ))
        val mapData = TestMapDataWithGeometry(listOf(crossing))
        assertEquals(1, questType.getApplicableElements(mapData).toList().size)
        assertNull(questType.isApplicableTo(crossing))
    }

    @Test fun `not applicable to crossing with private road`() {
        val crossing = OsmNode(1L, 1, 0.0,0.0, mapOf(
            "highway" to "crossing"
        ))
        val privateRoad = OsmWay(1L, 1, listOf(1,2,3), mapOf(
            "highway" to "residential",
            "access" to "private"
        ))
        val mapData = TestMapDataWithGeometry(listOf(crossing, privateRoad))
        assertEquals(0, questType.getApplicableElements(mapData).toList().size)
        assertNull(questType.isApplicableTo(crossing))
    }
}
