package org.example.Controllers;

import org.example.Enums.Status;
import org.example.Other.ArgumentException;
import org.example.models.Cat;
import org.example.models.Owner;
import org.example.reps.CatRepository;
import org.example.reps.OwnerRepository;

import java.util.List;
import java.util.UUID;

public class OwnerController {
    private final CatRepository catRepository = new CatRepository();
    private final OwnerRepository ownerRepository = new OwnerRepository();

    public boolean addOwner(Owner owner) {
        try {
            ownerRepository.insert(owner);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean deleteOwner(UUID id) {
        try {
            Owner owner = ownerRepository.getById(id).orElseThrow(() -> new ArgumentException("Owner with this ID does not exists"));
            ownerRepository.delete(owner);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean updateOwner(UUID id) {
        try {
            Owner owner = ownerRepository.getById(id).orElseThrow(() -> new ArgumentException("Owner with this ID does not exists"));
            ownerRepository.update(owner);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean addCatToOwner(UUID ownerId, UUID catId) {
        try {
            Owner owner = ownerRepository.getById(ownerId).orElseThrow(() -> new ArgumentException("Owner with this ID does not exists"));
            Cat cat = catRepository.getById(catId).orElseThrow(() -> new ArgumentException("Cat with this ID does not exists"));
            ownerRepository.addCatToOwner(owner, cat);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Response<List<Cat>> getAllCatsByOwner(UUID ownerId) {
        Response<List<Cat>> response = new Response<>();
        try {
            Owner owner = ownerRepository.getById(ownerId).orElseThrow(() -> new ArgumentException("Owner with this ID does not exists"));
            response.setResultClass(ownerRepository.getAllCatsByOwner(owner));
            response.setStatus(Status.NORM);
        }
        catch (Exception ex) {
            response.setStatus(Status.NOTGGTODAY);
        }

        return response;
    }

    public Response<Owner> getOwnerById(UUID id) {
        Response<Owner> response = new Response<>();
        try {
            response.setResultClass(ownerRepository.getById((id)).orElseThrow(() -> new ArgumentException("Owner with this ID does not exists")));
            response.setStatus(Status.NORM);
        } catch (Exception ex) {
            response.setStatus(Status.NOTGGTODAY);
        }

        return response;
    }
}
