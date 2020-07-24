package treeSet;

import java.util.Objects;

/**
 * Create by peng on 2020/7/24.
 */
public class Item implements Comparable<Item>{
    private Objects objects;

    private String description;
    private int partNumber;

    /**
     * Constructs an item.
     * @param aDescription the item's description
     * @param aPartNumber the item's part number
     */
    public Item(String aDescription, int aPartNumber)
    {
        description = aDescription;
        partNumber = aPartNumber;
    }

    /**
     * Gets the description of this item.
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    public String toString()
    {
        return "[description=" + description + ", partNumber=" + partNumber + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, partNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        Item other = (Item) obj;
        return Objects.equals(description, other.description) && partNumber == other.partNumber;
    }

    @Override
    public int compareTo(Item o)
    {
        int diff = Integer.compare(this.partNumber, o.partNumber);
        return diff != 0 ? diff : this.description.compareTo(o.description);
    }
}
