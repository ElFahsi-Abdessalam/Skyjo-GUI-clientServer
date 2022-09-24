package be.he2b.atlir5.skyjo.dbManagement.dbSpecification;

/**
 *
 * @author mad8
 */
public class UserSpecification {
    private int id;
    private String name;
    /**
     *
     * @param name

     */
    public UserSpecification(String name) {
        this.id = 0;
        this.name = name;
    }

    /**
     *
     * @param id
     */
    public UserSpecification(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setMail(String name) {
        this.name = name;
    }
}


