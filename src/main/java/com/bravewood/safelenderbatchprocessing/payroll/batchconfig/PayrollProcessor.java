package com.bravewood.safelenderbatchprocessing.payroll.batchconfig;



import com.bravewood.safelenderbatchprocessing.payroll.domain.Payroll;
import com.bravewood.safelenderbatchprocessing.payroll.domain.PayrollGroup;
import com.bravewood.safelenderbatchprocessing.payroll.exception.PayrollException;
import com.bravewood.safelenderbatchprocessing.payroll.repository.PayrollGroupRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class PayrollProcessor implements ItemProcessor<Payroll, Payroll> {

    @Autowired
    private PayrollGroupRepo payrollGroupRepo;

    private Long payrollGroupId;
    private Long status;
    private String message;

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        JobParameters jobParameters = stepExecution.getJobExecution().getJobParameters();
        payrollGroupId = jobParameters.getLong("payrollGroupId");
        status = jobParameters.getLong("status");
        message = jobParameters.getString("message");
    }




    @Override
    public Payroll process(Payroll item) throws Exception {

       PayrollGroup payroll = payrollGroupRepo.findById(payrollGroupId).orElseThrow(()-> new PayrollException("PayrollGroup not found"));

       item.setPayrollGroup(payroll);
       item.setStatus(status);
       item.setMessage(message);
        log.info(item.toString());
        return item;
    }





}
