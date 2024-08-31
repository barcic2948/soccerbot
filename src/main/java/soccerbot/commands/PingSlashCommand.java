package soccerbot.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import soccerbot.SlashCommandsEnum;

@Component
public class PingSlashCommand implements SlashCommand {

    private final SlashCommandsEnum commandName = SlashCommandsEnum.PING;
    private final String commandDesc = "Replies with Pong!";
    private final SlashCommandData command = Commands.slash(commandName.getName(), commandDesc);

    @Override
    public void handleEvent(@NotNull SlashCommandInteractionEvent event) {
        event.reply("Pong!").queue();
    }

    @Override
    public SlashCommandData getCommandData() {
        return command;
    }
}
