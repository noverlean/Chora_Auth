package chora.auth.exceptions.custom;

import chora.auth.exceptions.CustomException;

public class UserAlreadyExistsException extends CustomException {
    public UserAlreadyExistsException() {
        super("User with current credentials already exists. Check your input fields or retry later.");
    }
}