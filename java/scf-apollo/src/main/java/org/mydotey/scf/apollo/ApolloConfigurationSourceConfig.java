package org.mydotey.scf.apollo;

import java.util.Objects;

import org.mydotey.scf.DefaultConfigurationSourceConfig;

import com.ctrip.framework.apollo.Config;

/**
 * @author koqizhao
 *
 * Jul 24, 2018
 */
public class ApolloConfigurationSourceConfig extends DefaultConfigurationSourceConfig {

    private Config _apolloConfig;

    protected ApolloConfigurationSourceConfig() {

    }

    public Config getApolloConfig() {
        return _apolloConfig;
    }

    public static class Builder extends DefaultConfigurationSourceConfig.DefaultAbstractBuilder<Builder> {

        @Override
        protected DefaultConfigurationSourceConfig newConfig() {
            return new ApolloConfigurationSourceConfig();
        }

        @Override
        protected ApolloConfigurationSourceConfig getConfig() {
            return (ApolloConfigurationSourceConfig) super.getConfig();
        }

        public Builder setApolloConfig(Config config) {
            getConfig()._apolloConfig = config;
            return this;
        }

        @Override
        public ApolloConfigurationSourceConfig build() {
            Objects.requireNonNull(getConfig()._apolloConfig, "apolloConfig is null");

            return (ApolloConfigurationSourceConfig) super.build();
        }

    }

}
