package com.tommyhasselman.termsandconditions.model;

import com.tommyhasselman.termsandconditions.Controller;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;

/**
 *  The Order class describes a order and a package of items to be displayed to the player.
 */
@SuppressWarnings("unused")
public class Order {

    private ArrayList<OrderItem> ordered = new ArrayList<>(); // Array of items on the order.
    private ArrayList<OrderItem> packed = new ArrayList<>(); // Array of items that have been packed.
    private ArrayList<OrderItem> packedShuff = new ArrayList<>();
    private boolean correctlyPacked;
    private boolean validated;

    /**
     * Order constructor populates the ordered and packed arrays. As well as a shuffled packed array
     * used by the BoxDialog class. An instance of Random is used against incorrect and missing
     * chances, defined in the controller object, to determine the makeup of the packed box; against
     * the original order array.
     * @param c The Instance of Controller used to retrieve the chance values.
     */
    public Order(Controller c) {

        int comp = c.getOrderItemComplexity();

        // Fills the ordered array with random items generated by the BasicItem class
        for (int i = 0; i < c.orderSize; i++){
            ordered.add(new Item(comp));
        }

        // Fills the packed array with matching or mismatching items.
        Random r = new Random();
        double chance = r.nextDouble();
        if (chance <= c.incorrectItemChance) {
            int amountWrong = r.nextInt(c.orderSize)+1;
            int i = 1;
            for (OrderItem oi : ordered) {
                if (i <= amountWrong) {
                    // If the chance is less than missingItemChance the item will be skipped.
                    if (chance > c.missingItemChance) {
                        Item item = new Item(comp);
                        packed.add(item); // There is a small chance that this item will happen to be the same at the moment.
                        packedShuff.add(item);
                    }
                } else {
                    packed.add(oi);
                    packedShuff.add(oi);
                }
                i++;
            }
        } else {
            for (OrderItem oi : ordered) {
                packed.add(oi);
                packedShuff.add(oi);
            }
        }

        correctlyPacked = (getOrderedCodes().equals(getPackedCodes()));
        Collections.shuffle(packedShuff);

    }

    /**
     * Joins Item codes of the ordered array for comparison.
     * @return Returns a String of OrderItem codes.
     */
    public String getOrderedCodes() {
        StringBuilder s = new StringBuilder();
        for (OrderItem i : ordered) {
            s.append(i.getCode());
        }
        return s.toString();
    }

    /**
     * Joins Item codes of the packed array for comparison.
     * @return Returns a String of OrderItem codes.
     */
    public String getPackedCodes() {
        StringBuilder s = new StringBuilder();
        for (OrderItem i : packed) {
            s.append(i.getCode());
        }
        return s.toString();
    }

    /**
     * @return Returns the ArrayList of packed items.
     */
    public ArrayList<OrderItem> getPacked() {
        return packed;
    }

    /**
     * @return Returns the ArrayList of shuffled packed items.
     */
    public ArrayList<OrderItem> getPackedShuff() {
        return packedShuff;
    }

    /**
     * @return Returns the ArrayList of ordered items.
     */
    public ArrayList<OrderItem> getOrdered() {
        return ordered;
    }

    /**
     * @param ordered Sets the ordered array to given ArrayList.
     */
    public void setOrdered(ArrayList<OrderItem> ordered) {
        this.ordered = ordered;
    }
    /**
     * @param packed Sets the packed array to given ArrayList.
     */
    public void setPacked(ArrayList<OrderItem> packed) {
        this.packed = packed;
    }

    /**
     * @return Returns the correctlyPacked boolean value.
     */
    public boolean isCorrectlyPacked() {
        return correctlyPacked;
    }
}
