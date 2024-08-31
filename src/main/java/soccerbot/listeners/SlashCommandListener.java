package soccerbot.listeners;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import soccerbot.SlashCommandsEnum;
import soccerbot.commands.*;

@Component
@RequiredArgsConstructor
public class SlashCommandListener extends ListenerAdapter {

    private final BattleSlashCommand battleSlashCommand;
    private final PingSlashCommand pingSlashCommand;
    private final BonusDiceSlashCommand bonusDiceSlashCommand;
    private final DistanceSlashCommand distanceSlashCommand;
    private final GoalkeeperSlashCommand goalKeeperSlashCommand;
    private final CardSlashCommand cardSlashCommand;
    private final ContusionSlashCommand contusionSlashCommand;
    private final ShotSlashCommand shotSlashCommand;
    private final PlaySlashCommand playSlashCommand;

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        SlashCommandsEnum eventName = SlashCommandsEnum.fromStringName(event.getName());

        switch (eventName) {
            case PING:
                pingSlashCommand.handleEvent(event);
                break;
            case BATTLE:
                battleSlashCommand.handleEvent(event);
                break;
            case BOND6:
                bonusDiceSlashCommand.handleEvent(event);
                break;
            case DISTANCE:
                distanceSlashCommand.handleEvent(event);
                break;
            case GOALKEEPER:
                goalKeeperSlashCommand.handleEvent(event);
                break;
            case CARD:
                cardSlashCommand.handleEvent(event);
                break;
            case CONTUSION:
                contusionSlashCommand.handleEvent(event);
                break;
            case SHOT:
                shotSlashCommand.handleEvent(event);
                break;
            case PLAY:
                playSlashCommand.handleEvent(event);
                break;
            default:
                event.reply("Unknown command!").queue();
                break;
        }
    }
}
