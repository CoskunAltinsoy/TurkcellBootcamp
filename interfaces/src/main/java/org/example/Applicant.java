package org.example;

public class Applicant extends User{
    private String natId;

  /*  public Applicant() {
    }*/

    public Applicant(int id, String firstName, String lastName, String natId) {
        super(id, firstName, lastName);
        this.natId = natId;
    }
    public String getNatId() {
        return natId;
    }

    public void setNatId(String natId) {
        this.natId = natId;
    }
}
