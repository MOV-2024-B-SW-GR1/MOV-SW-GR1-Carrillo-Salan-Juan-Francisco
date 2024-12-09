package Utils

import kotlin.properties.Delegates

class ObservableList<T> {
    private val internalList = mutableListOf<T>()
    private val observers = mutableListOf<(List<T>) -> Unit>()

    // Notify observers of changes
    private fun notifyObservers() {
        observers.forEach { it(internalList) }
    }

    fun add(element: T) {
        internalList.add(element)
        notifyObservers()
    }

    fun remove(element: T) {
        internalList.remove(element)
        notifyObservers()
    }

    fun setAll(elements: Collection<T>) {
        internalList.clear()
        internalList.addAll(elements)
        notifyObservers()
    }

    fun clear() {
        internalList.clear()
        notifyObservers()
    }

    fun toList(): List<T> = internalList.toList()

    fun observe(observer: (List<T>) -> Unit) {
        observers.add(observer)
    }
}
