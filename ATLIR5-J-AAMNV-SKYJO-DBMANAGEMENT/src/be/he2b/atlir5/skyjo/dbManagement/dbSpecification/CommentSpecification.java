package be.he2b.atlir5.skyjo.dbManagement.dbSpecification;

/**
 *
 * @author jj
 */
public class CommentSpecification {
    private int id;
    private String comment;

    public CommentSpecification(int id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }
    
    
}
