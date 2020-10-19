import java.io.*;
import java.util.Scanner;


/** CT2109 NUI Galway:
 * Class to demonstrate the use of BinaryTree code. 
 * Based on code by Carrano & Savitch.
 * @author Michael Madden.
 */

public class BinaryTreeDemo implements Serializable {

    FileOutputStream fout = new FileOutputStream("BinaryTree.ser"); // File to output to
    ObjectOutputStream oos = new ObjectOutputStream(fout);

    public BinaryTreeDemo() throws IOException {

    }


    public static void main(String[] args) throws IOException {
        // Create a tree
        System.out.println("Constructing a test tree ...");
        BinaryTree<String> testTree = new BinaryTree<String>();
        createTree1(testTree);

        playGame(testTree);

    } // end of main


    public static void createTree1(BinaryTree<String> tree) {
        // First the leaves
        BinaryTree<String> pTree = new BinaryTree<String>("Is it a Duck-Billed Platypus?");
        BinaryTree<String> qTree = new BinaryTree<String>("Is it a Dolphin?");

        BinaryTree<String> rTree = new BinaryTree<String>("Is it a Yorkie?");
        BinaryTree<String> sTree = new BinaryTree<String>("Is it a cat?");

        BinaryTree<String> tTree = new BinaryTree<String>("Is it a Penguin?");
        BinaryTree<String> uTree = new BinaryTree<String>("Is it a Emu?");


        BinaryTree<String> vTree = new BinaryTree<String>("Is it an Aligator?");
        BinaryTree<String> wTree = new BinaryTree<String>("Is it an Crocodile?");

        BinaryTree<String> xTree = new BinaryTree<String>("Is it a cheesecake?");
        BinaryTree<String> yTree = new BinaryTree<String>("Is it a Cgocolate Cake?");

        BinaryTree<String> zTree = new BinaryTree<String>("Is it chocolate ice cream?");
        BinaryTree<String> a1Tree = new BinaryTree<String>("Is it vanilla ice cream?");

        BinaryTree<String> b1Tree = new BinaryTree<String>("Is it a Redwood Tree?");
        BinaryTree<String> c1Tree = new BinaryTree<String>("Is it a Yew Tree?");

        BinaryTree<String> d1Tree = new BinaryTree<String>("Is it Mortal Kombat 11?");
        BinaryTree<String> e1Tree = new BinaryTree<String>("Is it Call of Duty 11?");

        ///////////////////////


        BinaryTree<String> hTree = new BinaryTree<String>("Does it feature in a Cartoon?", pTree, qTree);
        BinaryTree<String> iTree = new BinaryTree<String>("Is it a Dog?", rTree, sTree);

        BinaryTree<String> jTree = new BinaryTree<String>("Can it fly?", tTree, uTree);
        BinaryTree<String> kTree = new BinaryTree<String>("Is it a reptile?", vTree, wTree);

        BinaryTree<String> lTree = new BinaryTree<String>("Is it a cake?", xTree, yTree);
        BinaryTree<String> mTree = new BinaryTree<String>("Is it ice cream?", zTree, a1Tree);

        BinaryTree<String> nTree = new BinaryTree<String>("Is it tall?", b1Tree, c1Tree);
        BinaryTree<String> oTree = new BinaryTree<String>("Is it a video game?", d1Tree, e1Tree);

        // Next Sub trees
        BinaryTree<String> dTree = new BinaryTree<String>("Does it lay eggs?", hTree, iTree);
        BinaryTree<String> eTree = new BinaryTree<String>("Is it a Bird?", jTree, kTree);

        BinaryTree<String> fTree = new BinaryTree<String>("Is it a dessert?", lTree, mTree);
        BinaryTree<String> gTree = new BinaryTree<String>("Is it a tree?", nTree, oTree);
        ///////////////

        // Now the subtrees joining leaves:
        BinaryTree<String> bTree = new BinaryTree<String>("Is it a Mammal?", dTree, eTree);
        BinaryTree<String> cTree = new BinaryTree<String>("Is it a food?", fTree, gTree);

        // Now the root
        tree.setTree("Are you thinking of an animal?", bTree, cTree);

    } // end createTree1

