package projeto.integrador.equipe1.carrosluxo.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import projeto.integrador.equipe1.carrosluxo.Controller.CarController;

public class disponibilidade implements Job {
    @Autowired
    CarController carController;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        carController.availabilitySave();
    }
}