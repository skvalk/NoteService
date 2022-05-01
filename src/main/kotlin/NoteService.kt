class NoteNotFoundException(message: String) : RuntimeException(message)

object NoteService {
    private var notes = mutableListOf<Note>()
    private var comments = mutableListOf<Comment>()
    private var noteId: Int = 1
    private var commentId: Int = 1

    fun add(title: String, text: String): Int {
        val note = Note(
            nid = noteId,
            title = title,
            text = text
        )

        notes.add(note)
        noteId++
        return note.nid
    }

    fun createComment(noteId: Int, userId: Int, message: String): Int {
        for (item in notes) {
            if (item.nid == noteId) {
                val comment = Comment(
                    cid = commentId,
                    nid = noteId,
                    uid = userId,
                    message = message
                )

                comments.add(comment)
                commentId++
                return comment.cid
            }
        }
        throw NoteNotFoundException("Не найдена заметка с id: $noteId")
    }

    fun delete(noteId: Int): Boolean {
        for (item in notes) {
            if (item.nid == noteId) {
                notes.remove(item)
                for (comment in comments) {
                    if (comment.nid == noteId) comment.deleted = true
                }
                return true
            }
        }
        return false
    }

    fun deleteComment(commentId: Int): Boolean {
        for (comment in comments) {
            if (comment.cid == commentId && !comment.deleted) {
                comment.deleted = true
                return true
            }
        }
        return false
    }

    fun edit(noteId: Int, title: String, text: String): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (note.nid == noteId) {
                notes[index] = note.copy(
                    nid = noteId,
                    title = title,
                    text = text
                )
                return true
            }
        }
        return false
    }

    fun editComment(commentId: Int, userId: Int, text: String): Boolean {
        for ((index, comment) in comments.withIndex()) {
            if (comment.cid == commentId
                && comment.uid == userId
                && !comment.deleted
            ) {
                comments[index] = comment.copy(message = text)
                return true
            }
        }
        return false
    }

    fun get(): List<Note> {
        return notes
    }

    fun getById(noteId: Int): Note {
        for (note in notes) {
            if (note.nid == noteId) {
                return note
            }
        }
        throw NoteNotFoundException("Не найдена заметка с id: $noteId")
    }

    fun getComments(noteId: Int): List<Comment> {
        val listComment = mutableListOf<Comment>()
        for (comment in comments) {
            if (comment.nid == noteId && !comment.deleted) {
                listComment.add(comment)
            }
        }
        return listComment
    }

    fun restoreComment(commentId: Int): Boolean {
        for (comment in comments) {
            if (comment.cid == commentId && comment.deleted) {
                comment.deleted = false
                return true
            }
        }
        return false
    }

    fun print() {
        for (note in notes) {
            println(note)
        }

        for (comment in comments) {
            println(comment)
        }
    }

    fun clear() {
        notes = mutableListOf<Note>()
        comments = mutableListOf<Comment>()
        noteId = 1
        commentId = 1
    }
}