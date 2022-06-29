package at.vibes.domischmutz.npcs.utility.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * @author Gaston Gonzalez {@literal <znetworkw.dev@gmail.com>}
 */
public class VInventoryHolder implements InventoryHolder {
    /** The inventory in which the holder will be created for. */
    private final VInventory vInventory;

    /**
     * Creates a new {@link InventoryHolder} for a {@link VInventory}.
     *
     * @param vInventory The inventory.
     */
    public VInventoryHolder(VInventory vInventory) {
        this.vInventory = vInventory;
    }

    /** Returns the inventory. */
    public VInventory getzInventory() {
        return vInventory;
    }

    @Override
    public Inventory getInventory() {
        return vInventory.getInventory();
    }
}
