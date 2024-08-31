package soccerbot;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SlashCommandsEnum {
    PING("ping"),
    HELLO("hello"),
    BATTLE("bitwa"),
    BOND6("bonusd6"),
    DISTANCE("dystans"),
    GOALKEEPER("obrona"),
    CARD("kartka"),
    CONTUSION("kontuzja"),
    SHOT("strzal"),
    PLAY("zagranie"),
    ;

    private final String name;

    SlashCommandsEnum(String name) {
        this.name = name;
    }

    public static SlashCommandsEnum fromStringName(String name) {
        return Arrays.stream(SlashCommandsEnum.values())
                .filter(value -> value.name.equals(name))
                .findFirst()
                .orElse(null);
    }
}
