package leave.mgt.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @Column(name="end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private Integer duration;
    private String reason;
    private String status;
    @ManyToOne
    @JoinColumn(name="approver_id")
    private Employee approverId;
    @Column(name="requested_date")
    private Date requestedDate;
    @Column(name="comments")
    private String otherComments;
    @ManyToMany
    @JoinTable(
            name="employee_leave",
            joinColumns = @JoinColumn(name="leave_id"),
            inverseJoinColumns = @JoinColumn(name="employee_id")
    )
    private Set<Employee> employees;

    public Leave() {
    }

    public Leave(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Leave(Integer id, Date startDate, Date endDate, Integer duration, String reason, String status, Employee approverId, Date requestedDate, String otherComments, Set<Employee> employees) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.reason = reason;
        this.status = status;
        this.approverId = approverId;
        this.requestedDate = requestedDate;
        this.otherComments = otherComments;
        this.employees = employees;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getApproverId() {
        return approverId;
    }

    public void setApproverId(Employee approverId) {
        this.approverId = approverId;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getOtherComments() {
        return otherComments;
    }

    public void setOtherComments(String otherComments) {
        this.otherComments = otherComments;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
