package com.gora;
import java.util.concurrent.*;

/**
 *Class representing the library
 * @author krzys
 *
 */
public class Library {
    static final int MAX_PERMITS = 5;
    private final Semaphore readingRoom;
    private final Semaphore queue;
    private int readersInQueue = 0;
    private int writersInQueue = 0;
    private int readersInRoom = 0;
    private int writersInRoom = 0;

    public Library(){
        this.readingRoom = new Semaphore(MAX_PERMITS, true);
        this.queue = new Semaphore(1, true);
    }


    /**
     * method responsible for printing data about the library
     * @return message - string containing data about the library
     */
    String printQData(){
        return "Readers in room: " + Integer.toString(readersInRoom) + "\nWriters in room: " +
                Integer.toString(writersInRoom) + "\nReaders in queue: " + Integer.toString(readersInQueue) +
                "\nWriters in queue: " + Integer.toString(writersInQueue) + "\n";
    }

    /**
     * method responsible for requesting access to the library
     * @param threadName - name of the thread requesting access
     * @param permits - number of permits to be acquired
     */
    public void request(String threadName, int permits) throws InterruptedException {
        Logger.log(Logger.Color.BLUE, threadName + " wants to enter the library.\n");

        synchronized (this){
            if(permits==5)
                writersInQueue++;
            else
                readersInQueue++;
        }

        queue.acquire(1);
        readingRoom.acquire(permits);

        Logger.log(Logger.Color.BLUE, threadName + " is reading");
        synchronized (this){
            if(permits==5){
                writersInQueue--;
                writersInRoom++;
            }
            else{
                readersInQueue--;
                readersInRoom++;
            }

            Logger.log(Logger.Color.GREEN, printQData());
        }
        try{
            Thread.sleep(10);
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        queue.release(1);

    }

    /**
     * method responsible for finishing access to the library
     * @param threadName - name of the thread finishing access
     * @param permits - number of permits to be released
     */
    public void finish(String threadName, int permits){

        Logger.log(Logger.Color.WHITE, threadName + " has left the library.\n");
        readingRoom.release(permits);
        if(permits==5)
            writersInRoom--;
        else
            readersInRoom--;
    }


    public Semaphore getReadingRoom() {
        return readingRoom;
    }


    public Semaphore getQueue() {
        return queue;
    }

    public int getReadersInQueue() {
        return readersInQueue;
    }

    public int getWritersInQueue() {
        return writersInQueue;
    }

    public int getReadersInRoom() {
        return readersInRoom;
    }

    public int getWritersInRoom() {
        return writersInRoom;
    }
}
