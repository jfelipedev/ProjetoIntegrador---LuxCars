package projeto.integrador.equipe1.carrosluxo.Configuraton;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import projeto.integrador.equipe1.carrosluxo.jobs.disponibilidade;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(disponibilidade.class)
                .withIdentity("Disponibilidade")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail job) {
        return TriggerBuilder.newTrigger()
                .forJob(job)
                .withIdentity("Disponibilidade")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/10 * * * ?"))
                .build();
    }
}