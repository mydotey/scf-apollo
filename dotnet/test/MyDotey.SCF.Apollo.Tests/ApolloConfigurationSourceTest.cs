using System;

using Xunit;
using Com.Ctrip.Framework.Apollo;
using MyDotey.SCF.Facade;

namespace MyDotey.SCF.Apollo
{
    /**
     * @author koqizhao
     *
     * Jul 20, 2018
     */
    public class ApolloConfigurationSourceTest
    {
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
            ConfigurationManagerConfig managerConfig = ConfigurationManagers.NewConfigBuilder().SetName("my-app")
                    .AddSource(1, source).Build();
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
    }
}