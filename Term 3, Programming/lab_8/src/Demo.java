import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Yule Cat on 02.11.2017.
 */
public class Demo {
    public static void main(String[] args) {
        System.out.println("Integer Tree:");
        Tree<Integer> intTree = new Tree();
        intTree.insert(7);
        intTree.insert(12);
        intTree.insert(1);
        intTree.insert(3);
        intTree.insert(8);
        intTree.insert(11);

        intTree.delete(12);

        intTree.displayTree();

        intTree.traverse(1);
        intTree.find(3).displayNode();
        // -------------------------------------------------------------
        System.out.println("\n\nStudent Tree:");
        Tree<Student> studTree = new Tree();
        studTree.insert(new Student("Harry", 4, 1));
        studTree.insert(new Student("Viktor", 7, 13));
        studTree.insert(new Student("Sleepy", 2, 13));
        studTree.insert(new Student("Hungry", 2, 4));

        studTree.displayTree();

        studTree.traverse(2);
    }
}
