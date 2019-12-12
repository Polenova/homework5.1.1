package ru.android.polenova;

import android.widget.ImageView;

public class ContactItem {

    private String contactInfo;
    private String callContact;
    private ImageView buttonRemove;

    public ContactItem(String callContact, String contactInfo, ImageView buttonRemove) {
        this.callContact = callContact;
        this.contactInfo = contactInfo;
        this.buttonRemove = buttonRemove;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getCallContact() {
        return callContact;
    }

    public void setCallContact(String callContact) {
        this.callContact = callContact;
    }

    public ImageView getButtonRemove() {
        return buttonRemove;
    }

}
