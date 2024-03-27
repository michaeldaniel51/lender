package com.bravewood.safelenderbatchprocessing.payroll.batchconfig;


import com.bravewood.safelenderbatchprocessing.payroll.domain.Payroll;
import com.bravewood.safelenderbatchprocessing.payroll.repository.PayrollRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;


@RequiredArgsConstructor
@Configuration
public class BatchConfig {

    private final PayrollRepo payrollRepo;


    @Bean
    @StepScope
    public FlatFileItemReader<Payroll> itemReader(@Value("#{jobParameters[fullPathFileName]}")String pathToFile) {

        FlatFileItemReader<Payroll> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource(pathToFile));
        itemReader.setName("csv-reader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }


    private LineMapper<Payroll> lineMapper() {

        DefaultLineMapper<Payroll> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setNames("EmployeeNumber","FullName","Department","Ministry","BankName","BankCode","AccountNo","GradeLevel","GrossEarnings","GrossDeductions","NetPay");
        tokenizer.setStrict(false);

        BeanWrapperFieldSetMapper mapper = new BeanWrapperFieldSetMapper<>();
        mapper.setTargetType(Payroll.class);

        lineMapper.setFieldSetMapper(mapper);
        lineMapper.setLineTokenizer(tokenizer);
        return lineMapper;
    }

    @Bean
    public PayrollProcessor processor (){
        return new PayrollProcessor();
    }


    @Bean
    public RepositoryItemWriter<Payroll> itemWriter(){

        RepositoryItemWriter<Payroll> writer = new RepositoryItemWriter<>();
        writer.setRepository(payrollRepo);
        writer.setMethodName("save");
        return writer;

    }

    @Bean
    public Step step(FlatFileItemReader<Payroll> itemReader, JobRepository repository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("csv-step",repository)
                .<Payroll, Payroll>chunk(3,platformTransactionManager)
                .reader(itemReader)
                .processor(processor())
                .writer(itemWriter())
                .build();
    }


    @Bean
    public Job job(FlatFileItemReader<Payroll> itemReader, JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){

        return new JobBuilder("csv-job",jobRepository)
                .flow(step(itemReader,jobRepository, platformTransactionManager))
                .end()
                .build();

    }


}
