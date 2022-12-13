//Fernanda Malheiros Rodrigues Alves
/*STAR WARS
 * Your goal is to defeat the Empire.
 * To do so, you must destroy 3 Death Stars. For you to reach a Death Star, you must first destroy 20 members of the Empire
 * 
 * Save the droids by getting them inside Millennium Falcon and gain lifes. 
 * Save Yoda from space to gain lifes.
 * 
 * up arrow: moves Millennium Falcon up
 * down arrow: moves Millennium Falcon down
 * right arrow: shoots lasers
 */

package GameLab8;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import javax.imageio.*;
import javax.swing.*;

public class Game {
    private Grid grid;
    private int userRow;
    private int userCol;
    private int msElapsed;
    private int timesGet;
    private int timesGetYoda;
    private int timesAvoidDStar;
    private int timesAvoid;
    private int gainPoints;
    private int losePoints;
    private String laser;

    public Game() {
        grid = new Grid(6, 11);
        userRow = 1;
        userCol = 0;
        msElapsed = 0;
        timesGet = 0;
        timesGetYoda = 3;
        timesAvoid = 0;
        timesAvoidDStar = 0;
        updateTitle();
        grid.setImage(new Location(userRow, 0), "user.gif");

        for (int i = 1; i < grid.getNumRows(); i++) {
            for (int k = 0; k < grid.getNumCols(); k++) {
                grid.setImage(new Location(i, k), "stars.gif");
            }
        }
    }

    public void play() {
        while (!isGameOver()) {
            grid.pause(100);
            handleKeyPress();
            getScore();
            if (msElapsed % 300 == 0) {
                scrollLeft();
                populateRightEdge();
            }
            if (msElapsed % 100 == 0) {
                moveLaser();
            }
            updateTitle();
            msElapsed += 100;
        }
        for (int i = 0; i < grid.getNumRows(); i++) {
            for (int k = 0; k < grid.getNumCols(); k++) {
                grid.setImage(new Location(i, k), "stars.gif");
                grid.setImage(new Location(1, 0), "theEnd.gif");
                grid.setImage(new Location(2, 0), "theEnd.gif");
                grid.setImage(new Location(3, 0), "theEnd.gif");
                grid.setImage(new Location(4, 0), "theEnd.gif");
                grid.setImage(new Location(5, 0), "theEnd.gif");
                
                grid.setImage(new Location(1, 1), "theEnd.gif");
                grid.setImage(new Location(3, 1), "theEnd.gif");
                grid.setImage(new Location(5, 1), "theEnd.gif");
                
                grid.setImage(new Location(1, 3), "theEnd.gif");
                grid.setImage(new Location(2, 3), "theEnd.gif");
                grid.setImage(new Location(3, 3), "theEnd.gif");
                grid.setImage(new Location(4, 3), "theEnd.gif");
                grid.setImage(new Location(5, 3), "theEnd.gif");
                
                grid.setImage(new Location(2, 4), "theEnd.gif");
                grid.setImage(new Location(3, 4), "theEnd.gif");
                
                grid.setImage(new Location(3, 5), "theEnd.gif");
                grid.setImage(new Location(4, 5), "theEnd.gif");
                
                grid.setImage(new Location(1, 6), "theEnd.gif");
                grid.setImage(new Location(2, 6), "theEnd.gif");
                grid.setImage(new Location(3, 6), "theEnd.gif");
                grid.setImage(new Location(4, 6), "theEnd.gif");
                grid.setImage(new Location(5, 6), "theEnd.gif");
                
                grid.setImage(new Location(1, 8), "theEnd.gif");
                grid.setImage(new Location(2, 8), "theEnd.gif");
                grid.setImage(new Location(3, 8), "theEnd.gif");
                grid.setImage(new Location(4, 8), "theEnd.gif");
                grid.setImage(new Location(5, 8), "theEnd.gif");
                
                grid.setImage(new Location(1, 9), "theEnd.gif");
                grid.setImage(new Location(5, 9), "theEnd.gif");
                
                grid.setImage(new Location(2, 10), "theEnd.gif");
                grid.setImage(new Location(3, 10), "theEnd.gif");
                grid.setImage(new Location(4, 10), "theEnd.gif");
            }
        }
    }

