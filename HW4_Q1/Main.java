import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * this class test methods of FileSystemTree
 * these methods search, addDir, addFile, printFileSystem, and remove methods
 */
public class Main {
    public static void main(String[] args) throws IOException {

        /**
         * if flag 1 directory otherwise file
         * firstly create a root directory
         */
        FileSystemTree fileSystemTree = new FileSystemTree("C:\\Root",1);


        fileSystemTree.addDir("C:\\Root\\fATİH");
        fileSystemTree.addDir("C:\\Root\\fATİH\\oguz");
        fileSystemTree.addDir("C:\\Root\\fATİH\\mesut");
        fileSystemTree.addDir("C:\\Root\\fATİH\\kayzer");
        fileSystemTree.addFile("C:\\Root\\fATİH\\kayzer\\1.txt");
        fileSystemTree.addFile("C:\\Root\\fATİH\\mesut\\2.txt");
        fileSystemTree.addFile("C:\\Root\\fATİH\\oguz\\3.txt");
        fileSystemTree.addFile("C:\\Root\\fATİH\\4.txt");
        fileSystemTree.addFile("C:\\Root\\fATİH\\45.txt");
        System.out.println("*************************************");
        fileSystemTree.printFileSystem();
        System.out.println("*************************************");

        fileSystemTree.remove("C:\\Root\\fATİH\\kayzer78");

        fileSystemTree.remove("C:\\Root\\fATİH\\kayzer\\1.txt");

        System.out.println(fileSystemTree.search("C:\\Root\\fATİH\\kayzer"));
        System.out.println(fileSystemTree.search("C:\\Root\\fATİH"));
        System.out.println(fileSystemTree.search("C:\\Root\\fATİH\\kayzer46"));




    }
}
