package is.ru.honn.rupin.service;

import is.ru.honn.rupin.data.UserDataGateway;
import is.ru.honn.rupin.domain.User;
import is.ruframework.data.RuDuplicateDataException;

public class UserServiceData implements UserService
{
  private UserDataGateway userDataGateway;

  @Override
  public User signup(User user) throws UsernameExistsException
  {
    try
    {
      userDataGateway.add(user);
    }
    catch (RuDuplicateDataException ddex)
    {
      throw new UsernameExistsException();
    }
    return user;
  }

  @Override
  public User login(String username, String password)
  {
    User user = this.userDataGateway.getUserByUsername(username);
    if (user == null)
      return null;
    if (!user.getPassword().equals(password))
      return null;
    return user;
  }

  @Override
  public User getUser(String username)
  {
    return userDataGateway.getUserByUsername(username);
  }

  public void setUserDataGateway(UserDataGateway userDataGateway)
  {
    this.userDataGateway = userDataGateway;
  }
}
