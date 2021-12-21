public class MyStack <T>
{
    Node topList;
    int count;
    public MyStack()
    {     topList = null;
          count = 0;
    }

    class Node
    {
        public T data;
        public Node next;
        public Node(T data)
        {
            this.data = data;
            this.next = null;
        }
    }

    public MyStack<T> push(T val)
    {
        Node newNode = new Node(val);
        newNode.data = val;
        newNode.next = topList;
        topList = newNode;count++;
        return this;
    }

    public T top()
    {
        return topList.data;
    }

    public void pop()
    {
        if(topList == null) {
            System.out.println(" Error ");
            return;
        }
        Node temp = topList;
        topList = topList.next;
        count--;
    }
    public Boolean empty()
    {
        return topList == null;
    }

    public int size()
    {
        return count;
    }
}