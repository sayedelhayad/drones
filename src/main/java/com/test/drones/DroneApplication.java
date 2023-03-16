package com.test.drones;import org.springframework.boot.SpringApplication;import org.springframework.boot.autoconfigure.SpringBootApplication;import org.springframework.boot.builder.SpringApplicationBuilder;import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;import org.springframework.data.jpa.repository.config.EnableJpaAuditing;import org.springframework.scheduling.annotation.EnableScheduling;@SpringBootApplication@EnableScheduling@EnableJpaAuditingpublic class DroneApplication extends SpringBootServletInitializer {    /**     * ${@inheritDoc}     */    @Override    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {        return application.sources(DroneApplication.class);    }    /**     * The main entry point for the application     *     * @param args the arguments     */    public static void main(String[] args) {        SpringApplication.run(DroneApplication.class, args);    }}