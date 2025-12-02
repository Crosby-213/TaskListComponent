package ComponenetProjectRoot.src.Components.Checklist;

/**
 * Kernel interface for a checklist of tasks.
 *
 * @author JayCrosby
 * @mathdefinitions
 *
 *                  <pre>
 * STRING_OF_TASKS is modeled as a string of Task entries:
 * entries: string of Task
 * length(entries) = number of tasks
 *                  </pre>
 */
public interface ChecklistKernel {

    /**
     * Adds {@code t} to the end of {@code this}.
     *
     * @param t
     *            the task to append
     * @updates this
     * @requires t != null
     * @ensures // entries = #entries * <t>
     */
    void append(Task t);

    /**
     * Removes and returns the last task in {@code this}.
     *
     * @updates this
     * @requires length(this) > 0
     * @ensures // removeLast = last(#entries) and entries = front(#entries)
     */
    Task removeLast();

    /**
     * Reports whether {@code this} has no tasks.
     *
     * @return true iff length(this) == 0
     * @ensures isEmpty = (length(this) == 0)
     */
    boolean isEmpty();

    /**
     * Reports the number of tasks.
     *
     * @return the length of {@code this}
     * @ensures length = length(this)
     */
    int length();
}
