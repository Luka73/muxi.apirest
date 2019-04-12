package com.muxi.apirest.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Configuration;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@JsonInclude(Include.NON_NULL)
@Table(name="TB_TERMINAL")
public class Terminal {
	@Id
	private Integer logic;
	
	@NotEmpty(message="Serial is a required field")
	@NotBlank(message="Serial can't be blank")
	private String serial;
	
	@NotEmpty(message="Model is a required field")
	@NotBlank(message="Model can't be blank")
	private String model;
	
	@Min(0)
	private int sam;
	private String ptid;
	private int plat;
	
	@NotEmpty(message="Version is a required field")
	@NotBlank(message="Version can't be blank")
	private String version;
	
	private int mxr;
	private int mxf;
	private String VERFM;
	
	
	public static Validator createValidator() {
        Configuration<?> config = Validation.byDefaultProvider().configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        Validator validator = factory.getValidator();
        factory.close();
        return validator;
    }
}
