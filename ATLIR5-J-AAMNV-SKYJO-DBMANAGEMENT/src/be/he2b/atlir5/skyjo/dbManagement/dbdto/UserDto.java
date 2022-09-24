package be.he2b.atlir5.skyjo.dbManagement.dbdto;

import be.he2b.atlir5.skyjo.dbManagement.exception.DtoException;
/**
 *
 * @author mad8
 */
public class UserDto extends EntityDto<Integer>{
    private String name;

    /**
     * constructeur d'un user persistant
     *
     * @param id
     * @param name
     * @throws be.he2b.atlir5.skyjo.dbManagement.exception.DtoException
     */
    public UserDto(Integer id, String name) throws DtoException {
        this(name);
        this.id = id;
    }

    /**
     * constructeur d'un user non persistant
     *
     * @param name
     * @throws be.he2b.atlir5.skyjo.dbManagement.exception.DtoException
     */
    public UserDto(String name) throws DtoException {
        if ( name == null) {
            throw new DtoException("les attributs login et  name sont obligatoires");
        }
        this.name = name;

    }

    /**
     *
     * @return mail
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[UserDto] (" + getId() + ") " + getName();
    }
}
