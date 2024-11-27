import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class GitHubSoftAssertionsJUnit5 {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://github.com/selenide/selenide";
    }

    @Test
    void gitHubSoftAssertionsLinkFullList() {

        // Opening Selenide wiki page
        open(""); //remove
        $("#wiki-tab").click();

        // Search by expanding link list
        $("#wiki-pages-box .js-wiki-more-pages-link").click(); // Expanding list
        $("#wiki-pages-box").shouldHave(text("SoftAssertions")); // Link is displayed
        $("#wiki-pages-box").$(byText("SoftAssertions")).
                shouldHave(attribute("href", "https://github.com/selenide/selenide/wiki/SoftAssertions")); // Correct link value

        System.out.println("[PASSED] Wiki main page: SoftAssertions Link exists [expanded list]");
    }

    @Test
    void gitHubSoftAssertionsLinkAfterSearcht() {

        // Opening Selenide wiki page
        open(""); //remove
        $("#wiki-tab").click();

        // Search by filtering link list
        $("#wiki-pages-box #wiki-pages-filter").append("SoftAssertions"); // Filtering list
        $("#wiki-pages-box").shouldHave(text("SoftAssertions")); // Link is displayed
        $("#wiki-pages-box").$(byText("SoftAssertions")).
                shouldHave(attribute("href", "https://github.com/selenide/selenide/wiki/SoftAssertions")); // Correct link value

        System.out.println("[PASSED] Wiki main page: SoftAssertions Link exists [search field]");
    }

    @Test
    void gitHubSoftAssertionsJUnit5Code() {

        // Open SoftAssertions wiki page
        open("/wiki/SoftAssertions#3-using-junit5-extend-test-class");

        // Searching code block after the JUnit5 header
        // Current wiki structure hase code block right after the header => checking sibling div
        $(withText("Using JUnit5 extend test class:")).shouldBe(visible);
        $(withText("Using JUnit5 extend test class:")).parent().sibling(0).
                shouldHave(text("@ExtendWith({SoftAssertsExtension.class}) " +
                        "class Tests { " +
                        "@Test void test() { Configuration.assertionMode = SOFT;" +
                        " open(\"page.html\"); " +
                        "$(\"#first\").should(visible).click(); " +
                        "$(\"#second\").should(visible).click(); " +
                        "} }"));

        System.out.println("[PASSED] SoftAssertions page: JUnit5 code exists");
    }

}

