package enterprise.sample.com.listview_multi_selected_row;

import java.io.Serializable;

public class Student implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String name;

    private String emailId;

    private String Remarks;

    private boolean isSelected;

    public Student() {
    }

    public Student(String name, String emailId, String remarks) {
        this.name = name;
        this.emailId = emailId;
        Remarks = remarks;
    }

    public Student(String name, String emailId, String remarks, boolean isSelected) {
        this.name = name;
        this.emailId = emailId;
        Remarks = remarks;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
