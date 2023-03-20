package com.gora;


/**
 * class representing the reader
 */
public class Reader extends Thread{
    /**
     * library object that reader wants to access
     */
    Library library;
    String threadName;
    int permits = 1;
    boolean test;
    Reader(Library l, String threadName, boolean test){
        this.library = l;
        this.threadName = threadName;
        this.test = test;
    }

    /**
     * run method of the thread
     */
    @Override
    public void run(){
        int counter = 0;
        while(true){
            try{
                this.library.request(threadName, permits);
                sleep(1000);
                this.library.finish(threadName, permits);
                sleep(1000);
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }




            counter++;
            if(counter == 3){
                break;
            }
        }
    }




}
