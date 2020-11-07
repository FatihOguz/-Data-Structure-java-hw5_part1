
/**
 * create File or directory
 */

import java.io.File;
/**
 * for file exist
 */
import java.io.IOException;
/**
 * for FileSystemTree children
 */
import java.util.LinkedList;
/**
 * if directory has other file or directory,ask to user that  want to delete directory
 */
import java.util.Scanner;

/**
 * this class has File Tree
 * tree hold file or directory.
 * this class add,remove and find file or directory in tree
 * this class print tree
 *
 */
public class FileSystemTree implements FileSystem{

    /**
     * this class hold file and name of file
     * this class inner class in FileSystemTree
     */
    private static class FileNode {
        /**
         * store file or directoy field
         */
        private File file;
        /**
         * store name of file or directory
         */
        private String name;
        /**
         * store children of  file or directory
         */
        private LinkedList<FileNode> Child = new LinkedList<FileNode>();

        /**
         * if flag equals 1 create directory otherwise create file
         * @param path store knowledge path of file or directory
         * @param flag if flag is 1 create a directory otherwise create a file
         * @throws IOException if file or directory exist or wrong path, throw exception
         */
        private FileNode(String path, int flag) throws IOException {

            file = new File(path);
            if (flag == 1) {
                if (!file.exists()) {
                     file.mkdir();
                }

            } else {
                if (!file.exists()) {
                    file.createNewFile();
                }
            }
            String[] str = path.split("\\\\");
            this.name = str[str.length - 1];

        }

        /**
         * print directory or file
         * @return
         */
        @Override
        public String toString() {
            return "FileNode{" +
                    "file=" + file +
                    '}';
        }
    }

    /**
     * root is a directory
     * start FileSystemTree
     */
    FileNode root;

    /**
     * if flag equals 1 create directory otherwise create file
     * @param path store knowledge path of file or directory
     * @param flag if flag is 1 create a directory otherwise create a file
     * @throws IOException if file or directory exist or wrong path, throw exception
     */
    public FileSystemTree(String path, int flag) throws IOException {
        root = new FileNode(path, flag);
    }

    /**
     * create directory
     * @param path store knowledge path of directory
     * @throws IOException if directory exist or wrong path, throw exception
     */
    public FileSystemTree(String path) throws IOException {
        root = new FileNode(path,1);
    }


    /**
     *no parameter constructor
     * create Root directory
     * @throws IOException if directory exist or wrong path, throw exception
     */
    public FileSystemTree() throws IOException {
        root = new FileNode("C:\\Root",1);
    }

    /**
     * this method help addDir(String path) method
     * @param path  store knowledge path of directory
     * @param subRoot is nth depth child of root
     * @throws IOException if directory exist or wrong path, throw exception
     */
    private void addDir(String path, FileNode subRoot) throws IOException {
        String[] str = path.split("\\\\");

        if(subRoot.name.equals(str[str.length-2])){
            subRoot.Child.add(new FileNode(path,1));
        }

        for (int i = 0; i < subRoot.Child.size(); i++) {
            if (subRoot.Child.get(i).name.equals( str[str.length - 2]))
            {
                subRoot.Child.get(i).Child.add(new FileNode(path, 1));
            }
            else{
                addDir(path, subRoot.Child.get(i));
            }
        }
    }

    /**
     *  create directory in path
     * @param path store knowledge path of directory
     * @throws IOException if directory exist or wrong path, throw exception
     */
    public void addDir(String path) throws IOException {
        addDir(path,root);
    }

    /**
     * create file in path
     * @param path store knowledge path of file
     * @throws IOException if file exist or wrong path, throw exception
     */
    public void addFile(String path) throws IOException {
        addFile(path,root);
    }

    /**
     * this method help addFile(String path) method
     * @param path  store knowledge path of file
     * @param subRoot is nth depth child of root
     * @throws IOException if file exist or wrong path, throw exception
     */
    private void addFile(String path,FileNode subRoot) throws IOException {
        String[] str = path.split("\\\\");
        if(subRoot.name.equals(str[str.length-2])){

            subRoot.Child.add(new FileNode(path,0));
        }

        for (int i = 0; i < subRoot.Child.size(); i++) {
            if (subRoot.Child.get(i).name.equals( str[str.length - 2]))
            {
                subRoot.Child.get(i).Child.add(new FileNode(path, 0));
            }
            else{
                addFile(path, subRoot.Child.get(i));
            }
        }
    }

    /**
     * print FileSystemTree
     */
    public void printFileSystem(){
        printFileSystem(root);
    }

