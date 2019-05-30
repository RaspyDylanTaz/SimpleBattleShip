import java.util.Scanner;


public class BattleShips{
    
    private int coord[][] = new int[2][2];
    private int guesses = 20;
    
    private int ships = 2;
    private boolean s1 = false, s2 = false;
    
    
    private Coord c1 = new Coord(), c2 = new Coord();;
    
    public BattleShips(){
            generateShips();
            playGame();
    }
    
    public void playGame(){
        Scanner sc = new Scanner(System.in);
        
        while (guesses > 0){
            System.out.println("Please enter an X coordinate between 0 and 7");
            int x = sc.nextInt();
            System.out.println("Please enter an Y coordinate between 0 and 7");
            int y = sc.nextInt();
            
            
            int dist1 = -1;
            int dist2 = -1;
            if(this.s1 == false){
                dist1 = calculateDist(x,y, "s1");
            }
            if(this.s2 == false){
                dist2 = calculateDist(x,y, "s2");
            }            
            checkHit(x,y, dist1, dist2);
            if(isWon()){
                System.out.println("The game has been won! You still has "+this.guesses+" guesses remaining");
                break;
            }

            
            this.guesses--;
            if(guesses == 0){
                System.out.println("Game over! Better luck next time!");
            }else{
                System.out.println("you have "+this.guesses+" guesses remaining");
            }
        }
        
    }
    
    /**
     * returns true if both ships have been destroyed
     * false if there is still atleast one remaining
     * */
    public boolean isWon(){
        if(s1 == true && s2 == true){//both ships have been destroyed, end game
            return true;
        }
        return false;
    }
    
    /**
     * firstly checks if the input is a valid coordinate, if so then check if either of the ships have been hit
     * if none of the ships have been hit, check the temprature of the closest, alive, ship.
     * */
    public void checkHit(int x, int y, int dist1, int dist2){
        if(x > 7 || x < 0 || y > 7 || y < 0 ){
            System.out.println("maybe try something on the board");
            return;
        }
        //if s1 has been hit notify user and return to main game loop
        if(this.s1 == false){
            if(this.coord[0][0] == x && this.coord[0][1] == y){
                System.out.println("Ship one has been hit");
                this.s1 = true;
                return;
            }
        }
        //if s2 has been hit notify user and return to main game loop
        if(this.s2 == false){
                if(this.coord[1][0] == x && this.coord[1][1] == y){
                System.out.println("Ship two has been hit");
                this.s2 = true;
                return;
            }
        }
        
        
        //if no ships have been hit give the user a measurement to let the know how close they were
        if(this.s1 == true || dist2<=dist1){ // if ship 2 is closer or if ship 1 has been destroyed 
            temp(dist2);
        }
        else if(this.s2 == true || dist1<dist2){ //if ship 1 is closer or if ship 2 has been destroyed
            temp(dist1);
        }

        
    }
    
    /**
     * given a distance to a ship this will output a tempture reading for the user to know how close they were to a ship
     * */
    public void temp(int dist){
        if(dist == 1 || dist == 2){
            System.out.println("Hot");
        }else if(dist == 3 || dist == 4){
            System.out.println("Warm");

        }else{
            System.out.println("Cold");
        }
    }
    
    
    /**
     * generate random x and y coord, check the coord isnt already being used then set as 1 to represent a ships location
     * */
    public void generateShips(){
        int min = 0, max = 8;
        for(int i = 0; i < 2; i++){
                    int x = (int) (Math.random() *((max - min) +1) + min);
                    int y = (int) (Math.random() *((max - min) +1) + min);
                    //if the second ship being created is the same as the first make another
                    if(this.coord[0][0] == x && this.coord[0][1] == y){
                        i--;
                    }else{
                        this.coord[i][0] = x;
                        this.coord[i][1] = y;
                    }
        }
    }
    
    /**
     * given two input integers returns the distance to the selected ship
     * */
    public int calculateDist(int x, int y, String s){
        int dist;
        
        if(s == "s1"){
            dist = Math.abs((x-this.coord[0][0]) + (y-this.coord[0][1]));
        }else{
            dist = Math.abs((x-this.coord[1][0]) + (y-this.coord[1][1]));
        }
        
        return dist;
        
    }
    

    
    public static void main(String[] args){
        new BattleShips();
    }
    
    class Coord{
        int x;
        int y;
    }

    
}