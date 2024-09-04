package com.example.fetch;

/**
 * Represents an item with an ID, list ID, and name.
 */
public class Item {
    // Private fields for item attributes
    private int id;
    private int listId;
    private String name;

    /**
     * Constructor to initialize an item with given id, listId, and name.
     *
     * @param id     The unique identifier for the item.
     * @param listId The identifier for the list the item belongs to.
     * @param name   The name of the item.
     */
    public Item(int id, int listId, String name) {
        this.id = id;
        this.listId = listId;
        this.name = name;
    }

    // Getter and setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter for listId
    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the item.
     *
     * @return A string describing the item.
     */
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", listId=" + listId +
                ", name='" + name + '\'' +
                '}';
    }
}
