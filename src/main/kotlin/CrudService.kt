open class Node(open var id: Int)

open class CrudService<E : Node> {
    private var nextId: Int = 1
    var elements = mutableListOf<E>()

    fun add(entity: E): E {
        entity.id = nextId++
        elements.add(entity)
        return elements.last()
    }

    open fun delete(id: Int): Boolean {
        for (node in elements) {
            if (node.id == id) {
                elements.remove(node)
                return true
            }
        }
        return false
    }

    fun edit(entity: E): Boolean {
        for ((index, node) in elements.withIndex()) {
            if (node.id == entity.id) {
                elements[index] = entity
                return true
            }
        }
        return false
    }

    fun get(): List<E> {
        return elements
    }

    open fun getById(id: Int) = elements.find { it.id == id }

    open fun print() {
        for (node in elements) {
            println(node)
        }
    }

    open fun clear() {
        nextId = 1
        elements = mutableListOf()
    }
}