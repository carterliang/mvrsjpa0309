package model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

public class UserDAO {
	private List <User> users = new ArrayList <User> ();
    public UserDAO() {
    	 users.add(new User(100L, "Amy", "amy@gmail.com"));
         users.add(new User(101L, "Mary", "mary@gmail.com"));
         users.add(new User(102L, "Tom", "tom@gmail.com"));
    }
    public boolean create(User user) {
        return users.add(user);
    }

    public List <User> findAll() {
       
        return users;
    }
    public User fetchBy(long id) throws NotFoundException {
//        for (User user: findAll()) {
//            if (id == user.getId()) {
//                return user;
//            } else {
//                throw new NotFoundException("Resource not found with Id :: " + id);
//            }
//        }
//        return null;
    	User us1=users.stream().filter(u->u.getId()==id).findAny().orElse(null);
    	return us1;
    }
    public boolean update(long id,User user) {
        for (User updateUser: users) {
//            if (updateUser.getId().equals(id)) {
//                users.remove(updateUser);
//                users.add(user);
//                return true;
//            }
        	 if (updateUser.getId().equals(id)) {
        		 updateUser.setName(user.getName());
        		 updateUser.setEmail(user.getEmail());
        		 return true;
        	 }
        }
        return false;
    }
    public boolean delete(Long id) throws NotFoundException {
        for (User user: users) {
            if (user.getId().equals(id)) {
                users.remove(user);
                return true;
            }
        }
        return false;
    }


}
