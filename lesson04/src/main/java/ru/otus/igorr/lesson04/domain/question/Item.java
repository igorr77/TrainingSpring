package ru.otus.igorr.lesson04.domain.question;

public class Item {

    private final ItemType type;
    private final int parentId;
    private final int id;
    private final String text;
    private final boolean correct;

    public Item(String[] fields) {
        this.type = ItemType.getValueOf(fields[0].trim().toUpperCase());
        this.parentId = Integer.valueOf(fields[1].trim());
        this.id = Integer.valueOf(fields[2].trim());
        this.text = fields[3].trim();
        this.correct = fields.length == 5 && Boolean.valueOf(fields[4].trim());
    }

    public Item(ItemType type, int parentId, int id, String text, boolean correct) {
        this.type = type;
        this.parentId = parentId;
        this.id = id;
        this.text = text;
        this.correct = correct;
    }


    public ItemType getType() {
        return type;
    }


    public int getParentId() {
        return parentId;
    }


    public int getId() {
        return id;
    }


    public String getText() {
        return text;
    }


    public boolean isCorrect() {
        return correct;
    }


    @Override
    public String toString() {
        return "Item{" +
                "type=" + type +
                ", parentId=" + parentId +
                ", id=" + id +
                ", text='" + text + '\'' +
                ", correct=" + correct +
                '}';
    }
}