    /**
     * print nth depth child of root of FileSystemTree
     * @param subRoot  nth depth child of root of FileSystemTree
     */
    public void printFileSystem(FileNode subRoot){
        System.out.println(subRoot.name);
        if(subRoot.Child.size()>0){

            for(int i=0;i<subRoot.Child.size();i++){

                printFileSystem(subRoot.Child.get(i));
            }
        }

    }

    /**
     * this method help search(String path) method
     * @param path store knowledge path of file or directory
     * @param subRoot  nth depth child of root of FileSystemTree
     * @return boolean if find directory or file true otherwise false
     */
    private boolean search(String path,FileNode subRoot){
        String[] str = path.split("\\\\");
        if(subRoot.name.equals(str[str.length-1])){
            return true;
        }
       if(subRoot.Child.size()>0){
           for(int i=0;i<subRoot.Child.size();i++){
               boolean res = search(path,subRoot.Child.get(i));
               if(res==true){
                   return true;
               }
           }
       }
        return false;
    }

    /**
     * search is there file or directory in path. if exist return true , otherwise return false
     * @param path store knowledge path of file or directory
     * @return boolean if find directory or file true otherwise false
     */
    public boolean search(String path){
        return search(path,root);
    }

    /**
     * this method remove file or directory in path
     * if directory has file or directory
     * method ask to user , do you want to delete these files or directories
     * if user say yes , method delete otherwise does not delete
     *   * if file or directory do not delete throw execption
     *      * if file or directory not exist ,throw exception
     * @param path store knowledge path of file or directory
     */
    public void remove(String path){

        remove(path,root);
        if(!search(path)){
            System.out.println("the path cannot be found");
        }

    }

    /**
     * if directory has file or directory
     * method ask to user , do you want to delete these files or directories
     * if user say yes , method delete otherwise does not delete
     *   * if file or directory do not delete throw execption
     *      * if file or directory not exist ,throw exception
     * @param path store knowledge path of file or directory
     * @param subRoot nth depth child of root of FileSystemTree
     */
    private void remove(String path,FileNode subRoot){
        String[] str = path.split("\\\\");


        if(subRoot.name.equals(str[str.length-1])){
            if(subRoot.Child.size()==0){
                subRoot.file.delete();
            }

           if(subRoot.file.exists()){
               subRoot.file.list();
               if(subRoot.file.delete()){
                   System.out.println("delete");
               }
               else{
                   System.out.println("not deleted");
               }
           }
           else{
               //System.out.println("not exist");
           }
        }
        else{

            for (int i = 0; i < subRoot.Child.size(); i++) {
                if (subRoot.Child.get(i).name.equals( str[str.length - 1]))
                {

                    if(subRoot.Child.get(i).Child.size()>0){
                        System.out.println("directory includes some other directories (or files)");
                        printFileSystem(subRoot.Child.get(i));
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("if you want to remove--> write yes otherwise write no");
                        String answer = scanner.nextLine();
                        if(answer.equals("yes")){
                            helpRemove(subRoot.Child.get(i));
                            subRoot.Child.get(i).file.list();
                        }
                        else{
                            return;
                        }

                    }
                    if(subRoot.Child.get(i).file.exists()){
                        subRoot.Child.get(i).file.list();
                        if(subRoot.Child.get(i).file.delete()){
                            System.out.println("delete");
                        }
                        else {
                            System.out.println("not deleted");
                        }
                    }
                    else{
                       // System.out.println("not exist");
                    }
                }
                else{
                    remove(path, subRoot.Child.get(i));
                }
            }
        }


    }

    /**
     * this method help remove(String path,FileNode subRoot) method
     * if directory has file or directory
     * method ask to user , do you want to delete these files or directories
     * if user say yes , method delete otherwise does not delete
     *   * if file or directory do not delete throw execption
     *      * if file or directory not exist ,throw exception
     * @param node
     */
    private void helpRemove(FileNode node){
       if(node.Child.size()>0){
           for(int i = 0 ; i< node.Child.size();i++){
               helpRemove(node.Child.get(i));
                node.Child.get(i).file.delete();
           }

       }
       else {
          node.file.delete();
           if(node.file.isFile()){
               System.out.println(node.name);

               if(node.file.isFile()){
                   node.file.list();
               }
               if(node.file.delete()){

                   System.out.println("deleted");
               }
               else{
                   System.out.println("not delete");
               }
           }

           if(node.file.isDirectory()){
               System.out.println("directory");
               node.file.delete();
           }

           else{
             //  System.out.println("not exist");
           }

       }

    }

}






