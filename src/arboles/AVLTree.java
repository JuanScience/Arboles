package arboles;

public class AVLTree {
    Node root;

    //Método set del parámetro root
    public void setRoot(Node root) {
        this.root = root;
    }

    //Método get del parámetro root
    public Node getRoot() {
        return root;
    }
    
    //Retorna la altura de un nodo
    public int height(Node N) {
        if (N == null)
            return 0;
        return N.getHeight();
    }

    //Devuelve el mayor de dos números
    public int max(int a, int b) {
        if (a > b)
            return a;
        else
            return b;
    }

    // Inserta un nuevo nodo
    public Node insertNode(Node node, char data) {
        if (node == null)
            return (new Node(data));
        if (data < node.getData())
            node.setLeft(insertNode(node.getLeft(), data));
        else if (data > node.getData())
            node.setRight(insertNode(node.getRight(), data));
        else
            return node;

        // Actualiza el factor de balance
        node.setHeight(1 + max(height(node.getLeft()), height(node.getRight())));
        int balanceFactor = getBalanceFactor(node);
        // Si hace falta, balancea el arbol
        if (balanceFactor > 1) {
            if (data < node.getLeft().getData()) {
                return rightRotate(node);
            } else if (data > node.getLeft().getData()) {
                node.setLeft(leftRotate(node.getLeft()));
                return rightRotate(node);
            }
        }
        if (balanceFactor < -1) {
            if (data > node.getRight().getData()) {
                return leftRotate(node);
            } else if (data < node.getRight().getData()) {
                node.setRight(rightRotate(node.getRight()));
                return leftRotate(node);
            }
        }
        return node;
    }
    
        //Rotación a la derecha
    public Node rightRotate(Node p) {
        Node q = p.getLeft();
        Node r = q.getRight();
        q.setRight(p);
        p.setLeft(r);
        p.setHeight(max(height(p.getLeft()), height(p.getRight())) + 1);
        q.setHeight(max(height(q.getLeft()), height(q.getRight())) + 1);
        return q;
    }

    //Rotación a la izquierda
    public Node leftRotate(Node p) {
        Node q = p.getRight();
        Node r = q.getLeft();
        q.setLeft(p);
        p.setRight(r);
        p.setHeight(max(height(p.getLeft()), height(p.getRight())) + 1);
        q.setHeight(max(height(q.getLeft()), height(q.getRight())) + 1);
        return q;
    }

    // Devuelve el factor de balance del nodo
    public int getBalanceFactor(Node n) {
        if (n == null)
            return 0;
        return height(n.getLeft()) - height(n.getRight());
    }

    public Node nodeWithMimumValue(Node node) {
        Node current = node;
        while (current.getLeft() != null)
            current = current.getLeft();
        return current;
    }
  
    public void readPreOrder(Node n){
        if (n != null) {
            System.out.print(n.getData() + " ");
            readPreOrder(n.getLeft());
            readPreOrder(n.getRight());
        }
    }
    
    public void readInOrder(Node n){
        if (n != null) {
            readInOrder(n.getLeft());
            System.out.print(n.getData() + " ");
            readInOrder(n.getRight());
        }
    }
    
    public void readPostOrder(Node n){
        if (n != null) {
            readPostOrder(n.getLeft());
            readPostOrder(n.getRight());
            System.out.print(n.getData() + " ");
        }
    }

    public void showLeaves(Node n) {
        if (n != null) {
            if(n.getLeft() == null && n.getRight() == null)
                System.out.print(n.getData() + " ");
            showLeaves(n.getLeft());
            showLeaves(n.getRight());
        }
    }
    
    public void showAncestors(Node node, char data, String answer) {
        if (node == null)
            System.out.println("No se ha encontrado el elemento");
        else if (data < node.getData()){
            answer = answer + " " + node.getData();
            showAncestors(node.getLeft(), data, answer);
        }else if (data > node.getData()){
            answer = answer + " " + node.getData();
            showAncestors(node.getRight(), data, answer);
        }            
        else
            System.out.println("Los padres de " + data + " son:" + answer);
    }

