package utils;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:${user.dir}/src/main/resources/properties/config.properties",
        "system:properties"
})
public interface ConfigProps extends Config {
    @Config.Key("app.url")
    String url();

    @Config.Key("selenium.grid.url")
    String seleniumGridUrl();

    @Config.Key("os.name")
    String osName();
}
