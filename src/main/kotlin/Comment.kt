data class Comment(
    val cid: Int = 0,
    val nid: Int = 0,
    val uid: Int = 0,
    val message: String = "",
    var deleted: Boolean = false
)