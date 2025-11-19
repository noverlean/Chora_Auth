package chora.auth.exceptions.custom;

import chora.auth.exceptions.CustomException;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException() {
        super("User with current credentials was not found. Please, retry later.");
    }
}