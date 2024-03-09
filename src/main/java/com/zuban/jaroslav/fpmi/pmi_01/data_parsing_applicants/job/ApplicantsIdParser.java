package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.job;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service.PersonDataService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApplicantsIdParser {
    private final String URL = "https://joblab.ru/search.php?r=res&srregion=100&page=";
    private final String key = "&submit=1";
    private final PersonDataService personDataService;
    private final ResumeParsing resumeParsing = new ResumeParsing();
    Boolean is = true;

    @Autowired
    public ApplicantsIdParser(PersonDataService personDataService) {
        this.personDataService = personDataService;
    }

    @Scheduled(fixedDelay = 1000)
    public void parser() {
        final int pagesCount = 168; // количество страниц на сайте пока просто поставлю 2 чтобы легче можно было работать и провести тест

        for (int i = 1; i < 2; i++) {
            try {
                Document document = Jsoup.connect(URL + i + key)
                        .userAgent("Chrome")
                        .timeout(5000)
                        .referrer("https://google.com")
                        .get();

                Elements linkApplicants = document.select("p.prof a");

                for (Element element : linkApplicants) {
                    resumeParsing.parser(element.attr("href"));// возвращается ссылка на резюме
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
