package net.minitt.hero.common.jpa;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zt on 2017/5/17.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Integer id;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
