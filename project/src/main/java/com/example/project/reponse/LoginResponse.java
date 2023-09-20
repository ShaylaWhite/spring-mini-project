package com.example.project.reponse;

/**
 * LoginResponse is a class used to represent the response when a user successfully logs in.
 * It contains a JWT (JSON Web Token) that can be used for authentication.
 */
public class LoginResponse {
    /**
     * The JWT (JSON Web Token) issued upon successful authentication.
     */
    private String jwt;

    /**
     * Constructs a new LoginResponse with the provided JWT.
     *
     * @param jwt The JSON Web Token issued upon successful authentication.
     */
    public LoginResponse(String jwt) {
        this.jwt = jwt;
    }

    /**
     * Gets the JWT (JSON Web Token) from the response.
     *
     * @return The JWT issued upon successful authentication.
     */
    public String getJwt() {
        return jwt;
    }

    /**
     * Sets the JWT (JSON Web Token) in the response.
     *
     * @param jwt The JWT to set in the response.
     */
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
