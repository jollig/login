package is.ru.honn.rupin.service;

import is.ru.honn.rupin.data.UserDataGateway;
import is.ru.honn.rupin.domain.User;

public interface UserService
{
  public User signup(User user) throws UsernameExistsException;
  public User login(String username, String password);
  public User getUser(String username);

  public void setUserDataGateway(UserDataGateway userDataGateway);
}
