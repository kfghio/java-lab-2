package org.example.Controllers;

import org.example.Enums.Status;
import org.example.Other.ArgumentException;
import org.example.models.Cat;
import org.example.models.Owner;
import org.example.reps.CatRepository;

import java.util.List;
import java.util.UUID;

public class CatController {
    private final CatRepository catRepository = new CatRepository();

    public boolean addCat(Cat cat) {
        try {
            catRepository.insert(cat);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    public boolean deleteCat(UUID id) {
        try {
            Cat cat = catRepository.getById(id).orElseThrow(() -> new ArgumentException("Cat with this ID does not exists"));
            catRepository.delete(cat);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    public Response<Cat> getCatById(UUID id) {
        Response<Cat> response = new Response<>();
        try {
            response.setResultClass(catRepository.getById((id)).orElseThrow(() -> new ArgumentException("Cat with this ID does not exists")));
            response.setStatus(Status.NORM);
        }
        catch (Exception ex) {
            response.setStatus(Status.NOTGGTODAY);
        }

        return response;
    }

    public Response<List<Cat>> getCatFriends(UUID catId) {
        Response<List<Cat>> response = new Response<>();
        try {
            Cat cat = catRepository.getById(catId).orElseThrow(() -> new ArgumentException("Cat with this ID does not exists"));
            response.setResultClass(catRepository.getFriends(cat));
            response.setStatus(Status.NORM);
        }
        catch (Exception ex) {
            response.setStatus(Status.NOTGGTODAY);
        }

        return response;
    }

    public Response<Owner> getCatOwner(UUID catId) {
        Response<Owner> response = new Response<>();
        try {
            Cat cat = catRepository.getById(catId).orElseThrow(() -> new ArgumentException("Cat with this ID does not exists"));
            response.setResultClass(catRepository.getOwnerByCat(cat));
            response.setStatus(Status.NORM);
        }
        catch (Exception ex) {
            response.setStatus(Status.NOTGGTODAY);
        }

        return response;
    }

    public boolean updateCat(UUID id) {
        try {
            Cat cat = catRepository.getById(id).orElseThrow(() -> new ArgumentException("Cat with this ID does not exists"));
            catRepository.update(cat);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    public boolean addFriend(UUID catId, UUID friendId) {
        try {
            Cat cat = catRepository.getById(catId).orElseThrow(() -> new ArgumentException("Cat with this ID does not exists"));
            Cat friend = catRepository.getById(catId).orElseThrow(() -> new ArgumentException("Cat friend with this ID does not exists"));
            catRepository.addCatFriend(cat, friend);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
}
