package at.vibes.domischmutz.npcs.utility.itemstack;

import at.vibes.domischmutz.npcs.utility.Utils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ItemStackBuilder {
    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    protected ItemStackBuilder(ItemStack stack) {
        this.itemStack = stack;
        this.itemMeta = stack.getItemMeta();
    }

    public static ItemStackBuilder forMaterial(Material material) {
        if (material == null || material == Material.AIR) {
            throw new IllegalStateException("can't create builder for a NULL material.");
        }
        return new ItemStackBuilder(new ItemStack(material, 1));
    }

    public ItemStackBuilder setName(String name) {
        itemMeta.setDisplayName(Utils.toColor(name));
        return this;
    }

    public ItemStackBuilder setLore(Iterable<String> lore) {
        itemMeta.setLore(StreamSupport.stream(lore.spliterator(), false)
            .map(Utils::toColor)
            .collect(Collectors.toList()));
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemStackBuilder setLore(String... lore) {
        return setLore(Arrays.asList(lore));
    }

    public ItemStackBuilder setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
