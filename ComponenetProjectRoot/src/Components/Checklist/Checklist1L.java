package ComponenetProjectRoot.src.Components.Checklist;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Sequence-based implementation of {@link Checklist}.
 *
 * <p>
 * Representation: {@code rep} is a {@code Sequence<Task>} storing tasks from
 * front (index 0) to back (index length-1).
 * </p>
 *
 * @invariant rep != null
 * @correspondence entries = // the string of Tasks stored in rep in index order
 *                 (for all valid i) <rep[i]> concatenated in order
 */
public final class Checklist1L extends ChecklistSecondary implements Checklist {

    // ------------ Representation ------------
    private final Sequence<Task> rep;

    // ------------ Constructors --------------
    /**
     * No-argument constructor: makes an empty checklist.
     *
     * @ensures length(this) = 0
     */
    public Checklist1L() {
        this.rep = new Sequence1L<>();
    }

    //KERNEL
    @Override
    public void append(Task t) {
        assert t != null : "Violation: t is null";
        this.rep.add(this.rep.length(), t);
    }

    @Override
    public Task removeLast() {
        assert !this.isEmpty() : "Violation: empty checklist";
        return this.rep.remove(this.rep.length() - 1);
    }

    @Override
    public boolean isEmpty() {
        return this.rep.length() == 0;
    }

    @Override
    public int length() {
        return this.rep.length();
    }

    //SECONDARIES
    @Override
    public void completeAt(int i) {
        assert 0 <= i && i < this.length() : "index out of bounds";

        Checklist1L temp = new Checklist1L();
        int n = this.length();

        for (int c = 0; c < n; c++) {
            temp.append(this.removeLast());
        }

        for (int idx = 0; idx < n; idx++) {
            Task t = temp.removeLast(); // restore original order
            this.append(idx == i ? t.completedCopy() : t);
        }
    }

    @Override
    public int find(String text) {
        assert text != null : "Violation: text is null";

        Checklist1L temp = new Checklist1L();
        int n = this.length();

        for (int c = 0; c < n; c++) {
            temp.append(this.removeLast());
        }

        int found = -1;
        for (int idx = 0; idx < n; idx++) {
            Task t = temp.removeLast();

            if (found == -1 && t.toString().contains(text)) {
                found = idx;
            }
            this.append(t);
        }
        return found;
    }

    @Override
    public String[] toArray() {
        int n = this.length();
        String[] out = new String[n];

        Checklist1L temp = new Checklist1L();
        for (int c = 0; c < n; c++) {
            temp.append(this.removeLast());
        }
        for (int i = 0; i < n; i++) {
            Task t = temp.removeLast();
            out[i] = t.toString();
            this.append(t);
        }
        return out;
    }
}
