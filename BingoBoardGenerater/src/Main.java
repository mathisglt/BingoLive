
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Integer[][] board = boardInit(randomBoardSchema());
        printBoard(board);
    }

    /**
     * Affiche une planche de loto
     * 
     * @param board une matrice d'entier
     */
    public static <Integer> void printBoard(Integer[][] board){
        for (int i = 0; i < board.length; i++) {
            if(board[i][0] != null){
                System.out.print(" 0" + board[i][0] + " ");
            }else {
                System.out.print(" __ ");
            }
            for (int j = 1; j < board[i].length; j++) {
                Integer chiffre = board[i][j];
                if(chiffre != null){
                    System.out.print(" " +board[i][j] + " ");
                }else{
                    System.out.print(" __ ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Remplie une planche avec des nombres aleatoires en fonction de la colonne et en respectant un schema aleatoire
     * 
     * @param schema un schema de planche
     * @return une matrice correspondant a une planche
     */
    public static Integer[][] boardInit(List<Pair<Integer,Integer>> schema){
        Integer[][] board = new Integer[3][9];
        for (int i = 0; i<board[0].length; i++){
            // selection des colonnes remplie a partire du schema
            Pair<Integer, Integer> cols = schema.get(i);
            Integer column1 = cols.getFirst();
            Integer column2 = cols.getSecond();

            // tirage du premier nombre
            int firstDrow = randomBoxDraw(i);
            board[column1][i] = firstDrow;

            //tirage d'un second nombre différant du premier si besoin
            if(column2 != null){
                board[column2][i] = randomBoxDraw(i,firstDrow);
            }
        }
        return board;
    }

    /**
     * @return un shema aleatoire pour une planche
     */
    public static List<Pair<Integer,Integer>> randomBoardSchema() {
        //tirage aleatoire des ligne dans le schema
        int row1 = randomDraw(2);
        Integer row2 = randomDraw(2,row1);
        Integer row3 = null;
        if(row1 != 0 && row2 != 0){
            row3 =0;
        }else if(row1 != 1 && row2 !=1){
            row3 =1;
        }else {
            row3 =2;
        }

        //initialisation des différante paire
        Pair<Integer,Integer> p1N = new Pair<>(row1,null);
        Pair<Integer,Integer> p2N = new Pair<>(row2,null);
        Pair<Integer,Integer> p3N = new Pair<>(row2,null);
        Pair<Integer,Integer> p12 = new Pair<>(row1,row2);
        Pair<Integer,Integer> p13 = new Pair<>(row1,row3);
        Pair<Integer,Integer> p23 = new Pair<>(row2,row3);

        //initialisation des différants schemas et melange aléatoire des schema
        List<Pair<Integer,Integer>> matrix1 = new ArrayList<>(List.of(p1N, p1N, p1N, p12, p13, p23, p23
                , p23,p23));
        Collections.shuffle(matrix1);
        List<Pair<Integer,Integer>> matrix2 = new ArrayList<>(List.of(p1N, p1N, p2N, p12, p12, p13, p23, p23
                , p23));
        Collections.shuffle(matrix2);
        List<Pair<Integer,Integer>> matrix3 = new ArrayList<>(List.of(p1N, p2N, p3N, p12, p12, p13, p13
                , p23,p23));
        Collections.shuffle(matrix3);

        //selection aleatoire du schema
        int randomNumberMatrix = randomDraw(2);
        return switch (randomNumberMatrix) {
            case 0 -> matrix1;
            case 1 -> matrix2;
            case 2 -> matrix3;
            default -> null;
        };
    }

    /**
     * @param column une colonne de la planche entre 0 et 8
     * @return un nombre aleatoire pour la colonne
     */
    public static int randomBoxDraw(int column) {
        if(column == 0){
            // entre 1 et 9 pour la première colonne
            return randomDraw(8) + 1;
        }else  if (column == 8){
            // entre 0 et 10 pour la huitième colonne
            return  randomDraw(10) + 80;
        }else {
            //entre 0 et 9 pour les autres colonnes
            return  randomDraw(9) + (10 * column);
        }
    }

    /**
     * @param column une colonne de la planche entre 0 et 8
     * @param unwantedNumber un nombre a ne pas tirer
     * @return un nombre aleatoire pour la colonne colonne
     */
    public static int randomBoxDraw(int column, int unwantedNumber) {
        if(column == 0){
            return randomDraw(8,unwantedNumber -1) + 1;
        }else  if (column == 8){
            // entre 0 et 9 si le nombre non voulue est 90 sinon entre 0 et 10
            if(unwantedNumber == 90){
                return  randomDraw(9) + 80;
            }
            return randomDraw(10,unwantedNumber % 10) + 80;
        }else {
            return  randomDraw(9,unwantedNumber % 10) + (10 * column);
        }
    }

    /**
     * @param max le nombres maximume a tiré
     * @return un nombre aleatoire entre 0 et le max
     */
    public static int randomDraw(int max){
        return (int)(Math.random() * max + 1);
    }

    /**
     * @param max le nombres maximume a tiré
     * @param unwantedNumber un nombre a ne pas tirer
     * @return un nombre aleatoire entre 0 et le max qui n'est pas le nombre qu'on ne veut pas tiré
     */
    public static int randomDraw(int max, int unwantedNumber){
        int result;
        do {result = (int) (Math.random() * max);
        }while (unwantedNumber==result);
        return result;
    }
}