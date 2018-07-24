# SCF Apollo Configuration Source

## maven dependency

apollo-client is not in the central repository, please use your owner repo release, see also: [apollo-client](https://github.com/ctripcorp/apollo/wiki/Java%E5%AE%A2%E6%88%B7%E7%AB%AF%E4%BD%BF%E7%94%A8%E6%8C%87%E5%8D%97#%E4%BA%8Cmaven-dependency)

```xml
<dependency>
    <groupId>com.ctrip.framework.apollo</groupId>
    <artifactId>apollo-client</artifactId>
    <version>0.10.2</version>
</dependency>
<dependency>
    <groupId>org.mydotey.scfapollo</groupId>
    <artifactId>scf-apollo</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage

```java
@Test
public void testDemo() {
    // create an apollo config
    Config appConfig = ConfigService.getAppConfig();

    // create a scf apollo configuration source
    ApolloConfigurationSourceConfig sourceConfig = new ApolloConfigurationSourceConfig.Builder()
            .setName("apollo-source").setApolloConfig(appConfig).build();
    ApolloConfigurationSource source = new ApolloConfigurationSource(sourceConfig);

    // create scf manager & properties facade tool
    ConfigurationManagerConfig managerConfig = ConfigurationManagers.newConfigBuilder().setName("my-app")
            .addSource(1, source).build();
    ConfigurationManager manager = ConfigurationManagers.newManager(managerConfig);
    StringProperties properties = new StringProperties(manager);

    // use properties
    Property<String, String> appName = properties.getStringProperty("app.name", "unknown");
    System.out.println("app name: " + appName.getValue());

    // add listener for dynamic property
    Property<String, Integer> requestTimeout = properties.getIntProperty("request.timeout", 1000);
    System.out.println("request timeout: " + requestTimeout.getValue());
    requestTimeout.addChangeListener(e -> System.out.println("do something"));
}
```
