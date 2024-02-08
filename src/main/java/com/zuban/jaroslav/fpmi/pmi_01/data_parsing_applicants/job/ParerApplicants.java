package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.job;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service.PersonDataService;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ParerApplicants implements Parser {
    private final String URL = "https://joblab.ru";

    private final PersonDataService personDataService;

    @Autowired
    public ParerApplicants(PersonDataService personDataService) {
        this.personDataService = personDataService;
    }

    @Override
    @Scheduled(fixedDelay = 10000)
    public void parser() {
        int id = 5645136;

        try {
            Document document = Jsoup.connect(URL)
                    .userAgent("Chrome")
                    .timeout(5000)
                    .referrer("https://google.com")
                    .get();

            Element table = document.select("table.table-to-div").first();

            if (table != null) {
                Elements rows = table.select("tr");

                for (Element row : rows) {
                    Elements cells = row.select("td");
                    if (cells.size() == 2) { // Ensure there are exactly two cells in a row
                        Element categoryElement = cells.get(0).selectFirst("p.graytext");
                        Element valueElement = cells.get(1).selectFirst("p");

                        if (categoryElement != null && valueElement != null) {
                            String category = categoryElement.text();
                            String value = valueElement.text();

                            System.out.println(category + ": " + value);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
