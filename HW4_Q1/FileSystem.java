import java.io.IOException;

/**
 * this interface define FileSystemTree methods
 * remove,search,printFileSystem,addDir,addFile
 */
public interface FileSystem {


    /**
     * this method remove file or directory in path
     * if directory has file or directory
     * method ask to user , do you want to delete these files or directories
     * if user say yes , method delete otherwise does not delete
     *   * if file or directory do not delete throw execption
     *      * if file or directory not exist ,throw exception
     * @param path store knowledge path of file or directory
     */
    abstract void remove(String path);
    /**
     * search is there file or directory in path. if exist return true , otherwise return false
     * @param path store knowledge path of file or directory
     * @return boolean if find directory or file true otherwise false
     */
    abstract boolean search(String path);
    /**
     * print FileSystemTree
     */
    abstract void printFileSystem();
    /**
     * create file in path
     * @param path store knowledge path of file
     * @throws IOException if file exist or wrong path, throw exception
     */
    abstract void addFile(String path) throws IOException;
    /**
     *  create directory in path
     * @param path store knowledge path of directory
     * @throws IOException if directory exist or wrong path, throw exception
     */
    abstract void addDir(String path) throws IOException;
}
