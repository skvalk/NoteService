data class Comment(
    val nid: Int = 0,
    val message: String = "",
    var deleted: Boolean = false,
    override var id: Int = 1
): Node(id = 1){
    override fun toString(): String {
        return "(ID Comment: $id)(ID NOTE: $nid) $message ($deleted)"
    }
}