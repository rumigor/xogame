package lesson4;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static final int SIZE = 5;
    static final int DOTS_TO_WIN = 4;
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';
    static char[][] map;
    static Scanner scanner = new Scanner(System.in);
    static Random random;

    public static void main(String[] args) {
	initMap();
	printMap();
	while (true) {
        humanTurn();
        printMap();
        if (checkWin(DOT_X)){
            System.out.println("Вы победили!");
            break;
        }
        if (isFull()) {
            System.out.println("Ничья");
            break;
        }
        aiTurn();
        printMap();
        if (checkWin(DOT_O)){
            System.out.println("Компьютер победили!");
            break;
        }
        if (isFull()) {
            System.out.println("Ничья");
            break;
        }
    }
    }

    public static void initMap(){
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap(){
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i+1 + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
            }
    }

    public static void humanTurn(){
        int x, y;
        do {
            System.out.println("Введите координаты Вашего хода X Y");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(y, x));
        map[y][x] = DOT_X;
    }

    public static boolean isCellValid(int y, int x){
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static void aiTurn(){
        int x, y;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i, j)) {
                    map[i][j] = DOT_O;
                    if (checkWin(DOT_O)) {
                        return;
                    }
                    else {map[i][j] = DOT_EMPTY;}
                }
            }
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i, j)) {
                    map[i][j] = DOT_X;
                    if (checkWin(DOT_X)) {
                        map[i][j] = DOT_O;
                        return;
                    } else {
                        map[i][j] = DOT_EMPTY;
                    }
                }
            }
        }
        random = new Random();
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(y, x));
        map[y][x] = DOT_O;
    }

    public static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkWin(char c) {
        for (int i = 0; i < SIZE; i++) {            //проверка по горизонтали
            int countLine = 1;
            for (int j = 0; j < SIZE - 1; j++) {
                if ((map[i][j] == c) && (map[i][j+1] == c)) {
                    countLine++;
                    if (countLine == DOTS_TO_WIN) {return true;}
                }
                else {countLine = 1;}
            }
        }
        for (int i = 0; i < SIZE; i++) { // проверка по вертикали
            int countLine = 1;
            for (int j = 0; j < SIZE-1; j++) {
                if ((map[j][i] == c) && (map[j+1][i] == c)) {
                    countLine++;
                    if (countLine == DOTS_TO_WIN) {return true;}
                }
                else {countLine = 1;}
            }
        }
        for (int i = 0; i <= SIZE - DOTS_TO_WIN; i++) { //проверка 1-ой диагонали
            int countLine = 1;
            for (int j = 0; j < SIZE - 1 - i; j++){
                if ((map[i+j][j] == c) && (map[i+j+1][j+1] == c)) {
                    countLine++;
                    if (countLine == DOTS_TO_WIN) {return true;}
                }
                else {countLine = 1;}
            }
        }

        for (int i = 0; i <= SIZE - DOTS_TO_WIN; i++) { //проверка 2-ой диагонали
            int countLine = 1;
            for (int j = SIZE - 1; j > 0 + i; j--){
                if ((map[i + SIZE - 1 - j][j] == c) && (map[i + SIZE - j][j-1] == c)) {
                    countLine++;
                    if (countLine == DOTS_TO_WIN) {return true;}
                }
                else {countLine = 1;}
            }
        }

        return false;
    }
}
