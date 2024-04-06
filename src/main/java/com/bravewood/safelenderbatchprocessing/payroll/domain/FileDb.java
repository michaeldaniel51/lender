package com.bravewood.safelenderbatchprocessing.payroll.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "files")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDb {


    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String type;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;

    @OneToOne
    private PayrollGroup payrollGroup;


    public FileDb(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }


}
