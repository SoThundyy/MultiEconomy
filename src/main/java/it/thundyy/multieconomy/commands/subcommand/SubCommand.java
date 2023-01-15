package it.thundyy.multieconomy.commands.subcommand;

import org.bukkit.entity.Player;

public interface SubCommand {
    String name();

    String permission();

    void execute(Player player, String[] args);
}
