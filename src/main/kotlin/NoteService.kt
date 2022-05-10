class NoteNotFoundException(message: String) : RuntimeException(message)

object NoteService : CrudService<Note>() {

    private val comment = CommentService

    override fun delete(id: Int): Boolean {
        for (node in comment.elements) {
            if (node.nid == id) {
                node.deleted = true
            }
        }
        return super.delete(id)
    }

    fun createComment(noteId: Int, message: String): Int {
        for (node in elements) {
            if (node.id == noteId) {
                return comment.add(Comment(noteId, message)).id
            }
        }
        throw NoteNotFoundException("Не найдена заметка с id: $noteId")
    }

    fun deleteComment(commentId: Int): Boolean {
        return comment.delete(commentId)
    }

    fun editComment(commentId: Int, text: String): Boolean {
        for (node in comment.elements) {
            if (node.id == commentId && !node.deleted) {
                val temp = node.copy(message = text)
                return comment.edit(temp)
            }
        }
        return false
    }

    fun getComments(noteId: Int): List<Comment> {
        val listComment = mutableListOf<Comment>()
        for (node in comment.elements) {
            if (node.nid == noteId && !node.deleted) {
                listComment.add(node)
            }
        }
        return listComment
    }

    fun restoreComment(commentId: Int): Boolean {
        for (node in comment.elements) {
            if (node.id == commentId && node.deleted) {
                node.deleted = false
                return true
            }
        }
        return false
    }

    override fun getById(id: Int): Note {
        return super.getById(id) ?: throw NoteNotFoundException("Не найдена заметка с id: $id")
    }

    override fun print() {
        super.print()
        comment.print()
    }

    override fun clear() {
        comment.clear()
        super.clear()
    }
}
