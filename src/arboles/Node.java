package arboles;

public class Node {
    char data;
    int height;
    Node left, right;

    //Constructor
    public Node(char d) {
      data = d;
      height = 1;
    }

    //Métodos set
    public void setData(char data) {
        this.data = data;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    //Métodos get
    public char getData() {
        return data;
    }

    public int getHeight() {
        return height;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    void showUs(Node node, char data, int searchLevel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