    public void handleKeyPress(){
        int key = grid.checkLastKeyPressed();
        if (key == 38 && userRow > 1) { //UP ARROW
            handleShipCollision(new Location(userRow-1, userCol));
            grid.setImage(new Location(userRow,0), "stars.gif"); //null
            userRow--;      
            grid.setImage(new Location(userRow,0), "user.gif");
        }
        if (key == 40 && userRow < grid.getNumRows() - 1) { //DOWN ARROW
            handleShipCollision(new Location(userRow+1, userCol));
            grid.setImage(new Location(userRow,0), "stars.gif"); //null
            userRow++;
            grid.setImage(new Location(userRow,0), "user.gif");
        }
        if (key == 39) { //RIGHT ARROW
            laser = "laser.gif";
            grid.setImage(new Location(userRow,1), laser);
        }
    }

    public int randomizer(int min, int max){
        int diceRoll = (int)(min + Math.random() * (max - min) + 1);
        return diceRoll;
    }

    public void populateRightEdge() {

        int randomYoda = (randomizer(0, 200)); //creating random position with delay //LIFE
        if (randomYoda == 5) {
            int random = (int)(Math.random() * 4);
            if (random > 0) {
                grid.setImage(new Location(random, grid.getNumCols() -1), "getYoda.gif"); //Yoda
            }
        }
        int randomR2D2 = (randomizer(0, 20)); //creating random position with delay
        if (randomR2D2 == 5) {
            int random = (int)(Math.random() * 4);
            if (random > 0) {
                grid.setImage(new Location(random, grid.getNumCols() -1), "getR2D2.gif"); //R2D2
            }
        }
        int randomBB8 = (randomizer(0, 20)); //creating random position with delay
        if (randomBB8 == 5) {
            int random = (int)(Math.random() * 4);
            if (random > 0) {
                grid.setImage(new Location(random, grid.getNumCols() -1), "getBB8.gif"); //BB8
            }
        }
        int randomTie = (randomizer(0, 5)); //creating random position with delay
        if (randomTie == 5) {
            int random = (int)(Math.random() * 4);
            if (random > 0) {
                grid.setImage(new Location(random, grid.getNumCols() -1), "avoidTie.gif"); //Tie Fighter
            }
        }
        int randomStorm = (randomizer(0, 5)); //creating random position with delay
        if (randomStorm == 5) {
            int random = (int)(Math.random() * 4);
            if (random > 0) {
                grid.setImage(new Location(random, grid.getNumCols() -1), "avoidStorm.gif"); //Storm
            }
        }
    }

    public void scrollLeft() { //bringing the characters
        Location collision = new Location(userRow, 1);
        handleShipCollision(collision);
        for (int row = 1; row < grid.getNumRows(); row++) {
            for (int col = 1; col < grid.getNumCols(); col++){
                String image1 = grid.getImage(new Location(row, col)); 
                String image2 = grid.getImage(new Location(row, col-1)); 

                if (image1 != null && !image1.equals(laser)) { //not null  
                    if (image1.equals("stars.gif") && image2 != null && !image2.equals(laser)){
                        grid.setImage(new Location(row, col-1), image1);
                    }
                    if (!image1.equals("stars.gif") && image2 != null && !image2.equals(laser)){
                        grid.setImage(new Location(row, col-1), image1);
                    }
                    if (!image1.equals("stars.gif") && image2 != null && image2.equals(laser)){ // NOT STARS + CHARACTERS
                        grid.setImage(new Location(row, col+1), "stars.gif");
                    }
                    grid.setImage(new Location(row, col), "stars.gif"); //
                }
            }
        }
        grid.setImage(new Location(userRow,0), "user.gif"); //position of Falcon
    }

