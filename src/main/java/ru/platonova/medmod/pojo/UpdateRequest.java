package ru.platonova.medmod.pojo;

import ru.platonova.medmod.DTO.EmployeeDTO;

public class UpdateRequest {
    private EmployeeDTO empl;
    private SignInRequest signInRequest;

    public UpdateRequest(EmployeeDTO empl, SignInRequest signInRequest) {
        this.empl = empl;
        this.signInRequest = signInRequest;
    }

    public EmployeeDTO getEmpl() {
        return empl;
    }

    public void setEmpl(EmployeeDTO empl) {
        this.empl = empl;
    }

    public SignInRequest getSignInRequest() {
        return signInRequest;
    }

    public void setSignInRequest(SignInRequest signInRequest) {
        this.signInRequest = signInRequest;
    }
}
