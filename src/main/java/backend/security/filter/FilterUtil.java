package backend.security.filter;

import com.auth0.jwt.algorithms.Algorithm;

public class FilterUtil {

    public static Algorithm getAlgorithm() {
        // Must place secret in an env variable as encrypted.
        Algorithm algorithm = Algorithm.HMAC256("testSecurity".getBytes());
        return algorithm;
    }
}
