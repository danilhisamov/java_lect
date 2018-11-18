package Lecture5;

public class Stack {
    private int length = 0;
    private int capacity = 16;
    private Object[] array = new Object[capacity];

    public void push(Object object) {
        if (length == capacity)
            extend();

        array[length] = object;
        length++;
    }

    public Object pop() {
        if (length == 0) return null;
        Object o = array[length - 1];
        array[length - 1] = null;
        length--;
        return o;
    }

    public Object peek() {
        if (length == 0) return null;
        return array[length - 1];
    }

    @Override
    public String toString() {
        String divider = ", ";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < length; i++) {
            sb.append(array[i].toString());
            sb.append(divider);
        }
        sb.replace(sb.lastIndexOf(divider), sb.length(), "");
        sb.append("]");

        return sb.toString();
    }

    private void extend() {
        capacity = capacity / 2 * 3 + 1;
        Object[] tmpArr = new Object[capacity];
        System.arraycopy(array, 0, tmpArr, 0, length);
        array = tmpArr;
        System.out.println("Inner array extended, new capacity = " + capacity);
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        stack.push("First String");
        System.out.println(stack.peek());
        stack.push(1);
        System.out.println(stack.pop());
        stack.push(2L);
        stack.push(3F);
        stack.push(4D);
        for (int i = 0; i < 16; i++) {
            stack.push('c');
        }
        stack.push(new Object());
        System.out.println(stack);
    }
}
