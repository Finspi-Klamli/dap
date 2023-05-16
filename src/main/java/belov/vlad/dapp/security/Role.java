package belov.vlad.dapp.security;

import belov.vlad.dapp.model.Permission;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.USER_PERMISSION)),
    ADMIN(Set.of(Permission.DEVELOPER_READ,
            Permission.DEVELOPER_WRITE,
            Permission.ADMIN_PERMISSION,
            Permission.USER_PERMISSION,
            Permission.USER_READ,
            Permission.USER_UPDATE,
            Permission.USER_DELETE)),
    MODERATOR(Set.of(Permission.DEVELOPER_READ,
            Permission.DEVELOPER_WRITE,
            Permission.ADMIN_PERMISSION,
            Permission.USER_CREATE,
            Permission.USER_READ,
            Permission.USER_UPDATE,
            Permission.USER_DELETE));
    private final Set<Permission> permissions;
    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
