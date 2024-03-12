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
    private final ResumeParsing resumeParsing;
    Boolean is = true;

    @Autowired
    public ApplicantsIdParser(ResumeParsing resumeParsing) {
        this.resumeParsing = resumeParsing;
    }

    @Scheduled(fixedDelay = 1000)
    public void parser() throws InterruptedException {
        final int pagesCount = 168; // количество страниц на сайте пока просто поставлю 2 чтобы легче можно было работать и провести тест
        resumeParsing.parser("3305832");

        Thread.sleep(100000);

        /*
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

                Thread.sleep(10000);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }*/
    }
}
