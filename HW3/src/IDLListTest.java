public class IDLListTest<E>{
    public static void main(String[] args){
        IDLList a = new IDLList();
        a.add(10);
        a.append(5);
        a.append(20);
        //10,5,20
        a.add(2,13);
        //10,5,13,20
        a.add(3,18);
        ////10,5,13,18,20
        a.add(4,13);
        ////10,5,13,18,13,20
        a.add(3,16);
        ////10,5,13,16,18,13,20
        a.append(133);
        ////10,5,13,16,18,13,20,133
        System.out.println(a);
        System.out.println(a.getHead());
        System.out.println(a.getLast());
        System.out.println(a.toString());
        System.out.println(a.remove());
        System.out.println(a.removeLast());
        System.out.println(a);
        System.out.println(a.removeAt(5));
        System.out.println(a.remove(13));
        System.out.println(a.remove(135));
        System.out.println(a.toString());
        System.out.println(a.size());
    }
}
