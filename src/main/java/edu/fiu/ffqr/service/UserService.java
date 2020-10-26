package edu.fiu.ffqr.service;

import edu.fiu.ffqr.models.Clinician;
import edu.fiu.ffqr.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.List;

public abstract class UserService<U extends User, A extends MongoRepository<U, String>> {
    protected A repository;

    protected String encodePassword(String userpassword) {
        int strength = 10; // work factor of bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder(strength, new SecureRandom());
        return bCryptPasswordEncoder.encode(userpassword);
    }

    public List<U> getAll()	{
        return repository.findAll();
    }

    public String getNextId() {
        return String.valueOf(getAll().stream()
                .map(User::getUserId)
                .map(Integer::parseInt)
                .max(Integer::compare)
                .orElse(0)
                + 1);
    }

    public U create(U user) {
        U updatedUser = user;
        if (user.getUserId().isEmpty()){
            updatedUser.setUserId(this.getNextId());
        }
        if (user.getUsername().isEmpty()){
            updatedUser.setUsername(user.getUsertype() + this.getNextId());
            updatedUser.setUserpassword(user.getUsertype() + this.getNextId());
        }
        String encodedPassword = encodePassword(updatedUser.getUserpassword());
        updatedUser.setUserpassword(encodedPassword);
        return this.repository.save(updatedUser);
    }
}
