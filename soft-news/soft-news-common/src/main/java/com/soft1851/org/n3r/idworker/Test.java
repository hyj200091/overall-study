package com.soft1851.org.n3r.idworker;

public class Test {

    public static void main(String[] args) {
        com.soft1851.org.n3r.idworker.Sid sid = new com.soft1851.org.n3r.idworker.Sid();
        for (int i = 0; i < 1000; i++) {
            System.out.println(sid.nextShort());
        }
    }

}
