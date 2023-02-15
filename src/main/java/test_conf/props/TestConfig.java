package test_conf.props;

public enum TestConfig {
    URI("uri"),
    PATH("path");

    public final String Value;

    TestConfig(String value) {
        this.Value = Configuration.getConfiguration().getProperty(value);
    }
}
