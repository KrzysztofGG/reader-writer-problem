package com.gora;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WriterTest {

    @Test
    void writerSetupTest(){
        Library l = new Library();
        Writer w = new Writer(l, "Writer 1", true);
        assertEquals("Writer 1", w.threadName);
        assertEquals(5, w.permits);
        assertEquals(true, w.test);
    }

    @Test
    void writerRunTest() throws InterruptedException {
        Library l = new Library();
        Writer w = new Writer(l, "Writer 1", true);
        w.start();
        Thread.sleep(100);
        assertEquals(0, l.getReadingRoom().availablePermits());
        assertEquals(1, l.getQueue().availablePermits());
        assertEquals(0, l.getReadersInRoom());
        assertEquals(1, l.getWritersInRoom());
        assertEquals(0, l.getReadersInQueue());
        assertEquals(0, l.getWritersInQueue());
        w.join();
    }

}