package soccerbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import soccerbot.SlashCommandsEnum;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class GoalkeeperSlashCommand implements SlashCommand {

    private final SlashCommandsEnum commandName = SlashCommandsEnum.GOALKEEPER;
    private final String commandDesc = "Czy gracz otrzymuje kartkę";
    private final SlashCommandData command = Commands.slash(commandName.getName(), commandDesc)
            .addOption(OptionType.STRING, "goalkeeper", "Ilośc rzuconych kości w zależności od siły bramkarza.", true);

    @Override
    public SlashCommandData getCommandData() {
        return command;
    }

    @Override
    public void handleEvent(@NotNull SlashCommandInteractionEvent event) {
        String goalkeeper = Objects.requireNonNull(event.getOption("goalkeeper")).getAsString();

        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Wyniki:");
        embed.setColor(new Color(0x563275));
        embed.setFooter("Wysałne przez " + event.getUser().getName(), event.getUser().getAvatarUrl());

        embed.addField("Rzut na obronę bramkarza:", "Gwiazdki: **" + goalkeeper + "** | Wynik: " + getResult(goalkeeper), false);

        event.replyEmbeds(embed.build()).queue();
    }

    private String getResult(String goalkeeper) {
        Random random = new Random();
        Integer numberOfStars;
        try {
            numberOfStars = Integer.valueOf(goalkeeper);
        } catch (NumberFormatException e) {
            return "Nieprawidłowa wartość podana w funkcji: **" + goalkeeper + "**";
        }

        if (numberOfStars > 5 || numberOfStars < 1) {
            return "Nieprawidłowa wartość podana w funckji: **" + goalkeeper + "**";
        }

        List<Integer> rolls = new ArrayList<>();
        for (int i = 0; i < numberOfStars; i++) {
            rolls.add(random.nextInt(6) + 1);
        }

        return "**" + rolls.stream().map(Objects::toString).collect(Collectors.joining(" + ")) + "**";
    }
}
