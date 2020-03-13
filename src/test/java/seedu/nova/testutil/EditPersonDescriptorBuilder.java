package seedu.nova.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

<<<<<<< HEAD:src/test/java/seedu/nova/testutil/EditPersonDescriptorBuilder.java
import seedu.nova.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.nova.model.common.person.Address;
import seedu.nova.model.common.person.Email;
import seedu.nova.model.common.person.Name;
import seedu.nova.model.common.person.Person;
import seedu.nova.model.common.person.Phone;
import seedu.nova.model.common.tag.Tag;
=======
import seedu.nova.logic.commands.AbEditCommand.EditPersonDescriptor;
import seedu.nova.model.common.person.Address;
import seedu.nova.model.common.person.Email;
import seedu.nova.model.common.person.Name;
import seedu.nova.model.common.person.Person;
import seedu.nova.model.common.person.Phone;
import seedu.nova.model.common.tag.Tag;
>>>>>>> c6c0bb78e07ef00942b0263e80b55d6c724c2c2b:src/test/java/seedu/address/testutil/EditPersonDescriptorBuilder.java

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
