import java.util.*;

class Codechef {
    public static void main(String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("******************************");
        System.out.println("Welcome to the Base, Commander");
        System.out.println("******************************");
        System.out.println("What level would you like to play as?");
        System.out.println("1. Recruit (Easy)");
        System.out.println("2. Captain (Medium)");
        System.out.println("3. Commander (Hard)");

        System.out.println("Enter your name: ");
        String name = sc.next();
        int gridSize = 0 ;
        Player player1 = new Player(name);
        System.out.println("Enter the level you want to play");
        int level = sc.nextInt();

        if(level == 1){
            gridSize = 3;
        } else if (level == 2) {
            gridSize = 9;
        } else if (level == 3) {
            gridSize = 12;
        } else {
            System.out.println("Please enter a valid level");
        }

        Ship ship = new Ship(gridSize);
        int numShips = 3; // Assuming 3 ships
        genShip.generateships(ship, numShips);

        Targeting targeting = new Targeting(ship);
        while (!targeting.allShipsHit()) {
            targeting.play();
            targeting.printGrid();
        }
    }
}

class Player {
    String name;

    Player(String n ) {
        name = n;
    }
}

class Ship{
    int[][] arr;
    Ship(int size){
        arr = new int[size][size];
    }
}

class genShip{
    public static void generateships(Ship ship ,int numShips) {
        Random random = new Random();
        int size = ship.arr.length;
        for (int s = 0; s < numShips; s++) {
            boolean bool;
            bool = random.nextBoolean();
            int row , col ;
            if (bool) {
                row = random.nextInt(size);
                col = random.nextInt(size - 2);

            } else {
                row = random.nextInt(size);
                col = random.nextInt(size);

            }
            for(int i = 0 ;i<3;i++){
                if(bool){
                    ship.arr[row][col+i] = 1 ;
                }else{
                    ship.arr[row + i][col] = 1 ;
                }
            }
        }
    }
}

class Targeting {
    private Ship ship ;
    private boolean[][] hits ;
    private  int numships;

    Targeting(Ship ship){
        this.ship = ship ;
        hits = new  boolean[ship.arr.length][ship.arr.length];
        numships = 3 ;
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        int gridSize = ship.arr.length;

        System.out.println("Welcome to Control Room , Commander ! Please enter the coordinates to hit");
        System.out.println("Enter the row number (1-" + gridSize + "): ");
        int row = sc.nextInt() - 1;
        System.out.println("Enter the column number (1-" + gridSize + "): ");
        int col = sc.nextInt() - 1;
        if (row < 0 || row >= gridSize || col < 0 || col >= gridSize) {
            System.out.println("Invalid coordinates. Try again!");
            return;
        }

        if (!hits[row][col]) {
            hits[row][col] = true;
            if (ship.arr[row][col] == 1) {
                System.out.println("Voila Commander! Ship is hit!!");
                numships--;
            } else {
                System.out.println(" You missed the ship!");
            }
        } else {
            System.out.println("No hostiles present there , Commander !");
        }
    }

    public void printGrid() {
        System.out.println("Grid:");
        for (int i = 0; i < ship.arr.length; i++) {
            for (int j = 0; j < ship.arr[i].length; j++) {
                if (hits[i][j]) {
                    System.out.print(ship.arr[i][j] == 1 ? "X " : "* ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean allShipsHit() {
        return numships == 0;
    }
}