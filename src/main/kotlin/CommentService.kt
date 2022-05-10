object CommentService : CrudService<Comment>() {
    override fun delete(id: Int): Boolean {
        for (node in elements) {
            if (node.id == id && !node.deleted) {
                node.deleted = true
                return true
            }
        }
        return false
    }
}
