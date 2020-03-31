package seedu.nova.model;

import java.util.ArrayList;
import java.util.List;

public class VersionedAddressBook extends AddressBook {

    final List<ReadOnlyAddressBook> addressBookStateList;
    int currStatePointer;

    public VersionedAddressBook(ReadOnlyAddressBook initialState) {
        super(initialState);

        addressBookStateList = new ArrayList<>();
        addressBookStateList.add(new AddressBook(initialState));
        currStatePointer = 0;
    }

    //User did something to address book, save that state of address book
    public void commit() {
        addressBookStateList.subList(currStatePointer + 1, addressBookStateList.size()).clear();
        addressBookStateList.add(new AddressBook(this));
        currStatePointer++;
        /*for (int i = 0; i < addressBookStateList.size(); i++) {
            System.out.println(addressBookStateList.get(i).getPersonList().get(0).getRemark());
        } */
    }

    public boolean canUndo() {
        return currStatePointer > 0;
    }

    public boolean canRedo() {
        return currStatePointer < addressBookStateList.size() - 1;
    }

    public void undo() {
        if (canUndo()) {
            currStatePointer--;
            resetData(addressBookStateList.get(currStatePointer));
        }
    }

    public void redo() {
        if (canRedo()) {
            currStatePointer++;
            resetData(addressBookStateList.get(currStatePointer));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VersionedAddressBook)) {
            return false;
        }

        VersionedAddressBook otherVersionedAddressBook = (VersionedAddressBook) other;

        // state check
        return super.equals(otherVersionedAddressBook)
                && addressBookStateList.equals(otherVersionedAddressBook.addressBookStateList)
                && currStatePointer == otherVersionedAddressBook.currStatePointer;
    }
}
