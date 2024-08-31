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
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class BattleSlashCommand implements SlashCommand {

    private final SlashCommandsEnum commandName = SlashCommandsEnum.BATTLE;
    private final String commandDesc = "Wykonaj odpowiedni pojedynek";
    private final SlashCommandData command = Commands.slash(commandName.getName(), commandDesc)
            .addOption(OptionType.STRING, "player_with_the_ball", "Wybór jakie kości mają być rzucone przez bota dla zawodnika z piłką.", true)
            .addOption(OptionType.STRING, "player_without_the_ball", "Wybór jakie kości mają być rzucone przez bota dla zawodnika bez piłki.", true);

    public SlashCommandData getCommandData() {
        return command;
    }

    @Override
    public void handleEvent(@NotNull SlashCommandInteractionEvent event) {
        String playerWithoutBall = Objects.requireNonNull(event.getOption("player_without_the_ball")).getAsString();
        String playerWithBall = Objects.requireNonNull(event.getOption("player_with_the_ball")).getAsString();

        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Wyniki:");
        embed.setColor(new Color(0x563275));
        embed.setFooter("Wysłane przez " + event.getUser().getName(), event.getUser().getAvatarUrl());

        embed.addField("Gracz bez piłki", "Gwiazdki: **" + playerWithoutBall + "** | Wynik: " + throwDice(playerWithoutBall, false), false);
        embed.addField("Gracz z piłką", "Gwiazdki: **" + playerWithBall + "** | Wynik: " + throwDice(playerWithBall, true), false);

        event.replyEmbeds(embed.build()).queue();
    }

    private String throwDice(String value, boolean hasBall) {
        Random random = new Random();
        List<Integer> rolls;

        switch (value) {
            case "1":
                rolls = List.of(random.nextInt(8) + 1, random.nextInt(8) + 1);
                break;
            case "2":
                rolls = List.of(random.nextInt(4) + 1, random.nextInt(6) + 1, random.nextInt(8) + 1);
                break;
            case "3":
                rolls = List.of(random.nextInt(6) + 1, random.nextInt(6) + 1, random.nextInt(6) + 1, random.nextInt(3) + 1);
                break;
            case "4":
                rolls = List.of(random.nextInt(3) + 1, random.nextInt(3) + 1, random.nextInt(4) + 1, random.nextInt(6) + 1, random.nextInt(8) + 1);
                break;
            case "5":
                rolls = List.of(random.nextInt(3) + 1, random.nextInt(4) + 1, random.nextInt(6) + 1, random.nextInt(8) + 1, random.nextInt(8) + 1);
                break;
            default:
                return "Zła ilość waizdek/złe polecenie -> nie liczba";
        }

        int sum = rolls.stream().reduce(0, Integer::sum);
        String results = rolls.stream().map(Object::toString).collect(Collectors.joining(" + "));
        boolean trick = isTrick(rolls, hasBall);

        return results + " = **" + sum + "**" + (trick ? " | Trick!" : ".");
    }

    private boolean isTrick(List<Integer> rolls, boolean hasBall) {
        if (!hasBall) return false;
        int cnt = 0;
        for (Integer roll : rolls) if (roll.equals(1)) cnt++;
        return cnt == 2;
    }
}
