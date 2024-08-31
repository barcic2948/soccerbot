package soccerbot.config;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import soccerbot.commands.*;
import soccerbot.listeners.SlashCommandListener;

@Configuration
@RequiredArgsConstructor
public class BotConfig {

    @Value("${bot.token}")
    private String token;

    private final BattleSlashCommand battleSlashCommand;
    private final PingSlashCommand pingSlashCommand;
    private final BonusDiceSlashCommand bonusDiceSlashCommand;
    private final DistanceSlashCommand distanceSlashCommand;
    private final GoalkeeperSlashCommand goalKeeperSlashCommand;
    private final CardSlashCommand cardSlashCommand;
    private final ContusionSlashCommand contusionSlashCommand;
    private final ShotSlashCommand shotSlashCommand;
    private final PlaySlashCommand playSlashCommand;

    @Bean
    public JDA jda() throws Exception {

        JDA jda = JDABuilder.createDefault(token)
                .addEventListeners(new SlashCommandListener(
                        battleSlashCommand,
                        pingSlashCommand,
                        bonusDiceSlashCommand,
                        distanceSlashCommand,
                        goalKeeperSlashCommand,
                        cardSlashCommand,
                        contusionSlashCommand,
                        shotSlashCommand,
                        playSlashCommand
                ))
                .build();

        jda.updateCommands().addCommands(
                battleSlashCommand.getCommandData(),
                pingSlashCommand.getCommandData(),
                bonusDiceSlashCommand.getCommandData(),
                distanceSlashCommand.getCommandData(),
                goalKeeperSlashCommand.getCommandData(),
                cardSlashCommand.getCommandData(),
                contusionSlashCommand.getCommandData(),
                shotSlashCommand.getCommandData(),
                playSlashCommand.getCommandData()
        ).queue();

        return jda;
    }
}
