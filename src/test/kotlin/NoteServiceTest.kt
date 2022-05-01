import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {

    @Test
    fun add() {
        NoteService.clear()
        val result = NoteService.add("Test", "Hello")
        assertNotEquals(0, result)
    }

    @Test
    fun createComment() {
        NoteService.clear()
        NoteService.add("Test", "Hello")
        val result = NoteService.createComment(1, 37, "Test")
        assertNotEquals(0, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun createCommentException() {
        NoteService.clear()
        NoteService.add("Test", "Hello")
        NoteService.createComment(37, 37, "Test")
    }

    @Test
    fun delete() {
        NoteService.clear()
        NoteService.add("Test", "Hello")
        NoteService.createComment(1, 37, "Test")
        val result = NoteService.delete(1)
        assertTrue(result)
        val resultFalse = NoteService.delete(1)
        assertFalse(resultFalse)
    }

    @Test
    fun deleteComment() {
        NoteService.clear()
        NoteService.add("Test", "Hello")
        NoteService.createComment(1, 37, "Test")
        val result = NoteService.deleteComment(1)
        assertTrue(result)
        val resultFalse = NoteService.deleteComment(1)
        assertFalse(resultFalse)
    }

    @Test
    fun edit() {
        NoteService.clear()
        NoteService.add("Test", "Hello")
        NoteService.createComment(1, 37, "Test")
        val result = NoteService.edit(1, "NewTestTitle", "NewText")
        assertTrue(result)
        val resultFalse = NoteService.edit(37, "Test", "Hello")
        assertFalse(resultFalse)
    }

    @Test
    fun editCorrect() {
        NoteService.clear()
        NoteService.add("Test", "Hello")
        NoteService.createComment(1, 37, "Test")
        NoteService.edit(1, "NewTestTitle", "NewText")
        val result = NoteService.getById(1).title
        assertEquals("NewTestTitle", result)
    }

    @Test
    fun editComment() {
        NoteService.clear()
        NoteService.add("Test", "Hello")
        NoteService.createComment(1, 37, "Test")
        val resultTrue = NoteService.editComment(1,37, "NewText")
        assertTrue(resultTrue)
        val resultFalseOne = NoteService.editComment(1,38, "NewText")
        assertFalse(resultFalseOne)
        val resultFalseTwo = NoteService.editComment(2,37, "NewText")
        assertFalse(resultFalseTwo)
        NoteService.deleteComment(1)
        val resultFalseThree = NoteService.editComment(1,37, "NewText")
        assertFalse(resultFalseThree)
    }

    @Test
    fun editCommentCorrect(){
        NoteService.clear()
        NoteService.add("Test", "Hello")
        NoteService.createComment(1, 37, "Test")
        NoteService.editComment(1,37, "NewText")
        val result = NoteService.getComments(1)[0].message
        assertEquals("NewText", result)
    }

    @Test
    fun get() {
        NoteService.clear()
        NoteService.add("Test", "Hello")
        NoteService.add("Test", "Hello")
        NoteService.add("Test", "Hello")
        val result = NoteService.get().size
        assertEquals(3, result)
        assertNotEquals(4, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getById() {
        NoteService.clear()
        NoteService.add("Test", "Hello")
        NoteService.createComment(1, 37, "Test")
        NoteService.getById(5)
    }

    @Test
    fun getComments() {
        NoteService.clear()
        NoteService.add("Test", "Hello")
        NoteService.createComment(1, 37, "Test")
        NoteService.createComment(1, 37, "Test")
        NoteService.createComment(1, 37, "Test")
        val result = NoteService.getComments(1).size
        assertEquals(3, result)
        assertNotEquals(4, result)
    }

    @Test
    fun restoreComment() {
        NoteService.clear()
        NoteService.add("Test", "Hello")
        NoteService.createComment(1, 37, "Test")
        val resultFalse = NoteService.restoreComment(1)
        assertFalse(resultFalse)
        NoteService.deleteComment(1)
        val resultTrue = NoteService.restoreComment(1)
        assertTrue(resultTrue)
    }
}