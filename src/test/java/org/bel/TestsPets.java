package org.bel;

import org.bel.apiHelper.PetsActionsHelper;
import org.bel.apiHelper.StatusEnumPet;
import org.bel.models.ApiResponse;
import org.bel.models.Pet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class TestsPets {
    private PetsActionsHelper petsActions = new PetsActionsHelper();
    private Long petId = 6644192626L;
    private File image = new File("src/test/resources/petImage.jpeg");


    @Test
    public void addNewPet() {
        Pet newPet = new Pet();
        Long newlyCreatedPetId = newPet.getId();
        petsActions.addNewPet(newPet);
        Pet petFoundById = petsActions.getPetById(newlyCreatedPetId);
        Assert.assertTrue(petFoundById.equals(newPet));

    }

    @Test
    public void uploadImageToExistingPet() {
        ApiResponse uploadResponse = petsActions.uploadImageForPet(petId, image);
        Assert.assertTrue(uploadResponse.getMessage().contains("File uploaded to ./" + image.getName()));

    }

    @Test
    public void getPetsByStatus() {
        List<Pet> petsByStatus = petsActions.findPetsByStatus(StatusEnumPet.pending);
        for (Pet pet : petsByStatus) {
            Assert.assertEquals(pet.getStatus(), StatusEnumPet.pending);
        }
    }

    @Test
    public void deletePet() {
        Pet newPet = new Pet();
        Long petId = newPet.getId();
        petsActions.addNewPet(newPet);
        petsActions.deletePet(petId);
        Assert.assertEquals(petsActions.isPetExistMessage(petId), "Pet not found");

    }


}

