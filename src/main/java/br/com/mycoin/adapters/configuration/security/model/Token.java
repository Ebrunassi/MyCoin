package br.com.mycoin.adapters.configuration.security.model;

public class Token {
    private String token_type;
    private String access_token;
    private String expiration;

    public Token() {}

    public Token(String token_type, String access_token, String expiration) {
        this.token_type = token_type;
        this.access_token = access_token;
        this.expiration = expiration;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}
