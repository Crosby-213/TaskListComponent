package ComponenetProjectRoot.src.Components.Checklist;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Abstract "secondary" implementation for representation-independent methods of
 * Checklist. Concrete subclasses need only implement the kernel methods
 * declared in ChecklistKernel (e.g., append, removeLast, isEmpty, length) using
 * their chosen representation.
 *
 * <p>
 * This class implements the enhanced methods purely in terms of the kernel,
 * without accessing any representation details.
 * </p>
 */
public abstract class ChecklistSecondary implements Checklist {

    //Kernel methods to be supplied by concrete subclass
    @Override
    public abstract void append(Task t);

    @Override
    public abstract Task removeLast();

    @Override
    public abstract boolean isEmpty();

    @Override
    public abstract int length();

    //Enhanced methods

    /**
     * Marks the task at index {@code i} complete.
     *
     * @param i
     *            index to mark complete (0-based)
     * @updates this
     * @ensures // same tasks in same order as #this except position i is
     *          completed
     */
    @Override
    public void completeAt(int i) {
        int n = this.length();
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException(
                    "index: " + i + ", length: " + n);
        }

        // Move the trailing tasks off the back to expose index i at the back
        int toPop = n - 1 - i;
        Deque<Task> buf = new ArrayDeque<>();
        for (int k = 0; k < toPop; k++) {
            buf.push(this.removeLast());
        }

        // Now the last element corresponds to index i
        Task target = this.removeLast();
        target.complete(); // representation-independent operation on Task
        this.append(target);

        // Restore the trailing tasks in their original order
        while (!buf.isEmpty()) {
            this.append(buf.pop());
        }
    }

    /**
     * Finds the first index whose string form contains {@code text}.
     *
     * @param text
     *            substring to search
     * @return index of first matching task or -1 if none
     * @restores text
     */
    @Override
    public int find(String text) {
        int n = this.length();
        int found = -1;
        Deque<Task> buf = new ArrayDeque<>();

        // Move all tasks off to a temporary buffer
        for (int k = 0; k < n; k++) {
            buf.push(this.removeLast());
        }

        // Rebuild in order, checking from index 0..n-1
        for (int idx = n - 1; idx >= 0; idx--) {
            Task t = buf.pop();
            if (found == -1 && t.toString().contains(text)) {
                found = idx;
            }
            this.append(t);
        }
        return found;
    }

    /**
     * Returns a snapshot of the current tasks (front to back) as strings.
     *
     * @return array of stringified tasks
     */
    @Override
    public String[] toArray() {
        int n = this.length();
        String[] out = new String[n];
        Deque<Task> buf = new ArrayDeque<>();

        // Move all tasks to buffer so we can read them without exposing rep
        for (int k = 0; k < n; k++) {
            buf.push(this.removeLast());
        }

        // Restore tasks to this in original order while filling output
        for (int idx = n - 1; idx >= 0; idx--) {
            Task t = buf.pop();
            out[idx] = t.toString();
            this.append(t);
        }
        return out;
    }
}
