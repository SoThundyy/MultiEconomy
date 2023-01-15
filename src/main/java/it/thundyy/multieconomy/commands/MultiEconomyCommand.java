package it.thundyy.multieconomy.commands;

import com.google.common.collect.Maps;
import it.thundyy.multieconomy.commands.subcommand.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class MultiEconomyCommand implements CommandExecutor {
    private final Map<String, SubCommand> subCommands = Maps.newHashMap();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return true;


        return true;
    }

}