    public void showLevel(Node node, char data, int answer) {
        if (node == null)
            System.out.println("No se ha encontrado el elemento");
        else if (data < node.getData()){
            answer++;
            showLevel(node.getLeft(), data, answer);
        }else if (data > node.getData()){
            answer++;
            showLevel(node.getRight(), data, answer);
        }            
        else{
            answer++;
            System.out.println("El nivel de " + data + " es: " + answer);
        }
    }

    public void showHeight(Node node, char data) {
        if (node == null)
            System.out.println("No se ha encontrado el elemento");
        else if (data < node.getData()){
            showHeight(node.getLeft(), data);
        }else if (data > node.getData()){
            showHeight(node.getRight(), data);
        }            
        else{
            System.out.println("La altura de " + data + " es: " + node.getHeight());
        }
    }

    public void showBrother(Node node, char data) {
        if (data < node.getData()){
            if(node.getLeft() != null)
                if (node.getRight() == null)
                    System.out.println(data + " no tiene hermano");
                else if(node.getLeft().getData() == data)
                    System.out.println("El hermano de " + data + " es: " + node.getRight().getData());
                else
                    showBrother(node.getLeft(), data);
            else
                System.out.println("No se encontró el dato");
        }else if (data > node.getData()){
            if(node.getRight() != null)
                if (node.getLeft() == null)
                    System.out.println(data + " no tiene hermano");
                else if(node.getRight().getData() == data)
                    System.out.println("El hermano de " + data + " es: " + node.getLeft().getData());
                else
                    showBrother(node.getRight(), data);
            else
                System.out.println("No se encontró el dato");
        }            
        else
            System.out.println(data + " es la raíz del árbol");
    }

    public void showCousinsComplete(Node father, Node node, char data, int level, int flag) {
        
        if (level > 2 && father != null){

            if (node != null){
                if (node != father){
                    flag++;
                    showCousinsComplete(father, node.getLeft(), data, level, flag);
                    showCousinsComplete(father, node.getRight(), data, level, flag);
                }
                if (flag == level)
                    System.out.print(node.getData() + " ");
            }
        }else{
            System.out.println("El objeto está en la raiz o en los primeros hijos de esta o no existe. No hay primos");
        }
    }
    
    public void showCousins(Node root,char data) {
        Node father = searchFather(root, data);
        Node grandfather = null;
        if(father != null)
            grandfather = searchFather(root, father.getData());
        if (grandfather != null){
            if (grandfather.getData() < data){
                if (grandfather.getLeft() != null){
                    if(grandfather.getLeft().getLeft() != null)
                        System.out.print(grandfather.getLeft().getLeft().getData());
                    if(grandfather.getLeft().getRight() != null)
                        System.out.print(grandfather.getLeft().getRight().getData());
                }
            }else{
                if (grandfather.getRight()!= null){
                    if(grandfather.getRight().getLeft() != null)
                        System.out.print(grandfather.getRight().getLeft().getData());
                    if(grandfather.getRight().getRight() != null)
                        System.out.print(grandfather.getRight().getRight().getData());
                }
            }
        }else{
            System.out.println("El objeto está en la raiz o en los primeros hijos de esta o no existe. No hay primos");
        }
    }
    
    public Node searchFather(Node node, char data){
        if (data < node.getData()){
            if(node.getLeft() != null)
                if (node.getLeft().getData() == data)
                    return node;
                else
                    return searchFather(node.getLeft(), data);
            else
                return null;
        }else if (data > node.getData()){
            if(node.getRight()!= null)
                if (node.getRight().getData() == data)
                    return node;
                else
                    return searchFather(node.getRight(), data);
            else
                return null;
        }            
        else
            return null;
    }
    
    public int searchLevel(Node node, char data, int answer){
        if (node == null)
            return 0;
        else if (data < node.getData()){
            answer++;
            return searchLevel(node.getLeft(), data, answer);
        }else if (data > node.getData()){
            answer++;
            return searchLevel(node.getRight(), data, answer);
        }            
        else
            answer++;
            return answer;
    }

    void showFathers(Node n) {
        if (n != null) {
            if(n.getLeft() != null || n.getRight() != null)
                System.out.print(n.getData() + " ");
            showFathers(n.getLeft());
            showFathers(n.getRight());
        }
    }
    

}
