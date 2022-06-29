package at.vibes.domischmutz.npcs.utility.inventory;

import org.bukkit.inventory.ItemStack;

/**
 *  Used to create a new item for a {@link VInventoryPage}.
 *
 * @author Gaston Gonzalez {@literal <znetworkw.dev@gmail.com>}
 */
public class VInventoryItem {
    /** The item stack. */
    private final ItemStack itemStack;

    /** The position in which the item will be placed in inventory. */
    private final int slot;

    /** {@code true} if the item should appear on all pages for the item page inventory. */
    private final boolean isDefault;

    /** The item callback. */
    private final VInventoryCallback clickCallback;

    /**
     * Creates a new {@link VInventoryItem}.
     *
     * @param itemStack The item stack.
     * @param slot The slot in which the item will be positioned.
     * @param isDefault If the item was added internally.
     * @param vInventoryCallback The runnable that will be executed when the item is clicked.
     */
    public VInventoryItem(
            ItemStack itemStack,
            int slot,
            boolean isDefault,
            VInventoryCallback vInventoryCallback) {
        this.itemStack = itemStack;
        this.slot = slot;
        this.isDefault = isDefault;
        this.clickCallback = vInventoryCallback;
    }

    /** Returns the item stack. */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /** Returns the slot position for the item. */
    public int getSlot() {
        return slot;
    }

    /** Returns {@code true} if the item should appear on all pages. */
    public boolean isDefault() {
        return isDefault;
    }

    /** Returns the runnable for when the item is clicked. */
    public VInventoryCallback getInventoryCallback() {
        return clickCallback;
    }
}
