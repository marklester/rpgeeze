package rpgeeze.util;

import rpgeeze.dp.Iterator;

/**
 * An inexhaustible Iterator that continuously cycles through one or more elements.  
 */

public class ContinuousIteratorWithElements<T> implements Iterator<T> {
        private final T[] elements;
        private int cursor = 0;

        /**
         * Creates a ContinuousIterator for the specified list of elements.
         */
        public ContinuousIteratorWithElements(T... elements) {
                this.elements = elements;
        }

        /**
         * Advances to the next element. This will never go outside the underlying
         * list.
         */
        public void advance() {
                this.cursor = (this.cursor + 1) % this.elements.length;
        }

        /**
         * Returns the current element. This doesn't automatically advance the
         * Iterator, you'll have to explicitly call advance.
         */
        public T current() {
                return this.elements[this.cursor];
        }

        /**
         * Always returns false.  
         */
        public boolean isDone() {
                return false;
        }

        /**
         * Resets the cursor to the beginning of the list. You probably will never
         * need this, this is how.
         */
        public void reset() {
                cursor = 0;
        }
}