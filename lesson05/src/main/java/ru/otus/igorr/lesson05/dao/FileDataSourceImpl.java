package ru.otus.igorr.lesson05.dao;

import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Repository;
import ru.otus.igorr.lesson05.config.DataSourceProps;
import ru.otus.igorr.lesson05.domain.question.Item;
import ru.otus.igorr.lesson05.domain.question.ItemType;
import ru.otus.igorr.lesson05.domain.question.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository("fileDataSource")
@EnableConfigurationProperties(DataSourceProps.class)
public class FileDataSourceImpl implements DataSource {
    private static final Logger LOG = LoggerFactory.getLogger(FileDataSourceImpl.class);

    private final DataSourceProps props;

    public FileDataSourceImpl(DataSourceProps props) {
        this.props = props;
        LOG.debug("--------------------->" + props.getFile());
    }

    @Override
    public List<Question> prepareList() {
        final List<Question> result = new ArrayList<>();

        try {
            final List<Item> qaList = readData(props.getFile());

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

    private List<Item> readData(String questionsListFileName) throws IOException {
        List<Item> result = new ArrayList<>();

        //Build reader instance
        String pathToData = this.getClass().getClassLoader().
                getResource(questionsListFileName).getPath();

        FileReader fr = new FileReader(pathToData);
        BufferedReader br = new BufferedReader(fr);

        try (CSVReader reader = new CSVReader(br)) {
            //Read all rows at once
            List<String[]> allRows = reader.readAll();
            allRows.forEach(row -> result.add(new Item(row)));
        }
        return result;
    }


}
