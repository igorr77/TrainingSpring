package ru.otus.igorr.lesson01.dao;

import com.opencsv.CSVReader;
import ru.otus.igorr.lesson01.domain.question.Item;
import ru.otus.igorr.lesson01.domain.question.ItemType;
import ru.otus.igorr.lesson01.domain.question.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileDataSourceImpl implements DataSource {
    private static final Logger LOG = LoggerFactory.getLogger(FileDataSourceImpl.class);

    @Override
    public List<Question> prepareList() {
        final List<Question> result = new ArrayList<>();

        try {
            final List<Item> qaList = readData();

            qaList.stream()
                    .filter(qa -> qa.getType() == ItemType.Q)
                    .forEach(q -> {
                        final Question question = new Question(q.getId(), q.getText());
                        qaList.stream()
                                .filter(qa -> qa.getType() == ItemType.A && qa.getParentId() == question.getId())
                                .forEach(a -> question.addAnswer(a.getId(), a.getText(), a.isCorrect()));
                        result.add(question);
                    });

        } catch (IOException e) {
            LOG.error("Error during execute", e);
        }

        return result;
    }

    // Вообще-то метод должен быть пиватным, но я пока не разобрался как мокать приват в junit5
    public List<Item> readData() throws IOException {
        List<Item> result = new ArrayList<>();

        //Build reader instance
        String pathToData = this.getClass().getClassLoader().
                getResource("data/questions.csv").toString();
        File file = new File(pathToData.substring(5));
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);

        try (CSVReader reader = new CSVReader(br)) {
            //Read all rows at once
            List<String[]> allRows = reader.readAll();
            //for (String[] row : allRows) {
            //    result.add(new Item(row));
            //}

            allRows.stream().forEach(row -> result.add(new Item(row)));
        }


        return result;
    }


}
