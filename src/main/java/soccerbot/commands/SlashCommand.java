package soccerbot.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;

public interface SlashCommand {
    SlashCommandData getCommandData();
    void handleEvent(@NotNull SlashCommandInteractionEvent event);
}

