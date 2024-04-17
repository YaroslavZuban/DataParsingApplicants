package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.job;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service.PersonDataService;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Component
public class ResumeParsing {

    private final Container container;

    @Autowired
    public ResumeParsing(Container container) {
        this.container = container;
    }


    public void parser(String link) {
        try {
            String url = "https://joblab.ru";

            Document document = Jsoup.connect(url + link)
                    .userAgent("Chrome")
                    .timeout(5000)
                    .referrer("https://google.com")
                    .get();

            // Проверяем наличие элемента <h1>
            Element h1Element = document.selectFirst("h1");
            if (h1Element != null) {
                String title = h1Element.text();
                container.completionResume("Title", title);
            }

            Element table = document.select("table.table-to-div").first();

            if (table != null) {
                Elements rows = table.select("tr");

                for (Element row : rows) {
                    Elements cells = row.select("td");

                    if (cells.size() == 2) { // Ensure there are exactly two cells in a row
                        Element categoryElement = cells.get(0).selectFirst("p.graytext");
                        Elements valueElements = cells.get(1).select("p");

                        if (categoryElement != null && valueElements != null && !valueElements.isEmpty()) {
                            String category = categoryElement.text();
                            StringBuilder values = new StringBuilder();

                            for (int i = 0; i < valueElements.size(); i++) {
                                Element valueElement = valueElements.get(i);
                                values.append(valueElement.text());

                                if (i + 1 != valueElements.size()) {
                                    valueElement.append("\n");
                                }
                            }

                            container.completionResume(category, String.valueOf(values));
                        }
                    }
                }
            }

            container.save();
            container.clearResume();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
