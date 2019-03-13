package ru.otus.igorr.lesson04.domain.question;

import java.util.HashMap;

public enum ItemType {
    Q("Q"),
    A("A");

    private final String code;

    ItemType(String code){
        this.code = code;
    }

    private static final HashMap<String, ItemType> CODE_MAP = new HashMap<>();
    static {
       for(ItemType item : ItemType.values()) {
           CODE_MAP.put(item.code, item);
       }
    }

    public String getCode() {
        return code;
    }

    public static ItemType getValueOf(String code) {
        return CODE_MAP.get(code.toUpperCase());
    }
}
