package be.he2b.atlir5.skyjo.dbManagement.dbdto;

/**
 *
 * @author jj
 */
public class CommentDto {
    private int id_user;
    private String comment;

    public int getId_user() {
        return id_user;
    }

    public String getComment() {
        return comment;
    }

    public CommentDto(int id_user, String comment) {
        this.id_user = id_user;
        this.comment = comment;
    }
    
}