    public void moveLaser() { //make a for looking for bullets and if find move right
        for (int row = 1; row < grid.getNumRows(); row++) {
            for (int col = 1; col < grid.getNumCols() -1; col++){
                String image1 = grid.getImage(new Location(row, col)); 
                if (image1 != null && image1.equals(laser)){
                    col++;
                    boolean Hit = handleLaserCollision(row, col);
                    grid.setImage(new Location(row, col), "stars.gif");
                    if (!Hit) {
                        grid.setImage(new Location(row, col), image1); 
                        grid.setImage(new Location(row, col-1), "stars.gif");
                    } 
                    grid.setImage(new Location(row, col-1), "stars.gif");

                } 
                if (col == grid.getNumCols()-1 && image1.equals(laser)){
                    grid.setImage(new Location(row, col), "stars.gif");
                }
            }
        }
        grid.setImage(new Location(userRow,0), "user.gif");
    }

    public boolean handleLaserCollision(int row, int col) { // WHERE LASER IS HITTING
        String image = grid.getImage(new Location(row, col));
        String laserImage = grid.getImage(new Location(row, col-1));

        if (image != null ){
            if (image.equals("avoidTie.gif")){ //LASER HIT TIE FIGHTER
                timesAvoid++;
                grid.setImage(new Location(row, col), "stars.gif");
                grid.setImage(new Location(row, col-1), "stars.gif");
            } 
            if (image.equals("avoidStorm.gif")){ //LASER HIT STORMTROOPER
                timesAvoid++;
                grid.setImage(new Location(row, col), "stars.gif");
                grid.setImage(new Location(row, col-1), "stars.gif");
            } 
            if (image.equals("deathStar.gif")) { //LASER HIT DEATH STAR
                timesAvoidDStar++;
                grid.setImage(new Location(row, col), "stars.gif");
                grid.setImage(new Location(row, col-1), "stars.gif");
            } 
            if (image.equals("getYoda.gif")) { //LASER HIT YODA
                timesGetYoda--;
                grid.setImage(new Location(row, col), "stars.gif");
                grid.setImage(new Location(row, col-1), "stars.gif");
            } 
            if (image.equals("getBB8.gif")) { //LASER HIT BB8
                timesGetYoda--;
                grid.setImage(new Location(row, col), "stars.gif");
                grid.setImage(new Location(row, col-1), "stars.gif");
            } 
            if (image.equals("getR2D2.gif")) { //LASER HIT R2D2
                timesGetYoda--;
                grid.setImage(new Location(row, col), "stars.gif");
                grid.setImage(new Location(row, col-1), "stars.gif");
            }
            if (timesAvoid == 20 || timesAvoid == 40 || timesAvoid == 60 ) {
                int random = (int)(Math.random() * 4);
                grid.setImage(new Location(random, grid.getNumCols() -1), "deathStar.gif"); //Send Death Star
            }
            if (!image.equals("stars.gif")){
                return true;
            }
        }

        return false;
    }

    public void handleShipCollision(Location loc) { //WHAT HITS THE SHIP
        //get the image at the location / check to see if not null / if good  char Score ++ / remove the image from location
        String image = grid.getImage(loc);

        if (image != null){
            if (image.equals("getR2D2.gif")){ //Points for getting R2D2 and BB8
                timesGet++;
                image.replace("getR2D2.gif", "stars.gif");
                image.replace("laser.gif", "stars.gif");
            } 
            if (image.equals("getBB8.gif")){ //Points for getting R2D2 and BB8
                timesGet++;
                image.replace("getBB8.gif", "stars.gif");
                image.replace("laser.gif", "stars.gif");
            } 
            if (image.equals("getYoda.gif")) { //Life for getting Yoda
                timesGetYoda++;
                image.replace("getYoda.gif", "stars.gif");
                image.replace("laser.gif", "stars.gif");
            }
            if (image.equals("avoidStorm.gif")) {
                timesGetYoda--;
            }
            if (image.equals("avoidTie.gif")) {
                timesGetYoda--;
            }
        }

        //this needs to be called at the top of the scrollLeft method / needs to be called when the ship goes up and down
        //colliding col +

    }

