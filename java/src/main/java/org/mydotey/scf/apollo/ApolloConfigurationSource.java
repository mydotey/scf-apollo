package org.mydotey.scf.apollo;

import org.mydotey.scf.source.stringproperty.StringPropertyConfigurationSource;

/**
 * @author koqizhao
 *
 * Jul 24, 2018
 */
public class ApolloConfigurationSource extends StringPropertyConfigurationSource<ApolloConfigurationSourceConfig> {

    public ApolloConfigurationSource(ApolloConfigurationSourceConfig config) {
        super(config);
        getConfig().getApolloConfig().addChangeListener(e -> ApolloConfigurationSource.this.raiseChangeEvent());
    }

    @Override
    public String getPropertyValue(String key) {
        return getConfig().getApolloConfig().getProperty(key, null);
    }

}
