package com.gora;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {

    @Test
    void readerSetupTest(){
        Library l = new Library();
        Reader r = new Reader(l, "Reader 1", true);
        assertEquals("Reader 1", r.threadName);
        assertEquals(1, r.permits);
        assertEquals(true, r.test);
    }

    @Test
    void readerRunTest() throws InterruptedException {
        Library l = new Library();
        Reader r = new Reader(l, "Reader 1", true);
        r.start();
        Thread.sleep(100);
        assertEquals(4, l.getReadingRoom().availablePermits());
        assertEquals(1, l.getQueue().availablePermits());
        assertEquals(1, l.getReadersInRoom());
        assertEquals(0, l.getWritersInRoom());
        assertEquals(0, l.getReadersInQueue());
        assertEquals(0, l.getWritersInQueue());
        r.join();
    }

}