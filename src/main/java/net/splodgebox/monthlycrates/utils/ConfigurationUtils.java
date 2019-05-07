package net.splodgebox.monthlycrates.utils;

import co.aikar.commands.annotation.Single;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.splodgebox.monthlycrates.MonthlyCrates;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@RequiredArgsConstructor
public class ConfigurationUtils {

    @Getter
    private final MonthlyCrates plugin;
    
    public void addReward(ItemStack itemStack, String crate, boolean giveItem, double chance, String command){
        String path = "Crates." + crate + ".rewards." + getLength(MonthlyCrates.getInstance().crates.getConfiguration().getConfigurationSection("Crates." + crate + ".rewards")) + ".";
        String name = itemStack.getItemMeta().getDisplayName().replace("§", "&");
        List<String> lore = itemStack.getItemMeta().getLore();
        if (lore == null) lore = Lists.newArrayList();
        String material = itemStack.getType().toString();
        getPlugin().crates.getConfiguration().set(path + "chance", chance);
        getPlugin().crates.getConfiguration().set(path + "material", material);
        getPlugin().crates.getConfiguration().set(path + "name", name);
        getPlugin().crates.getConfiguration().set(path + "lore", lore);
        getPlugin().crates.getConfiguration().set(path + "command", command);
        getPlugin().crates.getConfiguration().set(path + "give_item", giveItem);
        getPlugin().crates.save();
        getPlugin().crates.reload();
    }

    public int getLength(ConfigurationSection section) {
        int length = 0;
        for (String key : section.getKeys(false)){
            length++;
        }
        return length + 1;
    }
    
}