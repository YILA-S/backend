package backend.controller;

import backend.exception.ErrorDetails;
import backend.filter.FilterUtil;
import backend.services.role.Role;
import backend.services.teacher.TeacherService;
import backend.services.teacher.domain.Teacher;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class BaseController {

    @Resource(name = "teacherService")
    TeacherService teacherService;


    @GetMapping("/")
    @ResponseStatus(code = HttpStatus.OK)
    public String welcome() {
        return "Welcome to yila's school management api !";
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try{
                String token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = FilterUtil.getAlgorithm();
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                String username = decodedJWT.getSubject();

                Teacher teacher = teacherService.findByEmail(username);
                String access_token = JWT.create()
                        .withSubject(teacher.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", teacher.getRoles().stream().map(Role::name).collect(Collectors.toList()))
                        .sign(algorithm);

                response.setHeader("access_token", access_token);
            }
            catch (Exception e) {
                ErrorDetails details = new ErrorDetails(new Date(), e.getMessage(), request.getRequestURI());
                response.setContentType(APPLICATION_JSON_VALUE);
                response.setHeader("error", e.getMessage());
                new ObjectMapper().writeValue(response.getOutputStream(), details);
            }
        }
        else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
