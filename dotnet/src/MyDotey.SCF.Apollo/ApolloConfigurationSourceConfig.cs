using System;

using Com.Ctrip.Framework.Apollo;

namespace MyDotey.SCF.Apollo
{
    /**
     * @author koqizhao
     *
     * Jul 24, 2018
     */
    public class ApolloConfigurationSourceConfig : DefaultConfigurationSourceConfig
    {
        private IConfig _apolloConfig;

        protected ApolloConfigurationSourceConfig()
        {

        }

        public virtual IConfig ApolloConfig { get { return _apolloConfig; } }

        public new class Builder
                : DefaultConfigurationSourceConfig.DefaultAbstractBuilder<Builder, ApolloConfigurationSourceConfig>
        {
            protected override ApolloConfigurationSourceConfig NewConfig()
            {
                return new ApolloConfigurationSourceConfig();
            }

            public Builder SetApolloConfig(IConfig config)
            {
                Config._apolloConfig = config;
                return this;
            }

            public override ApolloConfigurationSourceConfig Build()
            {
                if (Config._apolloConfig == null)
                    throw new ArgumentNullException("apolloConfig is null");

                return base.Build();
            }
        }
    }
}