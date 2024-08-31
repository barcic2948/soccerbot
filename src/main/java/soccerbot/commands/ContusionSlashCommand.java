package soccerbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import soccerbot.SlashCommandsEnum;

import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class ContusionSlashCommand implements SlashCommand {

    private final SlashCommandsEnum commandName = SlashCommandsEnum.CONTUSION;
    private final String commandDesc = "Rzut na kontuzje";
    private final SlashCommandData command = Commands.slash(commandName.getName(), commandDesc);

    @Override
    public SlashCommandData getCommandData() {
        return command;
    }

    @Override
    public void handleEvent(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Czy gracz jest kontuzjowany:");
        embed.setColor(new Color(0x563275));
        embed.setFooter("Wysłane przez " + event.getUser().getName(), event.getUser().getAvatarUrl());
        embed.addField("Wynik:", getResult(), false);
        event.replyEmbeds(embed.build()).queue();
    }

    private String getResult() {
        Random random = new Random();
        java.util.List<Integer> rolls = List.of(random.nextInt(3) + 1, random.nextInt(3) + 1);
        return "**" + rolls.stream().map(Object::toString).collect(Collectors.joining(" + ")) + "**";
    }
}
