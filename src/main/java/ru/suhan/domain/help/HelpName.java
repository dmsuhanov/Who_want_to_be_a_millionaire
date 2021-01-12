package ru.suhan.domain.help;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum HelpName {

    PEOPLE("people_help"),
    FIFTY_FIFTY("fifty_fifty"),
    CALL_FRIEND("call_friend"),
    REPLACE("replacement_question"),
    MISTAKES("right_to_make_mistakes"),
    ;

    private final String codeName;
}
