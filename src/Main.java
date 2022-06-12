import java.util.Calendar;
import java.util.Date;

class MyCloneable implements Cloneable {
    private int num;

    public MyCloneable(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "A: " + num;
    }

    @Override
    public MyCloneable clone() {
        try {
            return (MyCloneable) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        testPartA();
        testPartB();
    }

    /**
     * Tests for part A.
     */
    private static void testPartA() {
        try {
            new ArrayQueue<>(-25);
        } catch (NegativeCapacityException e) {
            System.out.println("Negative capacity!");
        }

        Queue<MyCloneable> q1 = new ArrayQueue<>(5);
        ArrayQueue<MyCloneable> q2 = (ArrayQueue<MyCloneable>) q1;

        try {
            q1.peek();
        } catch (EmptyQueueException e) {
            System.out.println("The queue is empty");
        }

        try {
            q2.peek();
        } catch (QueueException e) {
            System.out.println("The queue is empty");
        }
        System.out.println();

        iterateQueue(q1, "q1");
        MyCloneable c1 = new MyCloneable(1);
        q1.enqueue(c1);
        iterateQueue(q1, "q1");
        System.out.println("Peek: " + q1.peek());
        System.out.println("Deque: " + q1.dequeue());
        System.out.println();
        iterateQueue(q1, "q1");

        MyCloneable c2 = new MyCloneable(2);
        q1.enqueue(c1);
        q1.enqueue(c2);

        iterateQueue(q2, "q2");

        ArrayQueue<MyCloneable> q3 = q2.clone();
        Queue<MyCloneable> q4 = q1.clone();

        System.out.println("Is q1 == q3? " + (q1 == q3));
        System.out.println("Is q2 == q3? " + (q2 == q3));
        System.out.println("Is q1 == q4? " + (q1 == q4));
        System.out.println("Is q2 == q4? " + (q2 == q4));
        System.out.println();
        iterateQueue(q3, "q3");

        c1.setNum(15);
        iterateQueue(q1, "q1");
        iterateQueue(q2, "q2");
        iterateQueue(q3, "q3");
        iterateQueue(q4, "q4");

        q1.enqueue(new MyCloneable(3));
        iterateQueue(q1, "q1");
        iterateQueue(q2, "q2");
        iterateQueue(q3, "q3");
        iterateQueue(q4, "q4");

        q1.enqueue(new MyCloneable(4));
        iterateQueue(q1, "q1");

        q1.enqueue(new MyCloneable(5));
        iterateQueue(q1, "q1");
        iterateQueue(q2, "q2");
        iterateQueue(q3, "q3");
        iterateQueue(q4, "q4");

        try {
            q1.enqueue(c1);
        } catch (QueueOverflowException e) {
            System.out.println("The queue reached its full capacity.");
        }

        try {
            q2.enqueue(c1);
        } catch (QueueException e) {
            System.out.println("The queue reached its full capacity.");
        }

        System.out.println("\nTesting of part A is over!\n\n");

    }

    /**
     * Iterates over a given queue.
     */
    private static void iterateQueue(Queue<?> q, String name) {
        System.out.println("Starts iterating " + name + "...");
        System.out.println("Queue size: " + q.size());
        System.out.println("Is empty? " + q.isEmpty());

        for (Object obj : q) {
            System.out.println(obj);
        }

        System.out.println("Done iterating");
        System.out.println("");
    }

    /**
     * Tests for part B.
     */
    private static void testPartB() {
        ToDoList l1 = new ToDoList();
        for (Task t : l1) {
            System.out.println("You should not reach here!");
        }

        Task t1 = new Task("Software Engineering HW0", new Date(2022 - 1900, Calendar.APRIL, 13));
        Task t2 = new Task("Software Engineering HW1", new Date(2022 - 1900, Calendar.MAY, 10));
        Task t3 = new Task("Software Engineering HW2", new Date(2022 - 1900, Calendar.MAY, 25));
        Task t4 = new Task("Software Engineering HW3", new Date(2022 - 1900, Calendar.JUNE, 16));

        l1.addTask(t1);
        l1.addTask(t4);
        l1.addTask(t3);
        l1.addTask(t2);

        System.out.println("l1: " + l1);

        ToDoList l2 = l1.clone();
        System.out.println("l1: " + l1);
        System.out.println("l2: " + l2);

        System.out.println("Is l1 == l2? " + (l1 == l2));
        System.out.println("Are lists l1 and l2 equal? " + l1.equals(l2));
        System.out.println("Are lists l2 and l1 equal? " + l2.equals(l1));
        System.out.println("Is l1.equals(null)? " + l1.equals(null));

        t1.setDueDate(new Date(2022 - 1900, Calendar.APRIL, 15));

        System.out.println("l1: " + l1);
        System.out.println("l2: " + l2);

        System.out.println();
        System.out.println("Are lists l1 and l2 equal? " + l1.equals(l2));
        System.out.println("Are lists l2 and l1 equal? " + l2.equals(l1));


        try {
            l1.addTask(new Task("Software Engineering HW0", new Date(2021 - 1900, Calendar.NOVEMBER, 24)));
        } catch (TaskAlreadyExistsException e) {
            System.out.println("Cannot add the task!");
        }
        try {
            l1.addTask(new Task("Software Engineering HW0", new Date(2018 - 1900, Calendar.MARCH, 21)));
        } catch (TaskAlreadyExistsException e) {
            System.out.println("Cannot add the task!");
        }
        Task clonedT1 = t1.clone();
        try {
            l1.addTask(clonedT1);
        } catch (TaskAlreadyExistsException e) {
            System.out.println("Cannot add the task!");
        }

        System.out.println("t1: " + t1);
        System.out.println("clonedT1: " + clonedT1);
        System.out.println("Are lists 11 and clonedT1 equal? " + t1.equals(clonedT1));
        System.out.println("Are lists clonedT1 and t1 equal? " + clonedT1.equals(t1));
        System.out.println("Is t1 == clonedT1? " + (t1 == clonedT1));
        System.out.println("Is t1.equals(null)? " + t1.equals(null));
        System.out.println();


        Date[] dates = {null, new Date(2022 - 1900, Calendar.MAY, 25), t2.getDueDate(), new Date(2017 - 1900, Calendar.MAY, 25), new Date(2023 - 1900, Calendar.MAY, 25)};

        checkScans(l1, dates);

        ToDoList l3 = new ToDoList();

        System.out.println("Are lists l1 and l3 equal? " + l1.equals(l3));
        l3.addTask(t2);
        System.out.println("l3: " + l3);
        System.out.println("Are lists l1 and l3 equal? " + l1.equals(l3));

        l3.addTask(t4);
        System.out.println("l3: " + l3);
        System.out.println("Are lists l1 and l3 equal? " + l1.equals(l3));

        l3.addTask(t1);
        System.out.println("l3: " + l3);
        System.out.println("Are lists l1 and l3 equal? " + l1.equals(l3));

        l3.addTask(t3);
        System.out.println("l3: " + l3);
        System.out.println("Are lists l1 and l3 equal? " + l1.equals(l3));

        checkScans(l3, dates);

        Task t5 = new Task("Calculus 2 HW4", new Date(2022 - 1900, Calendar.MAY, 25));
        l3.addTask(t5);

        System.out.println("l3: " + l3);
        checkScans(l3, dates);

        System.out.println("\nTesting of part B is over!");
    }

    /**
     * Checks all of the scan types for a given ToDoList using different dates.
**/
    private static void checkScans(ToDoList tdl, Date[] dates) {
        System.out.println("Starts scanning...");
        for (Task t : tdl) {
            System.out.println(t);
            System.out.println("-----------------------------------");
        }

        System.out.println("After initial scanning\n");

        int i = 1;
        for (Date date : dates) {
            System.out.println("Starting scan number " + i);
            tdl.setScanningDueDate(date);
            for (Task t : tdl) {
                System.out.println(t);
                System.out.println("-----------------------------------");
            }
            System.out.println("After scan number " + i++ + "\n");
        }

        System.out.println("Done scanning");
    }
}
