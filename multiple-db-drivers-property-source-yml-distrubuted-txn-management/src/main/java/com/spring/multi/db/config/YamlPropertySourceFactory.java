package com.spring.multi.db.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.util.Properties;

/**
 * This class handles the YAML file processing
 */
public class YamlPropertySourceFactory extends DefaultPropertySourceFactory {
        @Override
        public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
                String sourceName = name != null ? name : resource.getResource().getFilename();
                if (!resource.getResource().exists()) {
                        return new PropertiesPropertySource(sourceName, new Properties());
                } else if (sourceName.endsWith(".yml") || sourceName.endsWith(".yaml")) {
                        Properties propertiesFromYaml = loadYml(resource);
                        return new PropertiesPropertySource(sourceName, propertiesFromYaml);
                } else {
                        return super.createPropertySource(name, resource);
                }
        }

        private Properties loadYml(EncodedResource resource) {
                YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
                factory.setResources(resource.getResource());
                factory.afterPropertiesSet();
                return factory.getObject();
        }
}


/*public class YamlPropertySourceFactory implements PropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(@Nullable String name, EncodedResource encodedResource) throws IOException {
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(encodedResource.getResource());
        factory.afterPropertiesSet();
        Properties properties = factory.getObject();
        String sourceName = name != null ? name : encodedResource.getResource().getFilename();
        return new PropertiesPropertySource(sourceName, properties);
    }
}*/

