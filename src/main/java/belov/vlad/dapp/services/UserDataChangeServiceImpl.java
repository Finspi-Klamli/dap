package belov.vlad.dapp.services;

import belov.vlad.dapp.model.User;
import belov.vlad.dapp.model.UserDataChange;
import belov.vlad.dapp.repository.UserDataChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDataChangeServiceImpl implements UserDataChangeService {
    @Autowired
    private final UserDataChangeRepository userDataChangeRepository;

    public UserDataChangeServiceImpl(UserDataChangeRepository userDataChangeRepository) {
        this.userDataChangeRepository = userDataChangeRepository;
    }


    @Override
    public void saveUserDataChange(UserDataChange userDataChange) {
        userDataChangeRepository.save(userDataChange);
    }

    @Override
    public List<UserDataChange> getUserDataChangesByUserId(Long userId) {
        return userDataChangeRepository.findById(userId).stream().collect(Collectors.toList());
    }
    @Override
    public void deleteUserDataChange(Long id) {
        userDataChangeRepository.deleteById(id);
    }

    @Override
    public List<UserDataChange> getAllUserDataChanges() {
        return userDataChangeRepository.findAll();
    }
    @Override
    public void updateUserDataChange(UserDataChange userDataChange) {
        userDataChangeRepository.save(userDataChange);
    }

    @Override
    public void saveUserDataChange(User oldUser, User newUser) {
        Field[] fields  = oldUser.getClass().getDeclaredFields();
        for(Field f : fields){
            try {
                Field field1 = oldUser.getClass().getDeclaredField(f.getName());
                Field field2 = newUser.getClass().getDeclaredField(f.getName());
                field1.setAccessible(true);
                field2.setAccessible(true);
                Object ou = field1.get(oldUser);
                Object nu = field2.get(newUser);
                if(nu.equals(ou)){
                    System.out.println(ou);
                    System.out.println(nu);
                }else {
                    UserDataChange userDataChange = new UserDataChange();
                    userDataChange.setUser(newUser);
                    userDataChange.setFieldName(f.getName());
                    userDataChange.setNewValue(nu.toString());
                    userDataChange.setOldValue(ou.toString());
                    userDataChangeRepository.save(userDataChange);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }
    }
}