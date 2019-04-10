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

@Entity
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
	
	public Integer getLogic() {
		return logic;
	}
	public void setLogic(Integer logic) {
		this.logic = logic;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getSam() {
		return sam;
	}
	public void setSam(int sam) {
		this.sam = sam;
	}
	public String getPtid() {
		return ptid;
	}
	public void setPtid(String ptid) {
		this.ptid = ptid;
	}
	public int getPlat() {
		return plat;
	}
	public void setPlat(int plat) {
		this.plat = plat;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getMxr() {
		return mxr;
	}
	public void setMxr(int mxr) {
		this.mxr = mxr;
	}
	public int getMxf() {
		return mxf;
	}
	public void setMxf(int mxf) {
		this.mxf = mxf;
	}
	public String getVERFM() {
		return VERFM;
	}
	public void setVERFM(String VERFM) {
		this.VERFM = VERFM;
	}
	
	public static Validator createValidator() {
        Configuration<?> config = Validation.byDefaultProvider().configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        Validator validator = factory.getValidator();
        factory.close();
        return validator;
    }
}
