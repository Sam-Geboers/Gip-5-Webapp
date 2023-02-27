package be.ucll.gip5.controller;

import be.ucll.gip5.security.CustomUserDetailsService;
import be.ucll.gip5.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody PersonDTO personDTO) {
//        try{
//            UsernamePasswordAuthenticationToken usat =
//                    new UsernamePasswordAuthenticationToken(personDTO.getUsername(), personDTO.getPassword());
//            authenticationManager.authenticate(usat);
//            return ResponseEntity.ok(jwt);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
//        }
//    }
}
