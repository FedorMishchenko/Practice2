package ua.nure.mishchenko.practice2;

import java.util.Iterator;
import java.util.function.Predicate;

public class Demo {
    public static void main(String[] args) {
        System.out.println("==== Array ====");
        Array list = new ArrayImpl();
        // [A, A2]
        list.add("A");
        list.add("A2");
        System.out.println(list);

        // 2
        System.out.println(list.size());
        // []
        list.clear();
        System.out.println(list);

        String a2 = "A2";
        a2 += "";
        // [A, null]
        list.add("A");
        list.add(null);
        list.add(a2);
        list.add("A3");
        System.out.println(list);
        // 2
        System.out.println(list.indexOf(a2));
        // 1
        System.out.println(list.indexOf(null));
        // [A, A2]
        list.remove(3);
        list.remove(1);
        System.out.println(list);

        System.out.println("==============TEST=================");
        list = new ArrayImpl();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);



        list.forEach(System.out::println);

        Array entityList =  new ArrayImpl();
        for(int i = 1; i < 6; i++) {
            Entity e = new Entity();
            e.setId(i);
            e.setAge(20 + i);
            entityList.add(e);
        }
        /*entityList.forEach(System.out::println);*/

        Iterator it = entityList.iterator(x -> x.getId() < 3 && x.getAge() < 22);
        while (it.hasNext()){
            System.out.println(it.next());
        }

/*        // AA2
        for (Object el : list) {
            System.out.print(el);
        }
        System.out.println();

        list = new ArrayImpl();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);


        Iterator<Object> it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + ", ");
        }

        // 1 2 3 4
        it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + ", ");
        }
        System.out.println();
        it = list.iterator();
        //1
        System.out.println(it.next());
        //2
        System.out.println(it.next());
        it.remove();
        // [1, 3, 4]
        System.out.println(list);
        // 3
        System.out.println(it.next());

        // [1, 4]
        it.remove();
        System.out.println(list);

        // class java.lang.IllegalStateException
        try {
            it.remove();
        } catch (IllegalStateException ex) {
            System.out.println(ex.getClass());
        }

        list.add(null);
        //true
        System.out.println(list.contains(null));
        //true
        System.out.println(list.contains(1));
        //false
        System.out.println(list.contains("A"));


        list.clear();
        list.add(1);
        list.add(null);
        //true
        System.out.println(list.containsAll(list));

        list.add(123);
        //false
        System.out.println(list.containsAll(list));

        list.clear();
        list.add(null);
        //null
        System.out.println(list);
        //123
        list.set(0,123);
        System.out.println(list);

        //ListImpl
        System.out.println("==== List ====");

        List list2 = new ListImpl();
        list2.addLast("A");
        //A
        System.out.println(list2.getFirst());
        list2.addLast("B");
        //B
        System.out.println(list2.getLast());
        list2.addLast("C");
        //C
        System.out.println(list2.getLast());
        //A B C
        for (Object e: list2){
            System.out.print(e.toString() + " ");
        }
        System.out.println();
        list2.removeLast();
        //B
        System.out.println(list2.getLast());
        //B
        System.out.println(list2.getLast());
        list2.removeFirst();
        //B
        System.out.println(list2.getFirst());
        list2.addFirst("A");
        list2.addLast("C");
        System.out.println(list2);

        //[A B C]
        Iterator<Object> it2 = list2.iterator();
        //A B C
        while (it2.hasNext()){
            System.out.print(it2.next() + " ");
        }
        System.out.println();

        it2 = list2.iterator();
        it2.next();
        it2.next();
        it2.remove();
        //[A, B]
        System.out.println(list2);
        //A
        Object ob = list2.search("A");
        //true
        System.out.println(ob);
        System.out.println(list2.remove("A"));
        //B
        System.out.println(list2.getFirst());

        list2.clear();
        list2.addLast("A");
        list2.addFirst("B");
        list2.addLast("C");
        //[B, A, C]
        System.out.println(list2);

        it2 = list2.iterator();
        it2.next();
        it2.next();
        it2.next();
        it2.remove();
        //[B, A]
        System.out.println(list2);

        list2.clear();
        list2.addLast(1);
        list2.addLast("A");
        list2.addLast(1);
        //[1, A, 1]
        System.out.println(list2);
        //size=3
        System.out.println("size=" + list2.size());
        list2.remove(1);
        //[A, 1]
        System.out.println(list2);
        //size=2
        System.out.println("size=" + list2.size());
        list2.remove("A");
        //[1]
        System.out.println(list2);
        //size=1
        System.out.println("size=" + list2.size());
        it2 = list2.iterator();
        //1
        System.out.println(it2.next());

        list2.clear();
        list2.addLast("a");
        list2.addLast("b");
        list2.addLast("c");
        //[a, b, c]
        System.out.println(list2);
        it2 = list2.iterator();
        it2.next();
        it2.remove();
        //exception
        try {
            it2.remove();
        }catch (IllegalStateException e){
            System.out.println("exception");
        }

        System.out.println("==== Queue ====");

        Queue queue = new QueueImpl();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        //[A, B, C]
        System.out.println(queue.toString());
        //[A, B, C]
        System.out.println(queue);
        queue.dequeue();
        //[B, C]
        System.out.println(queue);
        //B
        System.out.println(queue.top());
        //size=2
        System.out.println("size=" + queue.size());
        queue.clear();
        //size=0
        System.out.println("size=" + queue.size());

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        //[1, 2, 3, 4]
        System.out.println(queue);
        Iterator it3 = queue.iterator();
        //1 2 3 4
        while (it3.hasNext())
            System.out.printf("%s ",it3.next());
        System.out.println();
        it3 = queue.iterator();
        //1
        System.out.println(it3.next());
        //2
        System.out.println(it3.next());
        it3 = queue.iterator();
        it3.next();
        it3.next();
        it3.next();
        it3.next();
        it3.remove();
        //[1, 2, 3]
        System.out.println(queue);
        //exception
        try {
            it3.remove();
        }catch (IllegalStateException e){
            System.out.println("exception");
        }
        System.out.println("==== Stack ====");

        Stack stack = new StackImpl();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        //[A, B, C]
        System.out.println(stack);
        //C
        System.out.println(stack.top());
        stack.pop();
        //[B, A]
        System.out.println(stack);
        stack.clear();
        //size=0
        stack = new StackImpl();
        System.out.println("size=" + stack.size());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Iterator it4 = stack.iterator();
        //3, 2, 1
        while (it4.hasNext()){
            System.out.printf("%s, ", it4.next());
        }
        System.out.println();

        it4 = stack.iterator();
        //3
        System.out.println(it4.next());
        it4.remove();
        //2
        System.out.println(it4.next());
        it4.remove();
        //1
        System.out.println(it4.next());
        //[1]
        System.out.println(stack);
        //size=1
        System.out.println("size=" + stack.size());
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(7);
        stack.push(7);
        //size=6
        System.out.println("size=" + stack.size());
        //[1, 5, 6, 7, 7, 7]
        System.out.println(stack);
        it4 = stack.iterator();
        it4.next();
        it4.remove();
        //exception
        try {
            it4.remove();
        }catch (IllegalStateException e){
            System.out.println("exception");
        }*/
    }
}
