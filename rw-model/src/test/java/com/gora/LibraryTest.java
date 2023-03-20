package com.gora;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void librarySetupTest(){
        Library l = new Library();
        assertEquals(5, l.getReadingRoom().availablePermits());
        assertEquals(1, l.getQueue().availablePermits());
        assertEquals(0, l.getReadersInRoom());
        assertEquals(0, l.getWritersInRoom());
        assertEquals(0, l.getReadersInQueue());
        assertEquals(0, l.getWritersInQueue());
        assertEquals("Readers in room: " + Integer.toString(0) + "\nWriters in room: " +
                Integer.toString(0) + "\nReaders in queue: " + Integer.toString(0) +
                "\nWriters in queue: " + Integer.toString(0) + "\n", l.printQData());

    }
    @Test
    void readerRequestTest() throws InterruptedException {
        Library l = new Library();
        l.request("Reader 1", 1);
        assertEquals(4, l.getReadingRoom().availablePermits());
        assertEquals(1, l.getQueue().availablePermits());
        assertEquals(1, l.getReadersInRoom());
        assertEquals(0, l.getWritersInRoom());
        assertEquals(0, l.getReadersInQueue());
        assertEquals(0, l.getWritersInQueue());

    }
    @Test
    void writerRequestTest() throws InterruptedException {
        Library l = new Library();
        l.request("Writer 1", 5);
        assertEquals(0, l.getReadingRoom().availablePermits());
        assertEquals(1, l.getQueue().availablePermits());
        assertEquals(0, l.getReadersInRoom());
        assertEquals(1, l.getWritersInRoom());
        assertEquals(0, l.getReadersInQueue());
        assertEquals(0, l.getWritersInQueue());

    }
    @Test
    void readerFinishTest() throws InterruptedException {
        Library l = new Library();
        l.request("Reader 1", 1);
        l.finish("Reader 1", 1);
        assertEquals(5, l.getReadingRoom().availablePermits());
        assertEquals(1, l.getQueue().availablePermits());
        assertEquals(0, l.getReadersInRoom());
        assertEquals(0, l.getWritersInRoom());
        assertEquals(0, l.getReadersInQueue());
        assertEquals(0, l.getWritersInQueue());

    }
    @Test
    void writerFinishTest() throws InterruptedException {
        Library l = new Library();
        l.request("Writer 1", 5);
        l.finish("Writer 1", 5);
        assertEquals(5, l.getReadingRoom().availablePermits());
        assertEquals(1, l.getQueue().availablePermits());
        assertEquals(0, l.getReadersInRoom());
        assertEquals(0, l.getWritersInRoom());
        assertEquals(0, l.getReadersInQueue());
        assertEquals(0, l.getWritersInQueue());
    }

    @Test
    void twoReadersTest() throws InterruptedException {
        Library l = new Library();
        l.request("Reader 1", 1);
        l.request("Reader 2", 1);
        assertEquals(3, l.getReadingRoom().availablePermits());
        assertEquals(1, l.getQueue().availablePermits());
        assertEquals(2, l.getReadersInRoom());
        assertEquals(0, l.getWritersInRoom());
        assertEquals(0, l.getReadersInQueue());
        assertEquals(0, l.getWritersInQueue());
    }

    @Test
    void twoWritersTest() throws InterruptedException {
        Library l = new Library();
        l.request("Writer 1", 5);
        l.finish("Writer 1", 5);
        l.request("Writer 2", 5);
        assertEquals(0, l.getReadingRoom().availablePermits());
        assertEquals(1, l.getQueue().availablePermits());
        assertEquals(0, l.getReadersInRoom());
        assertEquals(1, l.getWritersInRoom());
        assertEquals(0, l.getReadersInQueue());
        assertEquals(0, l.getWritersInQueue());
    }
    @Test
    void readerWriterTest() throws InterruptedException {
        Library l = new Library();
        l.request("Reader 1", 1);
        l.finish("Reader 1", 1);
        l.request("Writer 1", 5);
        assertEquals(0, l.getReadingRoom().availablePermits());
        assertEquals(1, l.getQueue().availablePermits());
        assertEquals(0, l.getReadersInRoom());
        assertEquals(1, l.getWritersInRoom());
        assertEquals(0, l.getReadersInQueue());
        assertEquals(0, l.getWritersInQueue());

    }




}