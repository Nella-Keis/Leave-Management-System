package leave.mgt.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="started_date")
    private Date startedDate;
    @Column(name="is_active")
    private boolean active;
    @ManyToMany(mappedBy = "employees")
    private Set<Leave> leaves;
    @OneToMany(mappedBy = "approverId")
    private List<Leave> approvedLeave;
    @OneToOne
    @JoinColumn(name="user_id")
    private Users user;
    public Employee() {
    }

    public Employee(Integer id) {
        this.id = id;
    }

    public Employee(Integer id, Date startedDate, boolean active, Set<Leave> leaves, List<Leave> approvedLeave) {
        this.id = id;
        this.startedDate = startedDate;
        this.active = active;
        this.leaves = leaves;
        this.approvedLeave = approvedLeave;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Leave> getLeaves() {
        return leaves;
    }

    public void setLeaves(Set<Leave> leaves) {
        this.leaves = leaves;
    }

    public List<Leave> getApprovedLeave() {
        return approvedLeave;
    }

    public void setApprovedLeave(List<Leave> approvedLeave) {
        this.approvedLeave = approvedLeave;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
