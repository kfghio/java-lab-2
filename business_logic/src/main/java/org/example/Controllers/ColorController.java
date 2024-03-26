package org.example.Controllers;

import org.example.Enums.Status;
import org.example.Other.ArgumentException;
import org.example.models.Color;
import org.example.reps.ColorRepository;

import java.util.UUID;

public class ColorController {
    private final ColorRepository colorRepository = new ColorRepository();
    public boolean addColor(String colorName) {
        Color color = new Color();
        color.setColor(colorName);
        try {
            colorRepository.insert(color);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    public boolean deleteColor(UUID uuid) {
        try {
            Color color = colorRepository.getById(uuid).orElseThrow(() -> new ArgumentException("Color with this ID does not exists"));
            colorRepository.delete(color);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    public boolean updateColor(UUID uuid) {
        try {
            Color color = colorRepository.getById(uuid).orElseThrow(() -> new ArgumentException("Color with this ID does not exists"));
            colorRepository.update(color);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    public Response<Color> getColorById(UUID uuid) {
        Response<Color> response = new Response<>();
        try {
            response.setResultClass(colorRepository.getById((uuid)).orElseThrow(() -> new ArgumentException("Color with this ID does not exists")));
            response.setStatus(Status.NORM);
        }
        catch (Exception ex) {
            response.setStatus(Status.NOTGGTODAY);
        }

        return response;
    }

}
