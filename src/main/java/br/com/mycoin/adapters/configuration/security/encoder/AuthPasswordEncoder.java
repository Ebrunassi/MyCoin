package br.com.mycoin.adapters.configuration.security.encoder;

import br.com.mycoin.application.domain.utils.Criptografia;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthPasswordEncoder implements PasswordEncoder {
//    protected Logger log = (Logger) LogManager.getLogger(LoginController.class);
    @Override
    public String encode(CharSequence rawPassword){
        try{
//            MessageDigest m = MessageDigest.getInstance("MD5");
//            m.update(rawPassword.toString().getBytes(),0, rawPassword.length());
//            String a = new BigInteger(1, m.digest()).toString(16);
//            return a;
            String password = rawPassword.toString();
            return Criptografia.getInstance().criptografa(password);
//        }catch(NoSuchAlgorithmException e){
//            e.printStackTrace();
//            return "";
//        }
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword){
        if(encodedPassword == null || encodedPassword.isEmpty())
            return false;
        if(rawPassword == null || rawPassword.length() <= 0)
            return false;
        return encodedPassword.equals(encode(rawPassword));
    }
}
