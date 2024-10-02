//This is an Uno game simulation.
//Created by James Vanderhyde, 30 September 2024
//Modified by Jackson McFarland 10/1/24
package dumbuno;

import java.util.Random;

public class DumbUno
{
     private static final Random rng = new Random();
    
    public static void main(String[] args) 
    {
        final int maxHandSize = 12;
        final int minHandSize = 3;
        
        //Put the players into a circular linked list
        IntNode startPlayer = new IntNode(0,null);
        IntNode current = startPlayer;
        current.setNext(new IntNode(0,null));
        current = current.getNext();
        current.setNext(new IntNode(0,null));
        current = current.getNext();
        current.setNext(new IntNode(0,null));
        current = current.getNext();
        current.setNext(new IntNode(0,null));
        current = current.getNext();
        current.setNext(startPlayer);
        
        //Deal every player a hand
        current = startPlayer;
        while (current.getNext() != startPlayer)
        {
            current.setData(rng.nextInt(minHandSize, maxHandSize+1));
            current = current.getNext();
        }
        current.setData(rng.nextInt(minHandSize, maxHandSize+1)); // Set for last player
        
        printGame(startPlayer);
        
        // Play the game
        current = startPlayer;
        while (true) 
        {
            int cards = current.getData();
            if (cards > 1) {
                current.setData(cards - 1);
                if (current.getData() == 1) {
                    System.out.println("Player with 1 card: Uno!");
                }
            } 
            else if (cards == 1) {
                System.out.println("I win!");
                break;
            }
            current = current.getNext();
        }
    }
    
    private static void printGame(IntNode startPlayer)
    {
        IntNode current = startPlayer;
        do {
            System.out.println("Player has " + current.getData() + " cards.");
            current = current.getNext();
        } while (current != startPlayer);
    }
}