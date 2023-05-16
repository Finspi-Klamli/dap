package belov.vlad.dapp.services;

import belov.vlad.dapp.model.User;
import belov.vlad.dapp.model.UserDataChange;

import java.util.List;

public interface UserDataChangeService {
    void saveUserDataChange(UserDataChange userDataChange);
    List<UserDataChange> getUserDataChangesByUserId(Long userId);
    void deleteUserDataChange(Long id);
    List<UserDataChange> getAllUserDataChanges();
    void updateUserDataChange(UserDataChange userDataChange);
    void saveUserDataChange(User oldUser, User newUser);
}
