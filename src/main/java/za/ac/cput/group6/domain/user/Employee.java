package za.ac.cput.group6.domain.user;
/**Employee.java
 * Domain  for Employee
 * Author: Nkuna Justin(219319820)
 * Date: 09 June 2022
 */


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Objects;


@Entity
public class Employee implements Serializable {
    @Id
    @Column
    private String staffId;
        @Column
        private String email;

    @OneToOne(mappedBy="name")
    private Name name;



    public Employee(Builder builder){

    this.staffId=builder.staffId;
    this.email=builder.email;
    this.name=builder.name;



    }

    public Employee() {

    }


    public String getStaffId() {
        return staffId;
    }

    public String getEmail() {
        return email;
    }

    public Name getName() {
        return name;
    }

    public static class Builder {
private String staffId,email;
        private Name name;


        public Builder staffId(String staffId) {
            this.staffId = staffId;
            return this;
        }
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        public Builder name(Name name) {
            this.name = name;
            return this;
        }

        public Builder copy(Employee employee) {
            this.staffId = employee.staffId;

            this.email = employee.email;

            this.name = employee.name;

            return this;
        }

        public Employee build() {
            return new Employee(this);
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setStaffId(String staffId) {
            this.staffId = staffId;
        }
    }
    @Override
    public boolean equals(Object o){

    if(this==o)return true;
    if(o== null || getClass() != o.getClass() )return false;
    Employee employee=(Employee) o;
    return staffId.equals(employee.staffId);



    }
    @Override
    public int hashCode(){

    return Objects.hash(staffId);

    }

    @Override
    public String toString() {
        return "Employee{" +
                "staffId='" + staffId + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
