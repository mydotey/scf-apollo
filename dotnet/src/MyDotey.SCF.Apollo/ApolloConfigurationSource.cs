using System;

using Com.Ctrip.Framework.Apollo;

using MyDotey.SCF.Source.StringProperty;

namespace MyDotey.SCF.Apollo
{
    /**
     * @author koqizhao
     *
     * Jul 24, 2018
     */
    public class ApolloConfigurationSource : StringPropertyConfigurationSource<ApolloConfigurationSourceConfig>
    {
        public ApolloConfigurationSource(ApolloConfigurationSourceConfig config)
            : base(config)
        {
            Config.ApolloConfig.ConfigChanged += (o, e) => RaiseChangeEvent();
        }

        public override string GetPropertyValue(string key)
        {
            return Config.ApolloConfig.GetProperty(key, null);
        }
    }
}