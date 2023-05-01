/**
 * CS2030S PE1 Question 2
 * AY21/22 Semester 2
 *
 * @author A0000000X
 */

class ArrayStack<T> implements Stack<T> {

  private T[] stack;

  private int pos;

  public ArrayStack(int max) {
    // The only way we can put an object into array is through
    // the method set() and we only put object of type T inside.
    // So it is safe to cast `Object[]` to `T[]`.
    @SuppressWarnings({"unchecked", "rawtypes"})
    T[] arr = (T[]) new Object[max];
    this.stack = arr;
    this.pos = 0;
  }

  public boolean isEmpty() {
    for(T i : stack) {
      if (i != null) {
        return false;
      }
    }
    return true;
  }

  public boolean isFull() {
    for(T i : stack) {
      if (i == null) {
        return false;
      }
    }
    return true;
  }

  @Override
  public T pop() {
    if (this.isEmpty()) {
      return null;
    }

    T result = stack[this.pos];
    stack[this.pos] = null;

    if (this.pos != 0) {
      this.pos -= 1;
    }

    return result;
  }
  
  @Override
  public void push(T item) {
    if (this.isFull()) {
      return;
    } else if (this.stack[this.pos] != null) {
      this.pos += 1;
    }

    stack[this.pos] = item;
  }

  @Override
  public int getStackSize() {
    int count = 0;
    for (T i : stack) {
      if (i != null) {
        count += 1;
      }
    }
    return count;
  }

  public static <U> ArrayStack<U> of(U[] arr, int max) {
    ArrayStack<U> result = new ArrayStack<U>(max);
    int len = max > arr.length ? arr.length : max;
    for (int i = 0; i < len; i++) {
      result.push(arr[i]);
    }
    return result;
  }

  public void pushAll(ArrayStack<? extends T> as) {
    // had to move size outside the for loop, otherwise it
    // is recalculated each time the stack is updated.
    int size = as.getStackSize();
    for (int i = 0; i < size; i++) {
      this.push(as.pop());
    }
  }

  public void popAll(ArrayStack<? super T> as) {
    // had to move size outside the for loop, otherwise it
    // is recalculated each time the stack is updated.
    int size = this.getStackSize();
    for (int i = 0; i < size; i++) {
      as.push(this.pop());
    }
  }

  @Override
  public String toString() {
    String s = "Stack:";
    for (int i = 0; i < this.getStackSize(); i++) {
      s += " " + this.stack[i];
    }
    return s;
  }
}