    public static void playGame(BinaryTree tree){

        displayStats(tree);


        Scanner scanner = new Scanner(System.in); // scanner
        String usrAns;

        while (true) {

            System.out.println("Would you like to play? Type: 1");
            System.out.println("Exit? Type: 2");
            System.out.println("Load from save? Type: 3");
            usrAns = scanner.nextLine();


            // Quit?
            if (usrAns.equals("2")) {
                System.exit(0);
            }
            // Load
            else if (usrAns.equals("3")) {
                tree = deserializeTree("BinaryTree.ser");
            }


            // Play again?
            if (usrAns.equals("1")) {

                BinaryNodeInterface<String> currentNode = tree.getRootNode(); // Get the current node we are at
                currentNode.getData();

                while (!currentNode.isLeaf()) { // If we are not at the end of the tree

                    System.out.println(currentNode.getData()); // print the question
                    usrAns = scanner.nextLine();

                    if (usrAns.toLowerCase().equals("yes")) {
                        currentNode = currentNode.getLeftChild(); // if yes get the left node
                    } else if (usrAns.toLowerCase().equals("no")) {
                        currentNode = currentNode.getRightChild(); // if no get the right node
                    }
                    else {
                        System.out.println("Not a vaild input");
                    }
                }


                //Present guess to user, and store their answer
                System.out.println(currentNode.getData());
                usrAns = scanner.nextLine();


                //Compare answer to guess. Two possibilities to proceed:
                //1. Answer is correct. Display options for user to continue

                if (usrAns.equals("yes")) { // Guess was right
                    System.out.println("Great! Would you like to play again? Type: 1");
                    System.out.println("Exit? Type: 2");
                    System.out.println("Save? Type: 3");
                    System.out.println("Load from save? Type: 4");
                    usrAns = scanner.nextLine();

                    // Play again?
                    if (usrAns.equals("1")) {
                        playGame(tree);
                    }
                    // Quit?
                    else if (usrAns.equals("2")) {
                        System.exit(0);
                    } else if (usrAns.equals("3")) {
                        serializeTree(tree); // save the tree to a file
                    } else if (usrAns.equals("4")) {
                        tree = deserializeTree("BinaryTree.ser"); // Load the tree from a file
                    }
                    else {
                        System.out.println("Not a vaild input");
                    }

                }

                //2. The answer was wrong. We need to expand the tree.
                //Get new question from user
                //Replace the current node with this question, and add //left& right children with the answers

                else if (usrAns.equals("no")) {
                    // Get correct answer
                    System.out.println("I DONT KNOW: What is the correct answer?");
                    usrAns = scanner.nextLine(); // get new answer

                    System.out.println("Distinguishing Question? ");
                    String usrQuestion = scanner.nextLine(); // get new question

                    BinaryNodeInterface usrAnswerNode = new BinaryNode(usrAns); // create new binary node with the users answer

                    System.out.println("Right question for " + currentNode.getData());
                    String leftOrRight = scanner.nextLine(); // right node or left node?

                    // place their answer to the right
                    if (leftOrRight.toLowerCase().equals("yes")) {
                        currentNode.setLeftChild(currentNode);
                        currentNode.setRightChild(usrAnswerNode);
                    }

                    // place their answer to the left
                    else if (leftOrRight.toLowerCase().equals("no")) {
                        currentNode.setRightChild(currentNode);
                        currentNode.setLeftChild(usrAnswerNode);
                    }
                    currentNode.setData(usrQuestion); // set their provided question to the current node
                }
                else {
                    System.out.println("Not a vaild input");
                }
            }
            else {
                System.out.println("Not a vaild input");
            }
        }
    }

    // Save their tree to a file
    public static void serializeTree(BinaryTree tree) {

        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        try {

            fout = new FileOutputStream("BinaryTree.ser");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(tree);

            System.out.println("Finshed Saving");

        } catch (Exception ex) {

            ex.printStackTrace();

        } finally {

            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    // Load their save from a file
    public static BinaryTree deserializeTree(String filename) {

        BinaryTree tree = null;

        FileInputStream fin = null;
        ObjectInputStream ois = null;

        try {

            fin = new FileInputStream(filename);
            ois = new ObjectInputStream(fin);
            tree = (BinaryTree) ois.readObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return tree;

    }

    public static void displayStats(BinaryTree<String> tree)
    {
        if (tree.isEmpty())
            System.out.println("The tree is empty");
        else
            System.out.println("The tree is not empty");

        System.out.println("Root of tree is " + tree.getRootData());
        System.out.println("Height of tree is " + tree.getHeight());
        System.out.println("No. of nodes in tree is " + tree.getNumberOfNodes());
    } // end displayStats

}
