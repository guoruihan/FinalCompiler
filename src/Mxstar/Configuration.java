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
    /*2 public static boolean PrintIRAfterBuild = false;
    public static boolean PrintIRAfterCorrector = false;
    public static boolean PrintIRAfterAllocator = false;
    public static boolean PrintIRAfterAll = false;*/


    //all the optimizers which is not those kind of definitely good for the compiler will have a option here
    /*2 public static boolean doVNOptimize = true; // may extend the UD-list so may don't have such good result
<<<<<<< HEAD

=======
    public static boolean doGlobalAllocate = false;
>>>>>>> 19de20b34c4fb43d7987a1307112529b859578ed

    public static boolean doInline = true;
    public static int inlineLimit = 40;
    public static int inlineDepth = 3;

    public static boolean doMemorization = false;*/
}