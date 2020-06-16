package Mxstar;

import java.io.FileInputStream;
import java.io.PrintStream;

public class Configuration{
    public static int REGISTER_WIDTH = 4;
     public enum ALLOCATOR {
        NAIVE_ALLOCATOR, GRAPH_ALLOCATOR
    }
    public static ALLOCATOR allocator = ALLOCATOR.GRAPH_ALLOCATOR;

    public static boolean doGlobalAllocate = false;

}