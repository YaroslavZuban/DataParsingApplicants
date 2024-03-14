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
    private final static int SECOND = 1000;
    private final static int MINUTE = 60 * SECOND;
    private final ResumeParsing resumeParsing;
    private static String firstResume = null;

    @Autowired
    public ApplicantsIdParser(ResumeParsing resumeParsing) {
        this.resumeParsing = resumeParsing;
    }

    @Scheduled(fixedDelay = 5 * MINUTE)
    public void parser() throws InterruptedException {
        final int pagesCount = 167; // количество страниц на сайте пока просто поставлю 2 чтобы легче можно было работать и провести тест
        final String URL = "https://joblab.ru/search.php?r=res&srregion=100&page=";
        final String key = "&submit=1";

        boolean isRestart = true;
        String firstResumeRestart = null;

        outerLoop:
        for (int i = 1; i < pagesCount; i++) {
            try {
                Document document = Jsoup.connect(URL + i + key)
                        .userAgent("Chrome")
                        .timeout(5000)
                        .referrer("https://google.com")
                        .get();

                Elements linkApplicants = document.select("p.prof a");

                for (Element element : linkApplicants) {
                    String resume = element.attr("href");// возвращается ссылка на резюме

                    if (isRestart) {
                        firstResumeRestart = resume;
                        isRestart = false;
                    }

                    if (firstResume != null && firstResume.equals(resume)) {
                        firstResume = firstResumeRestart;
                        break outerLoop;
                    }

                    resumeParsing.parser(resume);

                    if (firstResume == null) {
                        firstResume = resume;
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
