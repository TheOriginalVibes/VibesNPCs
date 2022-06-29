package at.vibes.domischmutz.npcs.utility.inventory;

import at.vibes.domischmutz.npcs.utility.itemstack.ItemStackBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Viiiiibes
 */
public abstract class VInventoryPage {
    private final VInventory vInventory;
    private final String pageName;
    private final int rows;
    private final List<VInventoryItem> inventoryItems;

    public VInventoryPage(VInventory vInventory, String inventoryName, int rows) {
        this.vInventory = vInventory;
        this.pageName = inventoryName;
        this.rows = rows * 9;
        this.inventoryItems = new ArrayList<>();
        if (vInventory.getInventory() != null) {
            final VInventoryPage vInventoryPage = vInventory.getPage();

            addItem(ItemStackBuilder.forMaterial(Material.ARROW)
                    .setName(ChatColor.GREEN + "Go back")
                    .setLore(ChatColor.GRAY + "click here...")
                    .build(),
                this.rows - 9,
                true,
                event -> {
                    vInventory.setCurrentPage(vInventoryPage);
                    openInventory();
            });
        }
        vInventory.setCurrentPage(this);
    }

    public VInventory getInventory() {
        return vInventory;
    }

    public String getPageName() {
        return pageName;
    }

    public int getRows() {
        return rows;
    }

    public List<VInventoryItem> getInventoryItems() {
        return inventoryItems;
    }

    public boolean containsItem(int slot) {
        return inventoryItems.stream().anyMatch(vInventoryItem -> vInventoryItem.getSlot() == slot);
    }

    public VInventoryItem findItem(int slot) {
        return inventoryItems.stream()
            .filter(vInventoryItem -> vInventoryItem.getSlot() == slot)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("can't find item for slot " + slot));
    }

    public void addItem(ItemStack itemStack, int slot, boolean isDefault, VInventoryCallback callback) {
        inventoryItems.add(new VInventoryItem(itemStack, slot, isDefault, callback));
    }

    public void addItem(ItemStack itemStack, int slot, VInventoryCallback callback) {
        addItem(itemStack, slot, false, callback);
    }

    public Player getPlayer() {
        return vInventory.getPlayer();
    }

    public void openInventory() {
        vInventory.getPlayer().openInventory(vInventory.build());
    }

    public abstract void update();
}
