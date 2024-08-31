package soccerbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import soccerbot.SlashCommandsEnum;

import java.awt.Color;
import java.util.Random;

@Component
public class PlaySlashCommand implements SlashCommand {

    private final SlashCommandsEnum commandName = SlashCommandsEnum.PLAY;
    private final String commandDesc = "Rzut na zagranie";
    private final SlashCommandData command = Commands.slash(commandName.getName(), commandDesc);

    @Override
    public SlashCommandData getCommandData() {
        return command;
    }

    @Override
    public void handleEvent(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Rzut na zagranie:");
        embed.setColor(new Color(0x563275));
        embed.setFooter("Wysłane przez " + event.getUser().getName(), event.getUser().getAvatarUrl());

        embed.addField("Wynik", getResult(), false);

        event.replyEmbeds(embed.build()).queue();
    }

    private String getResult() {
        Random random = new Random();
        return "**" + (random.nextInt(4) + 1) + "**";
    }

}
