package org.bel.models;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bel.apiHelper.StatusEnumPet;

import java.util.List;

public class Pet {

    private Long id;
    private Category category;
    private String name;
    private List<String> photoUrls = null;
    private List<Tag> tags = null;
    private StatusEnumPet status;

    public Pet() {
        id = Long.valueOf(RandomStringUtils.randomNumeric(10));
        name = "Pet Name: " + RandomStringUtils.randomAlphabetic(8);
        status = StatusEnumPet.available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }


    public StatusEnumPet getStatus() {
        return status;
    }

    public void setStatus(StatusEnumPet status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("category", category).append("name", name).append("photoUrls", photoUrls).append("tags", tags).append("status", status).toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Pet) == false) {
            return false;
        }
        Pet rhs = ((Pet) other);
        return new EqualsBuilder().append(photoUrls, rhs.photoUrls).append(name, rhs.name).append(id, rhs.id).append(category, rhs.category).append(tags, rhs.tags).append(status, rhs.status).isEquals();
    }

}
