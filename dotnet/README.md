# SCF Apollo Configuration Source

## NuGet Package

```sh
dotnet add package MyDotey.SCF.Apollo --version 1.2.1
```

Or use a single meta package

```sh
dotnet add package MyDotey.SCF.Bom -v 1.5.1
```

## Usage

```c#
[Fact]
public virtual void TestDemo()
{
    // create an apollo config
    IConfig appConfig = ApolloConfigurationManager.GetAppConfig().Result;

    // create a scf apollo configuration source
    ApolloConfigurationSourceConfig sourceConfig = new ApolloConfigurationSourceConfig.Builder()
        .SetName("apollo-source").SetApolloConfig(appConfig).Build();
    ApolloConfigurationSource source = new ApolloConfigurationSource(sourceConfig);

    // create scf manager & properties facade tool
    ConfigurationManagerConfig managerConfig = ConfigurationManagers.NewConfigBuilder()
        .SetName("my-app").AddSource(1, source).Build();
    IConfigurationManager manager = ConfigurationManagers.NewManager(managerConfig);
    StringProperties properties = new StringProperties(manager);

    // use properties
    IProperty<string, string> appName = properties.GetStringProperty("app.name", "unknown");
    Console.WriteLine("app name: " + appName.Value);

    // add listener for dynamic property
    IProperty<string, int?> requestTimeout = properties.GetIntProperty("request.timeout", 1000);
    Console.WriteLine("request timeout: " + requestTimeout.Value);
    requestTimeout.OnChange += (o, e) => Console.WriteLine("do something");
}
```
