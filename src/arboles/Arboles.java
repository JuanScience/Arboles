package arboles;

/**
 * @author Juan Carlos Salazar Muñoz
 */

import java.util.Scanner;
import java.util.regex.Pattern;

public class Arboles {
    
    private static final Scanner INGRESO = new Scanner (System.in);
    private static AVLTree tree = new AVLTree();

    public static void main(String[] args) {
        System.out.println("Bienvenido!");
        menu();
    }
    
    //menú de opciones
    public static void menu(){
        System.out.println("\n\n--------------MENÚ ÁRBOLES--------------");
        System.out.println("(0)  * Insertar caracter");
        System.out.println("(1)  * Insertar cadena");
        System.out.println("(2)  * Mostrar hojas");
        System.out.println("(3)  * Mostrar los padres");
        System.out.println("(4)  * Nivel de un registro");
        System.out.println("(5)  * Altura de un registro");
        System.out.println("(6)  * Mostrar hermano de registro");
        System.out.println("(7)  ? Mostrar ancestros de un registro");
        System.out.println("(8)  * Mostrar primos de un registro");
        System.out.println("(9)  * Mostrar PreOrden");
        System.out.println("(10) * Mostrar InOrden");
        System.out.println("(11) * Mostrar PosOrden");
        System.out.println("(12) * Borrar");
        System.out.println("(13) * Salir");
        System.out.println("----------------------------------------\n");
        System.out.print("Ingrese una opción (0-13) -> ");
        opciones(INGRESO.nextLine());
    }

    private static void opciones(String nextLine) {
        if(isNumeric(nextLine)){
            int n = Integer.parseInt(nextLine);
            switch (n){
                case 0: 
                    tree.root = tree.insertNode(tree.root, requestCharacter());
                    menu();
                case 1: requestString();
                    menu();
                case 2:
                    if (!isTreeNull()){
                        System.out.print("\nHojas: ");
                        tree.showLeaves(tree.getRoot());
                    }
                    menu();
                case 3:
                    if (!isTreeNull()){
                        String answer = " ";
                        tree.showAncestors(tree.getRoot(), requestCharacter(), answer);
                    }
                    menu();
                case 4:
                    if (!isTreeNull()){
                        int answer = 0;
                        tree.showLevel(tree.getRoot(), requestCharacter(), answer);
                    }
                    menu();
                case 5:
                    if (!isTreeNull()){                        
                        tree.showHeight(tree.getRoot(), requestCharacter());
                    }
                    menu();
                case 6:
                    if (!isTreeNull()){                        
                        tree.showBrother(null, tree.getRoot(), requestCharacter());
                    }
                    menu();
                case 7:
                    menu();
                case 8:
                    if (!isTreeNull()){
                        char character = requestCharacter();
                        int answer = 0, flag = 0;
                        tree.showCousins(tree.getRoot(), character, tree.searchLevel(tree.getRoot(), character, answer), flag);
                    }
                    menu();
                case 9:
                    if (!isTreeNull()) {
                        System.out.print("\nRecorrido Preorden: ");
                        tree.readPreOrder(tree.getRoot());
                    }
                    menu();
                case 10:
                    if (!isTreeNull()) {
                        System.out.print("\nRecorrido Inorden: ");
                        tree.readInOrder(tree.getRoot());
                    }
                    menu();
                case 11:
                    if (!isTreeNull()) {
                        System.out.print("\nRecorrido Postorden: ");
                        tree.readPostOrder(tree.getRoot());
                    }
                    menu();
                case 12:
                    if (!isTreeNull()) {
                        tree.setRoot(null);
                        System.out.print("\nÁrbol eliminado");
                    }
                    menu();
                case 13:
                    System.out.print("\nAdiós!\n\n");
                    System.exit(0);
                default:
                    System.out.println("\nIngreso no válido");
                    menu();
            }
        }else{
            System.out.println("\nIngreso no válido");
            menu();
        }
    }
    
    //Valida si un string es numérico
    public static boolean isNumeric(String s){
        if("".equals(s) || (s == null ? ("\"" + s + "\"") == null : s.equals("\"" + s + "\""))){//Si se envía un enter
            return false;
        }
        if (!Character.isDigit(s.charAt(0)) && s.charAt(0) != '-'){//Si en la primera posición no hay "-" o números
            return false;
        }
        for (int i = 1; i < s.length(); i++){//valida caracter por caracter si es dígito
            if(!Character.isDigit(s.charAt(i))){
                return false;
            }
        }
        return true;
    }
    
    //Solicita caracter e ingresa al árbol
    public static char requestCharacter() {
        System.out.print("Ingrese un caracter: ");
        String SData = INGRESO.nextLine();
        while(!verifyString(SData) || SData.length() > 1){
            System.out.print("Ingrese un caracter válido: ");
            SData = INGRESO.nextLine();
        }
        return SData.toUpperCase().charAt(0);
    }
    
    //Pide un string al usuario y lo ingresa en el árbol
    public static void requestString() {
        System.out.print("Ingrese una cadena de texto: ");
        String SData = INGRESO.nextLine();
        while(!verifyString(SData)){
            System.out.print("Ingrese una cadena válida: ");
            SData = INGRESO.nextLine();
        }
        int l = SData.length();
        SData = SData.toUpperCase();
        for (int i = 0; i < l; i++) {
            tree.root = tree.insertNode(tree.root, SData.charAt(i));
        }
    }
    
    //Retorna true si el string es alfabético y sin repeticiones
    private static boolean verifyString(String SData) {
        boolean answer = true;
        SData = SData.replaceAll("\\s","");
        answer = Pattern.matches("^[a-zA-Z]*$", SData);//Retorna false si hay un caracter no alfabético
        int l = SData.length();
        for (int i = 0; i < l && answer; i++) { //Verifica que no hayan caracteres repetidos
            for (int j = i + 1 ; j < l; j++) {
                if (SData.charAt(i) == SData.charAt(j))
                    answer = false;
            }
        }
        return answer;
    }

    private static boolean isTreeNull() {
        if (tree.getRoot() == null) {
            System.out.println("\nNo hay nada para ver");
            return true;
        }else
            return false;   
    }
    
}
