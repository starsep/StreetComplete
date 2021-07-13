package de.westnordost.streetcomplete.data.visiblequests

import de.westnordost.streetcomplete.data.ApplicationDbTestCase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class QuestProfilesDaoTest : ApplicationDbTestCase() {
    private lateinit var dao: QuestProfilesDao

    @Before fun createDao() {
        dao = QuestProfilesDao(database)
    }

    @Test fun getEmpty() {
        assertTrue(dao.getAll().isEmpty())
        assertNull(dao.getName(123))
    }

    @Test fun addGetDelete() {
        dao.add("test")
        assertEquals(listOf(QuestProfile(1, "test")), dao.getAll())
        assertEquals("test", dao.getName(1))
        dao.delete(1)
        assertTrue(dao.getAll().isEmpty())
    }

    @Test fun addTwo() {
        dao.add("one")
        dao.add("two")
        assertEquals(listOf(
            QuestProfile(1, "one"),
            QuestProfile(2, "two")
        ), dao.getAll())
    }
}