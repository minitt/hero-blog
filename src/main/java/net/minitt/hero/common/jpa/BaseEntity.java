package net.minitt.hero.common.jpa;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;

/**
 * Created by zt on 2017/5/17.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
	public interface BaseJsonView {};
    private static final long serialVersionUID = 1L;
    @Transient
    private int hashCode = Integer.MIN_VALUE;
    

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(BaseJsonView.class)
    protected Integer id;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
    	this.hashCode = Integer.MIN_VALUE;
        this.id = id;
    }
    
    public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}
}
