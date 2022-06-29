package at.vibes.domischmutz.npcs.utility.inventory;

import org.bukkit.event.inventory.InventoryClickEvent;

/** Interface used when a player interacts with an item as described in {@link VInventoryItem}. */
public interface VInventoryCallback {
    /**
     * Called when a {@link VInventoryItem} is clicked.
     *
     * @param event The item click event.
     */
    void onClick(InventoryClickEvent event);
}
