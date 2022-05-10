fun main() {
    val service = NoteService
    service.add(Note("My", "Super text 1"))
    service.add(Note("My", "Super text 2"))
    service.add(Note("My", "Super text 3"))
    service.createComment(1,"Comment 1")
    service.createComment(1,"Comment 2")
    service.createComment(2,"Comment 3")
    service.editComment(2,"New Super text")
    service.deleteComment(2)
    service.print()
    println(service.getComments(1))
   // comment.add(Comment(1,"Comment 1"))

}