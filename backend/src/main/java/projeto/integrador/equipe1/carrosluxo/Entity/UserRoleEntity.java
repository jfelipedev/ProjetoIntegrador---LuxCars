package projeto.integrador.equipe1.carrosluxo.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity(name = "roles")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "name_role")
    @Enumerated(EnumType.STRING)
    private UserRoles roleName;

    @OneToMany(mappedBy = "role")
    private List<UserEntity> users;

    public UserRoleEntity(long id, UserRoles roleName, List<UserEntity> users) {
        this.id = id;
        this.roleName = roleName;
        this.users = users;
    }

    public UserRoleEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserRoles getRoleName() {
        return roleName;
    }

    public void setRoleName(UserRoles roleName) {
        this.roleName = roleName;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
