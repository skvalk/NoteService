data class Note(
    val title: String = "New",
    val text: String = "Text",
    override var id: Int = 1
) : Node(id = 1) {
    override fun toString(): String {
        return "(ID: $id) $title - $text"
    }
}