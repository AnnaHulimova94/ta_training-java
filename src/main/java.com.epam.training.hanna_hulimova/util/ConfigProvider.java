package util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {

    Config config = readConfig();

    String URL_PASTE_BIN = config.getString("task_1.url");

    String URL_GOOGLE_CLOUD = config.getString("task_2.url");

    String SEARCH_TEXT_GOOGLE = config.getString("task_2.search_text");

    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("application.conf");
    }
}
