package ComponenetProjectRoot.src.Components.Checklist;

components.checklist├─ChecklistKernel(interface) // kernel
├─Checklist(interface) // enhanced extends kernel
└─Checklist1L(class) // representation implements Checklist

/**
 * Enhanced interface for a checklist, layered on {@link ChecklistKernel}.
 *
 * <p>
 * All secondaries are intended to be implemented using only kernel operations
 * (append, removeLast, isEmpty, length) per OSU discipline.
 * </p>
 */
public interface Checklist extends ChecklistKernel {

    /**
     * Marks the {@code i}-th task (0-based from front) as complete.
     *
     * @param i
     *            index of task to complete
     * @updates this
     * @requires 0 <= i && i < length(this)
     * @ensures // same tasks in same order as #this except position i is
     *          completed
     */
    void completeAt(int i);

    /**
     * Finds the first index whose text contains {@code text}.
     *
     * @param text
     *            substring to search
     * @return index of first matching task or -1 if none
     * @restores text
     * @ensures // (-1 <= find && find < length(this)) && // (find = -1 iff for
     *          all k, text not in toString(this[k]))
     */
    int find(String text);

    /**
     * Returns a snapshot of tasks as strings in display order.
     *
     * @return array of stringified tasks (e.g., "[ ] Read", "[x] Code")
     * @ensures // toArray.length = length(this) and toArray[i] =
     *          toString(this[i])
     */
    String[] toArray();
}
