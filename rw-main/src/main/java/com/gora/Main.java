package com.gora;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Library library = new Library();
        ArrayList<Thread> threads = new ArrayList<>();
//        for(int i=1; i<=3; i++){
//            threads.add(new Writer(library, "Writer " + i, false));
//        }
//        for(int i=1; i<=10; i++){
//            threads.add(new Reader(library, "Reader " + i, false));
//        }
        for(int i=1; i<=12; i++){
            if ((i-1)%5 ==0){
                threads.add(new Writer(library, "Writer " + i, false));
            }
            else{
                threads.add(new Reader(library, "Reader " + i, false));
            }
        }
        for(Thread thread : threads){
            thread.start();
            Thread.sleep(50);
        }

    }
}
