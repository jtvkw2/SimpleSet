package cpts132.data_structures;

class Node{
    Node left;
    Node right;
    Object data;

    public Node( Object data){
        this.left = null;
        this.right = null;
        this.data = data;
    }
}


public class MySimpleSet implements SimpleSet{
    private Node root;
    int count;
    String output;
    boolean duplicate;

    public MySimpleSet(){
        this.count = 0;
    }

    @Override
    public boolean add(Object o) {
        duplicate = false;
        this.count += 1;
        root = insert(root, o);
        return duplicate;
    }

    public Node insert(Node current, Object o){
        if(current == null){
            return new Node(o);
        }

        if((int)o < (int)current.data){
            current.left = insert(current.left, o);
        }else if((int)o > (int)current.data){
            current.right = insert(current.right, o);
        }else{
            duplicate = true;
            return current;
        }

        return current;
    }

    @Override
    public void clear() {
        this.root = null;
        this.count = 0;
    }

    @Override
    public boolean contains(Object o) {
        return get(root, o);
    }

    private boolean get(Node current, Object o){
        if(current == null){
            return false;
        }
        if(o == current.data){
            return true;
        }
        return (int)o < (int)current.data ? get(current.left, o) : get(current.right, o);
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public String toString(){
        output = "";
        output += "Level Order:";
        levelOrderString(root);
        output += "\nPreOrder:";
        preOrderString(root);
        output += "\nPost Order";
        postOrderString(root);
        output += "\nInOrder";
        inOrderString(root);
        return output;
    }

    private void inOrderString(Node current){
        if(current != null){
            output += ('(');
            preOrderString(current.left);
            output += (')');
            output += current.data.toString().charAt(0);
            output += ('(');
            preOrderString(current.right);
            output += (')');
        }
    }

    private void preOrderString(Node current){
        if(current != null){
            output += current.data.toString().charAt(0);
            output += ('(');
            preOrderString(current.left);
            output += (')');
            output += ('(');
            preOrderString(current.right);
            output += (')');
        }
    }

    private void postOrderString(Node current){
        if(current != null){
            output += ('(');
            postOrderString(current.left);
            output += (')');
            output += ('(');
            postOrderString(current.right);
            output += (')');
            output += current.data.toString().charAt(0);
        }
    }

    private void levelOrderString(Node current){
        if(current == null){
            return;
        }
        output += current.data.toString().charAt(0);
        if(current.left == null && current.right == null){
            return;
        }

        output += ('(');
        levelOrderString(current.left);
        output += (')');

        if(current.right != null){
            output += ('(');
            levelOrderString(current.right);
            output += (')');
        }
    }

    public static void main(String[] args) {
        MySimpleSet simple = new MySimpleSet();
        simple.add(5);
        simple.add(4);
        simple.add(7);
        simple.add(2);
        simple.add(6);
        simple.add(9);
        simple.add(3);
        simple.add(8);
        System.out.println(simple);
        System.out.println();
        System.out.println("Tree Current Size: " + simple.size());
        System.out.println("Adding 8 - Is it a Duplicate: "+ simple.add(8));
        System.out.println("Adding 10 - Is it a Duplicate: "+ simple.add(10));
        System.out.println("Does the tree contain 10: "+ simple.contains(10));
        System.out.println("Is the tree empty: "+ simple.isEmpty());
        simple.clear();
        System.out.println("Clearing tree");
        System.out.println("Is the tree empty: "+ simple.isEmpty());
    }
}
