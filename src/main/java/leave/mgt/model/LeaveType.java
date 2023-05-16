package leave.mgt.model;

import javax.persistence.*;

@Entity
public class LeaveType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="type_name")
    private String typeName;
    @Column(name="number_of_days")
    private Integer numberOfDays;

    public LeaveType() {
    }

    public LeaveType(Integer id) {
        this.id = id;
    }

    public LeaveType(Integer id, String typeName, Integer numberOfDays) {
        this.id = id;
        this.typeName = typeName;
        this.numberOfDays = numberOfDays;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
}
