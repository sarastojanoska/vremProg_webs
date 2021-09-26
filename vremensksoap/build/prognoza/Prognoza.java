package prognoza;

import java.io.Serializable;
import java.util.List;
import users.User;

public class Prognoza implements Serializable {
    private Integer id;
    private String grad;
    private String momentalna;
    private String momentalnastatus;
    private String in3hours;
    private String status3h;
    private String in1day;
    private String status1d;
    private String in2days;
    private String status2d;
    private String in3days;
    private String status3d;
    private List<String> users;
    
    public Prognoza() { }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return this.id;
    }

    public void setgrad(String grad) {
        this.grad = grad;
    }
    public String getgrad() {
        return this.grad;
    }

    public void setmomentalna(String momentalna) {
        this.momentalna = momentalna;
    }
    public String getmomentalna() {
        return this.momentalna;
    }

    public void setmomentalnastatus(String momentalnastatus) {
        this.momentalnastatus = momentalnastatus;
    }
    public String getmomentalnastatus() {
        return this.momentalnastatus;
    }

    
    public void setin3hours(String in3hours) {
        this.in3hours = in3hours;
    }
    public String getin3hours() {
        return this.in3hours;
    }

    
    public void setstatus3h(String status3h) {
        this.status3h = status3h;
    }
    public String getstatus3h() {
        return this.status3h;
    }

    
    public void setin1day(String in1day) {
        this.in1day = in1day;
    }
    public String getin1day() {
        return this.in1day;
    }

    
    public void setstatus1d(String status1d) {
        this.status1d = status1d;
    }
    public String getstatus1d() {
        return this.status1d;
    }

      
    public void setin2days(String in2days) {
        this.in2days = in2days;
    }
    public String getin2days() {
        return this.in2days;
    }

    
    public void setstatus2d(String status2d) {
        this.status2d = status2d;
    }
    public String getstatus2d() {
        return this.status2d;
    }

    public void setin3days(String in3days) {
        this.in3days = in3days;
    }
    public String getin3days() {
        return this.in3days;
    }

 
    public void setstatus3d(String status3d) {
        this.status3d = status3d;
    }
    public String getstatus3d() {
        return this.status3d;
    }


    public void setUsers(List<String> users) {
        this.users = users;
    }

    public List<String> getUsers() {
        return this.users;
    }
}