    public int getScore() {
        grid.setImage(new Location(0, 6), "stars.gif");
        grid.setImage(new Location(0, 7), "stars.gif");
        grid.setImage(new Location(0, 8), "stars.gif");
        grid.setImage(new Location(0, 9), "stars.gif");
        grid.setImage(new Location(0, 10), "stars.gif");
        if (timesGetYoda == 1 ) {
            grid.setImage(new Location(0, 0), "Yoda.gif"); 
            grid.setImage(new Location(0, 1), "stars.gif");
            grid.setImage(new Location(0, 2), "stars.gif");
            grid.setImage(new Location(0, 3), "stars.gif");
            grid.setImage(new Location(0, 4), "stars.gif");
            grid.setImage(new Location(0, 5), "stars.gif");
        } 
        if (timesGetYoda == 2) {
            grid.setImage(new Location(0, 0), "Yoda.gif");
            grid.setImage(new Location(0, 1), "Yoda.gif");
            grid.setImage(new Location(0, 2), "stars.gif");
            grid.setImage(new Location(0, 3), "stars.gif");
            grid.setImage(new Location(0, 4), "stars.gif");
            grid.setImage(new Location(0, 5), "stars.gif");
        } 
        if (timesGetYoda == 3) {
            grid.setImage(new Location(0, 0), "Yoda.gif");
            grid.setImage(new Location(0, 1), "Yoda.gif");
            grid.setImage(new Location(0, 2), "Yoda.gif");
            grid.setImage(new Location(0, 3), "stars.gif");
            grid.setImage(new Location(0, 4), "stars.gif");
            grid.setImage(new Location(0, 5), "stars.gif");
        } 
        if (timesGetYoda == 4) {
            grid.setImage(new Location(0, 0), "Yoda.gif");
            grid.setImage(new Location(0, 1), "Yoda.gif");
            grid.setImage(new Location(0, 2), "Yoda.gif");
            grid.setImage(new Location(0, 3), "Yoda.gif");
            grid.setImage(new Location(0, 4), "stars.gif");
            grid.setImage(new Location(0, 5), "stars.gif");
        } 
        if (timesGetYoda == 5) {
            grid.setImage(new Location(0, 0), "Yoda.gif");
            grid.setImage(new Location(0, 1), "Yoda.gif");
            grid.setImage(new Location(0, 2), "Yoda.gif");
            grid.setImage(new Location(0, 3), "Yoda.gif");
            grid.setImage(new Location(0, 4), "Yoda.gif");
            grid.setImage(new Location(0, 5), "stars.gif");
        } 
        if (timesGetYoda == 6) {
            grid.setImage(new Location(0, 0), "Yoda.gif");
            grid.setImage(new Location(0, 1), "Yoda.gif");
            grid.setImage(new Location(0, 2), "Yoda.gif");
            grid.setImage(new Location(0, 3), "Yoda.gif");
            grid.setImage(new Location(0, 4), "Yoda.gif");
            grid.setImage(new Location(0, 5), "Yoda.gif");
        }
        if (timesAvoidDStar == 1) {
            grid.setImage(new Location(0, 10), "deathStarBoard.gif");
            grid.setImage(new Location(0, 9), "stars.gif");
            grid.setImage(new Location(0, 8), "stars.gif");
        } 
        if (timesAvoidDStar == 2) {
            grid.setImage(new Location(0, 10), "deathStarBoard.gif");
            grid.setImage(new Location(0, 9), "deathStarBoard.gif");
            grid.setImage(new Location(0, 8), "stars.gif");
        } 
        if (timesAvoidDStar == 3) {
            grid.setImage(new Location(0, 10), "deathStarBoard.gif");
            grid.setImage(new Location(0, 9), "deathStarBoard.gif");
            grid.setImage(new Location(0, 8), "deathStarBoard.gif");
        }
        return timesAvoid;
    }

    public void updateTitle() {
        grid.setTitle("Alliance:  " + timesGet + "     |     " + "Empire: " + timesAvoid);
    }

    public boolean isGameOver() {
        if (timesAvoidDStar == 3) {
            return true;
        } else if (timesGetYoda == 0){
            return true;
        } else {
            return false;
        }
    }

    public static void test() {
        Game game = new Game();
        game.play();
    }

    public static void main(String[] args) {
        test();
    }

    public static void music() {

    }
}
