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
public class BonusDiceSlashCommand implements SlashCommand {

    private final SlashCommandsEnum commandName = SlashCommandsEnum.BOND6;
    private final String commandDesc = "Bonusowa kość d6";
    private final SlashCommandData command = Commands.slash(commandName.getName(), commandDesc);

    @Override
    public SlashCommandData getCommandData() {
        return command;
    }

    @Override
    public void handleEvent(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Bonusowa kośc d6:");
        embed.setColor(new Color(0x563275));
        embed.setFooter("Wysłane przez " + event.getUser().getName(), event.getUser().getAvatarUrl());

        embed.addField("Wynik", getResult(), false);

        event.replyEmbeds(embed.build()).queue();
    }

    private String getResult() {
        Random random = new Random();
        return "**" + (random.nextInt(6) + 1) +"**";
    }

}
