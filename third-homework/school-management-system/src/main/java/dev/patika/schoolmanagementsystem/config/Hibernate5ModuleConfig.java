package dev.patika.schoolmanagementsystem.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Hibernate5ModuleConfig {

    @Bean
    public Module hibernate5Module() {

        Hibernate5Module module = new Hibernate5Module();
        module.configure(Hibernate5Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS, true);

        return module;
    }

}
