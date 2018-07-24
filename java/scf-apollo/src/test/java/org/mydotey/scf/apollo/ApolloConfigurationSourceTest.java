package org.mydotey.scf.apollo;

import org.junit.Test;
import org.mydotey.scf.ConfigurationManager;
import org.mydotey.scf.ConfigurationManagerConfig;
import org.mydotey.scf.Property;
import org.mydotey.scf.facade.ConfigurationManagers;
import org.mydotey.scf.facade.StringProperties;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

/**
 * @author koqizhao
 *
 * Jul 20, 2018
 */
public class ApolloConfigurationSourceTest {

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

}
